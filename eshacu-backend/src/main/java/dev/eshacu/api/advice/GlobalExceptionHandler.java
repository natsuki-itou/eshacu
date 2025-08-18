package dev.eshacu.api.advice;

import dev.eshacu.api.dto.ErrorResponse;
import dev.eshacu.api.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(NotFoundException ex, HttpServletRequest req) {
        log.info("404 NOT_FOUND method={} path={} msg={}",
                req.getMethod(), req.getRequestURI(), safeMsg(ex));
        return ErrorResponse.of(404,"NOT_FOUND", ex.getMessage(), req.getRequestURI());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalid(MethodArgumentNotValidException ex, HttpServletRequest req){
        String first = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + (e.getDefaultMessage() == null ? "invalid" : e.getDefaultMessage()))
                .findFirst().orElse("Validation error");
        int count = ex.getBindingResult().getErrorCount();
        // 入力バリデーションは想定内 → INFO（件数と先頭メッセージをログ）
        log.info("400 BAD_REQUEST(BeanValidation) method={} path={} errors={} firstError={}",
                req.getMethod(), req.getRequestURI(), count, first);
        return ErrorResponse.of(400, "BAD_REQUEST", first, req.getRequestURI());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleUnreadable(HttpMessageNotReadableException ex, HttpServletRequest req){
        log.info("400 BAD_REQUEST(MalformedJSON) method={} path={} cause={}",
                req.getMethod(), req.getRequestURI(), rootSimple(ex));
        return ErrorResponse.of(400, "BAD_REQUEST", "Malformed JSON request", req.getRequestURI());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleViolation(ConstraintViolationException ex, HttpServletRequest req){
        String first = ex.getConstraintViolations().stream()
                .findFirst()
                .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                .orElse("Validation error");
        int count = ex.getConstraintViolations().size();
        log.info("400 BAD_REQUEST(ConstraintViolation) method={} path={} violations={} firstViolation={}",
                req.getMethod(), req.getRequestURI(), count, first);
        return ErrorResponse.of(400, "BAD_REQUEST", first, req.getRequestURI());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleMethod(HttpRequestMethodNotSupportedException ex, HttpServletRequest req){
        String allowed = ex.getSupportedHttpMethods() == null ? ""
                : ex.getSupportedHttpMethods().stream().map(HttpMethod::name).sorted().reduce((a, b) -> a + "," + b).orElse("");
        log.info("405 METHOD_NOT_ALLOWED method={} path={} allowed={}",
                req.getMethod(), req.getRequestURI(), allowed);
        String msg = allowed.isEmpty() ? ex.getMessage() : "Method " + ex.getMethod() + " not allowed (allowed: " + allowed + ")";
        return ErrorResponse.of(405, "METHOD_NOT_ALLOWED", msg, req.getRequestURI());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAny(Exception ex, HttpServletRequest req){
        // 想定外エラーは要調査 → ERROR + スタックトレース（必須）
        log.error("500 INTERNAL_ERROR method={} path={} exClass={}",
                req.getMethod(), req.getRequestURI(), ex.getClass().getName(), ex);
        return ErrorResponse.of(500, "INTERNAL_ERROR", "Unexpected error", req.getRequestURI());
    }

    private static String safeMsg(Throwable ex) {
        String m = ex.getMessage();
        if (m == null) return "";
        // 長すぎる/改行含むメッセージを切り詰め（ログ汚染防止）
        m = m.replaceAll("[\\r\\n]+", " ").trim();
        return m.length() > 300 ? m.substring(0, 300) + "..." : m;
    }

    private static String rootSimple(Throwable ex) {
        Throwable r = ex;
        while (r.getCause() != null && r.getCause() != r) r = r.getCause();
        String n = r.getClass().getSimpleName();
        String m = r.getMessage();
        if (m == null) return n;
        m = m.replaceAll("[\\r\\n]+", " ").trim();
        return n + ": " + (m.length() > 200 ? m.substring(0, 200) + "..." : m);
    }
}

package dev.eshacu.api.dto;

import java.time.Instant;
import org.springframework.http.HttpStatus;

/**
 * 内部（FE/自分）専用の標準エラーペイロード。
 * record なので不変・ボイラープレート最小。
 */
public record ErrorResponse(
        int status,
        String code,
        String message,
        String path,
        Instant timestamp
) {
    /** 便利メソッド（よく使う形） */
    public static ErrorResponse of(HttpStatus status, String code, String message, String path) {
        return new ErrorResponse(status.value(), code, message, path, Instant.now());
    }

    /** 数値ステータスを直接渡したい場合 */
    public static ErrorResponse of(int status, String code, String message, String path) {
        return new ErrorResponse(status, code, message, path, Instant.now());
    }
}

package dev.eshacu.api.controller.debug;

import dev.eshacu.api.advice.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = DemoController.class)
@Import(GlobalExceptionHandler.class)
@ActiveProfiles("dev")
public class ExceptionHandlerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void notFound_isHandledAsJson() throws Exception {
        mvc.perform(get("/api/_debug/boom"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.code").value("NOT_FOUND"))
                .andExpect(jsonPath("$.message").value("Demo not Found"));
    }
}

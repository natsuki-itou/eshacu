package dev.eshacu.api.controller.debug;

import dev.eshacu.api.exception.NotFoundException;
import  org.springframework.context.annotation.Profile;
import  org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/_debug/")
@Profile("dev")
public class DemoController {

    @GetMapping("/boom")
    public String boom() {
        throw new NotFoundException("Demo not Found");
    }
}

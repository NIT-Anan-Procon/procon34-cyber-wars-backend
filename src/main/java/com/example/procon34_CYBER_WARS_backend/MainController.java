package com.example.procon34_CYBER_WARS_backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/hello")
    public String index() {
        return "HelloWorld";
    }

}

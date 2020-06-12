package ir.hamycook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @GetMapping("test")
    public String test() {
        return "Hello World";
    }
}

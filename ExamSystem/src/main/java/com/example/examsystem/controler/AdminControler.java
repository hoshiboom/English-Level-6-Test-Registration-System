package com.example.examsystem.controler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminControler {
    @GetMapping(value = "/login")
    public String admin() {
        return "hello world";
    }

}

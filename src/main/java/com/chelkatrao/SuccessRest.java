package com.chelkatrao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuccessRest {
    @GetMapping
    public String success() {
        return "success";
    }
}

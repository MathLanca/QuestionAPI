package com.mackenzie.cif.question.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping()
    public String get(){
        return "API da CIF";
    }
}
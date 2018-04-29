package com.github.edgarzed.CBRTestTask.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @PostMapping("/filter")
    public String getFiltered(Model model) {

        return "index";
    }
}

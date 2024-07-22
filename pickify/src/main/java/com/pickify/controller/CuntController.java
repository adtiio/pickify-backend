package com.pickify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CuntController {
    @GetMapping("/name")
    public RedirectView hereHandler() {
        return new RedirectView("https://www.youtube.com/");
    }
}

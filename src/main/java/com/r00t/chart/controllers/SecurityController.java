package com.r00t.chart.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {
    @RequestMapping("/login")
    public ModelAndView loadLoginPage() {
        return new ModelAndView("login");
    }
}

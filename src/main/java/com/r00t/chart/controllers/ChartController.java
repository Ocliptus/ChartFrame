package com.r00t.chart.controllers;

import com.r00t.chart.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChartController {
    private DataService service;

    public ChartController(DataService service) {
        this.service = service;
    }

    @RequestMapping("/chart")
    public ModelAndView loadChartPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("chart");
        modelAndView.addObject("dataList", service.getAllData());
        return modelAndView;
    }
}

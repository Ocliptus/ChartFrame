package com.r00t.chart.controllers;

import com.r00t.chart.models.DataModel;
import com.r00t.chart.services.DataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SettingsController {
    private DataService dataService;

    public SettingsController(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/")
    public ModelAndView loadSettingsPage(ModelAndView modelAndView) {
        modelAndView.setViewName("settings");
        modelAndView.addObject("dataList", dataService.getAllData());
        return modelAndView;
    }

    @PostMapping("/save-update")
    public ModelAndView saveData(@ModelAttribute DataModel dataModel,
                                 ModelAndView modelAndView,
                                 RedirectAttributes redirectAttributes) {
        dataService.saveData(dataModel);

        modelAndView.setViewName("redirect:/");
        redirectAttributes.addFlashAttribute("isSuccess", true);
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public ModelAndView deleteData(@PathVariable("id") String id,
                                   ModelAndView modelAndView,
                                   RedirectAttributes redirectAttributes) {
        dataService.deleteDataWithId(id);

        modelAndView.setViewName("redirect:/");
        redirectAttributes.addFlashAttribute("isSuccess", true);
        return modelAndView;
    }
}

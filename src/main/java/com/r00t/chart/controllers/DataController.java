package com.r00t.chart.controllers;

import com.r00t.chart.models.DataModel;
import com.r00t.chart.services.DataService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DataController {
    private DataService service;

    public DataController(DataService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public DataModel addData(@RequestBody DataModel dataModel) throws Exception {
        return service.saveData(dataModel);
    }

    @GetMapping("/all")
    public List<DataModel> getAll() {
        return service.getAllData();
    }

    @DeleteMapping("/remove/{time}")
    public void removeData(@PathVariable("time") String time) {
        service.deleteDateWithTime(time);
    }
}

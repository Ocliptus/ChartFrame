package com.r00t.chart;

import com.r00t.chart.models.DataModel;
import com.r00t.chart.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChartApplication implements CommandLineRunner {
    @Autowired
    private DataService tempDataService;

    public static void main(String[] args) {
        SpringApplication.run(ChartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        tempDataService.deleteAll();
        for (int y = 1; y < 12; y++)
            for (int x = 1; x < 30; x++) {
                DataModel _dataModel = new DataModel();

                String __monthText = "";
                if (y < 10)
                    __monthText = "0" + String.valueOf(y);
                else
                    __monthText = String.valueOf(y);

                String __dayText = "";
                if (x < 10)
                    __dayText = "0" + String.valueOf(x);
                else
                    __dayText = String.valueOf(x);

                _dataModel.setTime("2016-" + __monthText + "-" + __dayText);
                _dataModel.setValue(x * 0.3);

                tempDataService.saveData(_dataModel);
            }
    }
}

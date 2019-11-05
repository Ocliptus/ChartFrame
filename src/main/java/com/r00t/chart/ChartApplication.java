package com.r00t.chart;

import com.r00t.chart.models.UserModel;
import com.r00t.chart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ChartApplication implements CommandLineRunner {
    @Autowired
    private UserService service;

    public static void main(String[] args) {
        SpringApplication.run(ChartApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.dropAll();
        UserModel userModel = new UserModel();
        userModel.setUserName("root");
        userModel.setPassword(new BCryptPasswordEncoder()
                .encode("test"));
        userModel.setActive(true);
        service.insertUser(userModel);
    }
}

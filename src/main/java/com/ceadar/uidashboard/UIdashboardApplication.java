package com.ceadar.uidashboard;

import com.ceadar.uidashboard.datamodel.entity.Status;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
public class UIdashboardApplication {

    public static void main(String[] args) {

        SpringApplication.run(UIdashboardApplication.class, args);
    }
}

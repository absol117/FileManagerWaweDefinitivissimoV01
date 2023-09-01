package com.scai.filemanager_wave2_v02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile({"dev", "prod", "test"})
public class FileManagerWave2V02Application {

    public static void main(String[] args) {
        SpringApplication.run(FileManagerWave2V02Application.class, args);
    }

}

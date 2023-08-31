package com.scai.filemanager_wave2_v02.main;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {


        String pathSource = "C:/Users/lucam/OneDrive/Desktop/GestoreFile/FileManager_Wave2_V02/src/main/resources/static/prova.txt";
        String pathDestination = "C:/Users/lucam/OneDrive/Desktop/GestoreFile/FileManager_Wave2_V02/src/main/resources/templates/prova.txt";
        try {
            Files.move(Path.of(pathSource), Path.of(pathDestination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

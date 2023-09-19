package com.scai.filemanager_wave2_v02;

import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;

public class Sockets {

    @SneakyThrows
    public static void main(String[] args) {
        File target = new File("prova.obj");

        Serializable oggetto = Oggetto.builder()
                .name("Pippo")
                .age(20)
                .build();

        try (var stream = new ObjectOutputStream(new FileOutputStream(target))){
            stream.writeObject(oggetto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (var stream = new ObjectInputStream(new FileInputStream(target))){
            Object o = stream.readObject();
            if (o instanceof Oggetto obj) {
                System.out.println("Letto:");
                System.out.printf(obj.toString());
            } else System.err.println("Non letto correttamente!");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Files.deleteIfExists(target.toPath());

    }

    @Data
    @Builder
    public static class Oggetto implements Serializable {

        @Serial
        private final static long serialVersionUID = 1;

        private String name;
        private String surname;
        private int age;

    }

}

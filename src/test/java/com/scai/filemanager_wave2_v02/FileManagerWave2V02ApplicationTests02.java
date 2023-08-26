package com.scai.filemanager_wave2_v02;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.model.User;
import com.scai.filemanager_wave2_v02.service.DocumentService;
import com.scai.filemanager_wave2_v02.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@DirtiesContext
public class FileManagerWave2V02ApplicationTests02 {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private UserService userService;

    private static Path pathDir;


    @BeforeAll
    public static void createDir() {
        try {
            pathDir = Files.createTempDirectory("testDir02");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void addDoc() {

        User luca = User.builder()
                .name("luca")
                .build();
        User userL = userService.create(luca);

        assertTrue(userService.findById(userL.getId()).isPresent());

        Document buildDoc = Document.builder()
                .tags(List.of("allora", "vediamo"))
                .path(pathDir.resolve("prova01.txt").toString())
                .build();

        Document document = documentService.create(buildDoc);

        assertTrue(documentService.findById(document.getId()).isPresent());

        userService.findById(userL.getId()).map(user -> user.getDocumentList().add(buildDoc));

        Optional<Document> document1 = userService.findById(userL.getId()).map(user -> user.getDocumentList().get(0));
        assertTrue(document1.isPresent());

    }








}

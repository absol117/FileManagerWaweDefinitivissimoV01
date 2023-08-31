package com.scai.filemanager_wave2_v02;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.repository.DocumentRepository;
import com.scai.filemanager_wave2_v02.service.DocumentService;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext
class FileManagerWave2V02ApplicationTests {
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentRepository documentRepository;
    private static Path pathDir;


    @BeforeAll
    public static void makeDir() {
        try {
            pathDir = Files.createTempDirectory("testDir");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void make() {
        Document newDoc = Document.builder()
                .tags(List.of("fabio", "gay", "ebreo"))
                .name("fabioGay.txt")
                .path(pathDir.resolve("FabioFibra.txt").toString())
                .build();

        Document ris = documentService.create(newDoc);
        assertTrue(ris.getFile().exists());
        assertTrue(newDoc.getFile().exists());
        assertEquals(ris.getPath(), newDoc.getPath());

        Optional<Document> byId = documentService.findById(ris.getId());
        assertFalse(byId.isEmpty());

        documentService.delete(byId.get().getId());
        assertFalse(documentService.findById(ris.getId()).isPresent());

    }
    @Test
    public void delete() {
        Document newDoc = Document.builder()
                .tags(List.of("fabio", "gay", "ebreo"))
                .name("fabioGay.txt")
                .path(pathDir.resolve("FabioFibra.txt").toString())
                .build();
        Document ris = documentService.create(newDoc);


        Optional<Document> byId = documentService.findById(ris.getId());
        assertFalse(byId.isEmpty());

        documentService.delete(byId.get().getId());
        assertFalse(documentService.findById(ris.getId()).isPresent());
    }

    @Test
    public void fix() {
        Document oldDoc = Document.builder()
                .tags(List.of("fabio", "gay", "ebreo"))
                .name("fabioGay.txt")
                .path(pathDir.resolve("FabioFibra.txt").toString())
                .build();
        Document ris = documentService.create(oldDoc);

        assertTrue(ris.getFile().exists());
        assertFalse(ris.getPath().isBlank());

        Document newDoc = new Document();
        newDoc.setName("LucaCapoSupremo.txt");
        newDoc.setTags(oldDoc.getTags());
        newDoc.setPath(pathDir.resolve("lucaCapo.txt").toString());

        Document update = documentService.update(oldDoc.getId(), newDoc);

        assertEquals(update.getId(), oldDoc.getId());
        assertNotEquals(update.getName(), oldDoc.getName());
        assertNotEquals(update.getPath(), oldDoc.getPath());
        assertTrue(update.getFile().exists());


        documentService.delete(ris.getId());
    }



    @Test
    void shouldMoveDocument() {
        Document document = Document.builder()
                .tags(List.of("test", "file", "document", "txt"))
                .path(pathDir.resolve("test.txt").toString())
                .build();

        document = documentService.create(document);

        Path path2 = pathDir.resolve("test2.txt");
        Document result = documentService.move(document, path2.toString());

        assertTrue(result.getPath().endsWith("test2.txt"));
    }


    @Test
    public void write() {

        String content = "bucchina";

        Document document = Document.builder()
                .tags(List.of("fabio", "gay", "ebreo"))
                .name("fabioGay.txt")
                .path(pathDir.resolve("FabioFibrga.txt").toString())
                .build();

        document = documentService.create(document);

        documentService.write(document.getId(), content);

        assertTrue(document.getFile().canWrite());
        assertTrue(document.getFile().length() > 2);

        documentService.delete(document.getId());
    }


    @Test
    public void read() {
        String content = "bucchina";

        Document oldDoc = Document.builder()
                .tags(List.of("fabio", "gay", "ebreo"))
                .name("fabioGay.txt")
                .path(pathDir.resolve("FabioFiaabra.txt").toString())
                .build();

        Document document = documentService.create(oldDoc);

        documentService.write(document.getId(), content);

        documentService.read(document.getId());

        assertTrue(document.getFile().canRead());
        assertTrue(document.getFile().length() > 2);


    }



}

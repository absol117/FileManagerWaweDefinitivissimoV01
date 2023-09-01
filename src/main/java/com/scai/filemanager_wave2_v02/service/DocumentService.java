package com.scai.filemanager_wave2_v02.service;

import com.scai.filemanager_wave2_v02.configuration.FileConfig;
import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.profiles.ProfileBasedStrategy;
import com.scai.filemanager_wave2_v02.profiles.ProfileBasedStrategy.GeneratorStrategy;
import com.scai.filemanager_wave2_v02.repository.DocumentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(FileConfig.class)
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final GeneratorStrategy<String, String> generater;

    @PostConstruct
    public void init() {
        log.info("generator initialized");
        generater.apply("test");
    }

    public String read(int id) {
        generater.apply("");
        Optional<Document> byId = findById(id);
        if(byId.isEmpty()) {
            throw new RuntimeException("Errore: il documento non è presente nel database");
        }
        Document document = byId.get();
        if(document.getPath().isBlank()) {
            throw new RuntimeException("Errore: il path non è presente nel documento");
        }
        try {
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(document.getPath()));
            String ris;
            while ((ris = bufferedReader.readLine()) != null) {
                stringBuilder.append(ris).append("\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void write(int id, String content) {
        Optional<Document> byId = findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Errore: il doumento non è presente nel database");
        }

        Document document = byId.get();
        if (document.getPath().isBlank()) {
            throw new RuntimeException("Errore: il path non è presente nel documento");
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(document.getPath()))){
            bufferedWriter.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Document> findAll() {
        return documentRepository.findAll();
    }

    public Optional<Document> findById(int id) {
        return documentRepository.findById(id);
    }

    public Document create(Document document) {
        if(document.getPath().isBlank()) {
            throw new RuntimeException("sei un coglione, il path è vuoto");
        }

        if(Files.exists(Path.of(document.getPath()))) {
            try {
                Files.delete(Path.of(document.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.createFile(Path.of(document.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return documentRepository.save(document);

    }




    public boolean delete(int id) {
        if(findById(id).isEmpty()) {
            throw new RuntimeException("Il documento non ha un id associato nel database");
        }
        Document document = findById(id).get();
        try {
            Files.deleteIfExists(Path.of(document.getPath()));
            documentRepository.delete(document);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Document update(int id, Document newDocumnet) {
        if(newDocumnet.getPath().isBlank()) {
            throw new RuntimeException("il path è vuoto brody");
        }

        if (findById(id).isEmpty()) {
            throw new RuntimeException("il file non è presente nel database");
        }

        Document document1 = findById(id).get();
        try {
            Files.delete(Path.of(document1.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        document1.setName(newDocumnet.getName());
        document1.setTags(newDocumnet.getTags());
        document1.setPath(newDocumnet.getPath());

        documentRepository.save(document1);

        try {
            Files.createFile(Path.of(document1.getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return document1;
    }


    public Document move(Document document, String newPath) {
        log.info("Doc: {}", document);
        return documentRepository.findDocumentByPath(document.getPath())
                .map(d -> {
                    try {
                        log.debug("Attempting to move document {} from {} to {}", document.getId(), document.getPath(), newPath);
                        Files.move(Path.of(d.getPath()), Path.of(newPath), StandardCopyOption.REPLACE_EXISTING);

                        log.debug("Successfully moved document {} from {} to {}", document.getId(), document.getPath(), newPath);
                        d.setPath(newPath);
                        return documentRepository.save(d);
                    } catch (Exception e) {
                        log.debug("Unable to move document {} from {} to {}", document.getId(), document.getPath(), newPath);
                        return null;
                    }
                }).orElseThrow(() -> new RuntimeException("Document not found"));
    }



    public Document move(int id, String path) {
        return documentRepository.findById(id).map(document -> move(document, path)).
                orElseThrow(() -> new RuntimeException("Document not found"));
    }



}

package com.scai.filemanager_wave2_v02.service;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.repository.DocumentRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Profile("dev")
@RequiredArgsConstructor
public class DocumentService_Profile_Dev {

    private final DocumentRepository documentRepository;


    @Value("${upload.directory}")
    private String uploadDirectory;

   public Document addDoc() {
       try {
           Path path = Files.createFile(Path.of(uploadDirectory)).resolve("test01.txt");
           Document document = new Document();
           document.setName("doc1Test");
           document.setPath(path.toString());
           document.setTags(List.of("tag1","tag2"));

           return documentRepository.save(document);

       } catch (IOException e) {
           throw new RuntimeException(e);
       }

   }

   public List<Document> getAll() {
       return documentRepository.findAll();
   }



}

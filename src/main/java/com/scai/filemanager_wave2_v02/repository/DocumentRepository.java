package com.scai.filemanager_wave2_v02.repository;

import com.scai.filemanager_wave2_v02.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findDocumentByPath(String path);
}

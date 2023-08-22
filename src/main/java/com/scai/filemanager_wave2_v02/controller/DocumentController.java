package com.scai.filemanager_wave2_v02.controller;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;
    @GetMapping("/get")
    public List<Document> getAll() {
        return documentService.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Document> findById(@PathVariable int id) {
        return documentService.findById(id);
    }


    @PostMapping("/add")
    public Document add(@RequestBody Document document) {
        return documentService.create(document);
    }

    @PutMapping("/fix/{id}")
    public Document fix(@PathVariable int id, @RequestBody Document document) {
        return documentService.update(id,document);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable int id) {
        return documentService.delete(id);
    }

    @PostMapping("/move/{id}")
    public Document move(@PathVariable int id, @RequestBody String path) {
        return documentService.move(id, path);
    }

    @GetMapping("/read/{id}")
    public String read(@PathVariable int id) {
        return documentService.read(id);
    }

    @PostMapping("/write/{id}")
    public void write(@PathVariable int id, @RequestBody String content) {
        documentService.write(id,content);
    }




}

package com.scai.filemanager_wave2_v02.controller;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.service.DocumentService_Profile_Dev;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class DocumentController_Profile_Dev {

    private final DocumentService_Profile_Dev documentServiceProfileDev;


    @GetMapping("/")
    public List<Document> getAll() {
        return documentServiceProfileDev.getAll();
    }


    @PostMapping("/addDev")
    public Document add() {
        return documentServiceProfileDev.addDoc();
    }


}

package com.scai.filemanager_wave2_v02.service;

import com.scai.filemanager_wave2_v02.model.Document;
import com.scai.filemanager_wave2_v02.model.User;
import com.scai.filemanager_wave2_v02.repository.DocumentRepository;
import com.scai.filemanager_wave2_v02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final DocumentRepository documentRepository;
    public User create(User user) {
       return userRepository.save(user);
    }

    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

    public boolean addDocument(int userId, int documentId) {
        if(findById(userId).isPresent()) {
            User user = findById(userId).get();
            if (documentRepository.findById(documentId).isPresent()) {
                documentRepository.findById(documentId)
                        .ifPresent(document -> user.getDocumentList().add(document));
                return true;
            } else {
                log.info("Errore: il documento non è presente nel database");
            }

        } else {
            log.info("Errore: l'utente non è presente nel database");
        }
        return false;
    }

    public boolean deleteDocument(int userId, int documentId) {
        if(findById(userId).isPresent()) {
            User user = findById(userId).get();
            if (documentRepository.findById(documentId).isPresent()) {
                documentRepository.findById(documentId)
                        .ifPresent(document -> user.getDocumentList().remove(document));
                return true;
            } else {
                log.info("Errore: il documento non è presente nel database");
            }

        } else {
            log.info("Errore: l'utente non è presente nel database");
        }
        return false;
    }




}

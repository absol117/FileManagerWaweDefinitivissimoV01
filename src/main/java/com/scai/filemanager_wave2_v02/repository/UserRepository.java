package com.scai.filemanager_wave2_v02.repository;

import com.scai.filemanager_wave2_v02.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}

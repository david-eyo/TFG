package com.tfg.dao;

import com.tfg.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFileUploadDao extends JpaRepository<UploadedFile, String> {

}

package com.tfg.service;

import com.tfg.entity.UploadedFile;
import org.springframework.web.multipart.MultipartFile;


public interface IFileUploadService {
    public UploadedFile uploadToDb(MultipartFile file);

    public UploadedFile downloadFile(String fileId);
}

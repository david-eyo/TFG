package com.tfg.service;

import com.tfg.dao.IFileUploadDao;
import com.tfg.entity.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUploadServiceImpl implements IFileUploadService{

    @Autowired
    private IFileUploadDao fileUploadDao;

    @Override
    public UploadedFile uploadToDb(MultipartFile file) {
        UploadedFile uploadedFile = new UploadedFile();
        try {
            uploadedFile.setFileData(file.getBytes());
            uploadedFile.setFileType(file.getContentType());
            uploadedFile.setFileName(file.getOriginalFilename());
            return fileUploadDao.save(uploadedFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UploadedFile downloadFile(String fileId) {
        UploadedFile uploadedFileToRet = fileUploadDao.getOne(fileId);
        return uploadedFileToRet;
    }
}

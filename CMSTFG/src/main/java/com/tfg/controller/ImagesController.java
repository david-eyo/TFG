package com.tfg.controller;

import com.tfg.entity.UploadedFile;
import com.tfg.response.FileUploadResponse;
import com.tfg.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/imagenes")
public class ImagesController {


    @Autowired
    private IFileUploadService fileUploadService;

    @PostMapping("/upload/db")
    public FileUploadResponse uploadDb(@RequestParam("image")MultipartFile multipartFile){
        UploadedFile uploadedFile= fileUploadService.uploadToDb(multipartFile);
        FileUploadResponse response = new FileUploadResponse();
        if (uploadedFile != null){
            String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/imagenes/download/")
                    .path(uploadedFile.getFileId())
                    .toUriString();

            response.setDownloadUri(downloadUri);
            response.setFileId(uploadedFile.getFileId());
            response.setFileType(uploadedFile.getFileType());
            response.setUploadStatus(true);
            response.setMessage("File uploaded successfully!");
            return response;
        }
        response.setMessage("Algo no ha ido como se esperaba, por favor vuelva a subir el archivo!");
        return response;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String id){
        UploadedFile uploadedFile= fileUploadService.downloadFile(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(uploadedFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= "+uploadedFile.getFileName())
                .body(new ByteArrayResource(uploadedFile.getFileData()));
    }


}

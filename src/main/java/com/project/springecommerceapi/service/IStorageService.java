package com.project.springecommerceapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    String uploadFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);

    String getFileUrl(String fileName);
}

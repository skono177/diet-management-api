package com.example.diet.repository.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {

    void upload(MultipartFile file, String key) throws Exception;

    byte[] download();
}

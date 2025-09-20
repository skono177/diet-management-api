package com.example.diet.repository.file;

import org.springframework.web.multipart.MultipartFile;

public class FileRepositoryImpl implements FileRepository {

    @Override
    public void upload(MultipartFile file, String key) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'upload'");
    }

    @Override
    public byte[] download() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'download'");
    }

}

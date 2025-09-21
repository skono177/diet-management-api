package com.example.diet.repository.file;

import java.net.URLConnection;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet.repository.s3.S3ClientProvider;

import software.amazon.awssdk.core.sync.RequestBody;

@Repository("fileRepositoryLocalImpl")
public class FileRepositoryLocalImpl implements FileRepository {

    private final S3ClientProvider s3ClientProvider;

    public FileRepositoryLocalImpl(S3ClientProvider s3ClientProvider) {
        this.s3ClientProvider = s3ClientProvider;
    }

    @Override
    public void upload(MultipartFile file, String key) throws Exception {
        String contentType = URLConnection
            .guessContentTypeFromName(file.getName());
        s3ClientProvider.getS3Client().putObject(
            builder -> builder.bucket(s3ClientProvider.getBucketName())
                .key(key)
                .contentType(contentType)
                .build(),
            RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
    }

    @Override
    public byte[] download() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'download'");
    }

}

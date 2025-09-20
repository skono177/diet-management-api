package com.example.diet.repository.s3;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Component
public class S3ClientProvider {

    private final S3Client s3Client;
    private final String bucketName;

    public S3ClientProvider(
        @Value("${aws.s3.access-key}") String accessKey,
        @Value("${aws.s3.secret-key}") String secretKey,
        @Value("${aws.s3.bucket-name}") String bucketName,
        @Value("${aws.s3.region}") String region) {
        this.bucketName = bucketName;

        AwsBasicCredentials creds = AwsBasicCredentials.create(accessKey,
            secretKey);
        this.s3Client = S3Client.builder()
            .region(Region.of(region))
            .credentialsProvider(StaticCredentialsProvider.create(creds))
            .build();
    }

    public S3Client getS3Client() {
        return s3Client;
    }

    public String getBucketName() {
        return bucketName;
    }
}

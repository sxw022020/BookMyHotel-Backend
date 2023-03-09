package com.haileysun.bookmyhotel.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.haileysun.bookmyhotel.exception.AWSS3UploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageStorageService {
    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Value("${aws.s3.region}")
    String region;

    public String save(MultipartFile file) throws AWSS3UploadException {

        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

        String filename = UUID.randomUUID().toString();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/png");

        try {
            // upload the image
            s3Client.putObject(new PutObjectRequest(bucketName, filename, file.getInputStream(), metadata));

        } catch (IOException e) {
            throw new AWSS3UploadException("Failed to upload file to S3");
        }

        return s3Client.getUrl(bucketName, filename).toExternalForm();
    }
}

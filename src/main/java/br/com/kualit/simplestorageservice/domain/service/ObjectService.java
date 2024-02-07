package br.com.kualit.simplestorageservice.domain.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.val;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ObjectService {
    @Autowired
    private AmazonS3Client amazonS3Client;

    @Value("${amazon.config.upload.files.directory}")
    String filesToUploadDirectory;

    @Value("${amazon.config.download.files.directory}")
    String filesToDownloadDirectory;

    public void putObject(String bucketName, String objectName) {
        val file = new File(filesToUploadDirectory.concat(objectName));
        amazonS3Client.putObject(
                bucketName,
                objectName,
                file
        );
    }

    public List<S3ObjectSummary> listObjects(String bucketName) {
        val objectList = amazonS3Client.listObjects(bucketName);
        return objectList.getObjectSummaries();
    }

    public void downloadObject(String bucketName, String objectName) {
        val s3object = amazonS3Client.getObject(bucketName, objectName);
        val inputStream = s3object.getObjectContent();
        try {
            FileUtils.copyInputStreamToFile(inputStream, new File(filesToDownloadDirectory + objectName));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void deleteObject(String bucketName, String objectName) {
        amazonS3Client.deleteObject(bucketName, objectName);
    }

    public void moveObject(String bucketSourceName, String objectName, String bucketTargetName) {
        amazonS3Client.copyObject(
                bucketSourceName,
                objectName,
                bucketTargetName,
                objectName
        );
    }
}

package br.com.kualit.simplestorageservice.domain.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BucketService {

    @Autowired
    private AmazonS3Client amazonS3Client;

    public String createABucket(String bucketName) {

        if (amazonS3Client.doesBucketExist(bucketName)) {
            val error = "This bucket already exists, please create a bucket with another name";
            log.warn(error);
            return error;
        }
        amazonS3Client.createBucket(bucketName);
        return "bucket crated";
    }

    public List<String> listBuckets() {
        val buckets = amazonS3Client.listBuckets();
        return buckets.stream().map(Bucket::getName).collect(Collectors.toList());
    }

    public void deleteABucket(String bucketName) {
        try {
            amazonS3Client.deleteBucket(bucketName);
        } catch (AmazonServiceException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}

package br.com.kualit.simplestorageservice.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class AWSConfig {

    @Value("${amazon.config.accessKey}")
    private String ACCESS_KEY;

    @Value("${amazon.config.secretKey}")
    private String SECRET_KEY;

    @Value("${amazon.config.region}")
    private String REGION;

    public AWSCredentials credentials() {
       return  new BasicAWSCredentials(
                ACCESS_KEY,
                SECRET_KEY
        );
    }

    @Bean
    public AmazonS3Client amazonS3Client() {
        return (AmazonS3Client) AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials()))
                .withRegion(REGION)
                .build();
    }
}

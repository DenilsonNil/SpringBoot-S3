package br.com.kualit.simplestorageservice.api.controller;

import br.com.kualit.simplestorageservice.domain.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buckets")
public class BucketController {

    @Autowired
    private BucketService bucketService;

    @GetMapping
    public ResponseEntity<List<String>>listBuckets(){
        return ResponseEntity.ok(bucketService.listBuckets());
    }

    @GetMapping("/create/{bucketName}")
    public ResponseEntity<String> createABucket(@PathVariable String bucketName) {
        return ResponseEntity.ok(bucketService.createABucket(bucketName));
    }

    @DeleteMapping("/delete/{bucketName}")
    public ResponseEntity<?> delete(@PathVariable String bucketName) {
        bucketService.deleteABucket(bucketName);
        return ResponseEntity.ok().build();
    }
}

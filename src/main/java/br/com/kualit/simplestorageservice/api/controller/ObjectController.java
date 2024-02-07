package br.com.kualit.simplestorageservice.api.controller;

import br.com.kualit.simplestorageservice.domain.service.ObjectService;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/objects")
public class ObjectController {

    @Autowired
    private ObjectService objectService;

    @GetMapping("/putObject")
    public ResponseEntity<?> putObject(@RequestParam String bucketName, @RequestParam String objectName) {
        objectService.putObject(bucketName, objectName);
        return ResponseEntity.ok(objectName.concat(" created."));
    }

    @GetMapping("/listObjects/{bucketName}")
    public ResponseEntity<List<S3ObjectSummary>> listObjects(@PathVariable String bucketName) {
        return ResponseEntity.ok(objectService.listObjects(bucketName));
    }

    @GetMapping("/downloadObject")
    public ResponseEntity<?> downloadObject(@RequestParam String bucketName, @RequestParam String objectName) {
        objectService.downloadObject(bucketName, objectName);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteObject")
    public ResponseEntity<?> deleteObject(@RequestParam String bucketName, @RequestParam String objectName) {
        objectService.deleteObject(bucketName, objectName);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/moveObject")
    public ResponseEntity<?> moveObject(
            @RequestParam String bucketSourceName,
            @RequestParam String objectName,
            @RequestParam String bucketTargetName
    ) {
        objectService.moveObject(bucketSourceName, objectName, bucketTargetName);
        return ResponseEntity.ok().build();
    }
}

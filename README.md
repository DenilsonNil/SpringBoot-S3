### ABOUT THIS PROJECT
This project implements a Amazon S3 Service. 
You can:

- Create a bucket
- List all buckets of your aws account
- Delete a bucket
- Put an object to a bucket
- List all objects from a specific bucket
- Download an object
- Delete an object
- Move an object to another bucket
  

### HOW TO USE IT
Open the file application.yml
Provide values for the environment variables below:

- AMAZON_CONFIG_ACCESS_KEY
- AMAZON_CONFIG_SECRET_KEY
- AMAZON_CONFIG_REGION
- AMAZON_CONFIG_UPLOAD_FILES_DIR
- AMAZON_CONFIG_DOWNLOAD_FILES_DIR

Run the application

### CURL

## Create a bucket

```curl
curl --location 'localhost:8080/api/v1/buckets/create/bucketname'
```

## List all buckets of your aws account

```curl
curl --location 'localhost:8080/api/v1/buckets'
```

## Delete a bucket
```curl
curl --location --request DELETE 'localhost:8080/api/v1/buckets/delete/bucketname'
```

## Put an object to a bucket
```curl
curl --location 'localhost:8080/api/v1/objects/putObject?bucketName=bucketName&objectName=myfile.txt'
```

## List all objects from a specific bucket
```curl
curl --location 'localhost:8080/api/v1/objects/listObjects/bucketName'
```

## Download an object
```curl
curl --location 'http://localhost:8080/api/v1/objects/downloadObject?bucketName=bucketName&objectName=myfile.txt'
```

## Delete an object
```curl
curl --location --request DELETE 'http://localhost:8080/api/v1/objects/deleteObject?bucketName=bucketName&objectName=myfile.txt'
```

## Move an object to another bucket
```curl
curl --location 'http://localhost:8080/api/v1/objects/moveObject?bucketSourceName=sourceBucket&objectName=myfile.txt&bucketTargetName=targetBucket'
```

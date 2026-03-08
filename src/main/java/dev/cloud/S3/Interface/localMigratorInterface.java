package dev.cloud.S3.Interface;

public interface localMigratorInterface<AnyType> {

    void uploadFile(String bucketName, String key, String filePath);

}
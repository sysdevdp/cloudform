package dev.cloud.S3;

import dev.cloud.S3.Interface.localMigratorInterface;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.nio.file.Paths;

public class localMigrator<AnyType> implements localMigratorInterface<AnyType> {
    private final S3Client s3Client;


    public localMigrator(Region region) {

        this.s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    @Override
    public void uploadFile(String bucketName, String key, String filePath) {
        try {
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();
            s3Client.putObject(request, RequestBody.fromFile(Paths.get(filePath)));
            System.out.println("Uploaded " + filePath + " to " + bucketName + "/" + key);
        } catch (Exception e) {
            System.err.println("Upload failed: " + e.getMessage());
        }
    }


}
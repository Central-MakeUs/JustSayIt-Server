package com.justsayit.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.justsayit.infra.s3.dto.ProfileImgInfo;
import com.justsayit.infra.s3.exception.FailToUploadImage;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@Transactional
public class UploadImageService implements UploadImageUseCase {

    private AmazonS3 amazonS3;
    private String bucket;

    public UploadImageService(AmazonS3 amazonS3, @Value("${cloud.aws.s3.bucket}") String bucket) {
        this.amazonS3 = amazonS3;
        this.bucket = bucket;
    }

    @Override
    public ProfileImgInfo uploadProfileImg(MultipartFile multipartFile) {
        // 사진을 업로드 하지 않은 경우 기본 프로필 이미지로 생성함
        // TODO 랜덤 이미지 생성기로 변경 필요
        if (multipartFile.isEmpty()) {
            return new ProfileImgInfo("https://jsi-bucket.s3.ap-northeast-2.amazonaws.com/default-profile-blue.png");
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);
        String storeFileName = UUID.randomUUID() + "." + ext;
        String key = "store/logo/" + storeFileName;


        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(
                    new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new FailToUploadImage();
        }

        String storeFileUrl = amazonS3.getUrl(bucket, key).toString();
        return new ProfileImgInfo(storeFileUrl);
    }
}

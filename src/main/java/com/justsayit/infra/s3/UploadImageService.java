package com.justsayit.infra.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.justsayit.infra.s3.dto.ProfileImgInfo;
import com.justsayit.infra.s3.dto.StoryImgInfo;
import com.justsayit.infra.s3.exception.FailToUploadFileException;
import com.justsayit.infra.s3.exception.FileSizeOverflowException;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
        String originalFilename = multipartFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);
        String storeFileName = UUID.randomUUID() + "." + ext;
        String key = "profile/" + storeFileName;
        return ProfileImgInfo.of(putToS3(multipartFile, key));
    }

    @Override
    public List<StoryImgInfo> uploadStoryImg(List<MultipartFile> multipartFileList) {
        List<StoryImgInfo> storyImgInfoList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFileList) {
            String originalFilename = multipartFile.getOriginalFilename();
            int index = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(index + 1);
            String storeFileName = UUID.randomUUID() + "." + ext;
            String key = "story/" + storeFileName;
            storyImgInfoList.add(StoryImgInfo.of(putToS3(multipartFile, key)));
        }
        return storyImgInfoList;
    }

    private String putToS3(MultipartFile multipartFile, String key) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(
                    new PutObjectRequest(bucket, key, inputStream, getObjectMetadata(multipartFile))
                            .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (FileSizeLimitExceededException e) {
            throw new FileSizeOverflowException();
        } catch (IOException e) {
            throw new FailToUploadFileException();
        }
        return amazonS3.getUrl(bucket, key).toString();
    }

    private ObjectMetadata getObjectMetadata(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        return objectMetadata;
    }
}

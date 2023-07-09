package com.oncha.oncha_web.feature.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class AmazonS3Service {

    private final String IMSI_FILE_NAME = "onchaProductImage";

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    private final AmazonS3Client amazonS3Client;

    /**
     * S3로 파일 업로드
     */
    public List<S3FileDto> uploadFiles(List<MultipartFile> multipartFiles, String prefixPath) {

        List<S3FileDto> s3files = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {

            String originalFileName = multipartFile.getOriginalFilename();

            try (InputStream inputStream = multipartFile.getInputStream()) {

                //s3에 저장할 파일 패스 생성
                String processedPrefix = reProcessFilePath(prefixPath);
                String fileDatePath = getFolderDatePath();
                String filePath = processedPrefix + fileDatePath;

                //s3에 저장할 키 생성
                String uploadFileName = getUUIDFileName(multipartFile);
                String keyName = filePath + uploadFileName;

                //메타 데이터 작성
                ObjectMetadata objectMetadata = getMetaDataByMultipart(multipartFile);

                // S3에 폴더 및 파일 업로드
                amazonS3Client.putObject(
                        new PutObjectRequest(bucketName, keyName, inputStream, objectMetadata));

                // S3에 업로드한 폴더 및 파일 URL
                String uploadFileUrl = amazonS3Client.getUrl(bucketName, keyName).toString();

                s3files.add(
                    S3FileDto.builder()
                        .originalFileName(originalFileName)
                        .uploadFileName(uploadFileName)
                        .uploadFilePath(filePath)
                        .uploadFileUrl(uploadFileUrl)
                        .build());

            } catch (IOException e) {
                e.printStackTrace();
                log.error("Filed upload failed", e);
            }
        }

        return s3files;
    }

    /**
     * S3에 업로드된 파일 삭제
     */
    public String deleteFile(String uploadFilePath, String uuidFileName) {

        String result = "success";

        try {
            String keyName = uploadFilePath + "/" + uuidFileName; // ex) 구분/년/월/일/파일.확장자
            boolean isObjectExist = amazonS3Client.doesObjectExist(bucketName, keyName);
            if (isObjectExist) {
                amazonS3Client.deleteObject(bucketName, keyName);
            } else {
                result = "file not found";
            }
        } catch (Exception e) {
            log.debug("Delete File failed", e);
        }

        return result;
    }

    /**
     * s3에 저장될 파일의 메타데이터 설정
     * @param multipartFile : 저장될 파일
     * @return 메타데이터 정보
     */
    private ObjectMetadata getMetaDataByMultipart(MultipartFile multipartFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());
        return objectMetadata;
    }

    private String reProcessFilePath (String prefix) {
        if (prefix.charAt(prefix.length() -1) != '/') prefix += "/";
        return prefix;
    }

    /**
     * UUID 파일명 반환 
     * 확장자명 제외
     */
    public String getUUIDFileName(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        if (fileName == null) {
            fileName = IMSI_FILE_NAME;
        }

        String ext = fileName.substring(fileName.indexOf(".") + 1);
        return UUID.randomUUID().toString() + "." + ext;
    }

    /**
     * 년/월/일 폴더명 반환
     */
    private String getFolderDatePath() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", "/")+"/";
    }
}

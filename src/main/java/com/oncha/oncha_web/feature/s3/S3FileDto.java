package com.oncha.oncha_web.feature.s3;

import com.oncha.oncha_web.domain.file.model.FileInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class S3FileDto {
    private String originalFileName;
    private String uploadFileName;
    private String uploadFilePath;
    private String uploadFileUrl;

    public FileInfo toFileInfoEntity () {
        return new FileInfo(
            originalFileName,
            uploadFileName,
            uploadFileUrl
        );
    }
}

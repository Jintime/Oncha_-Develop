package com.oncha.oncha_web.domain.file.repository;

import com.oncha.oncha_web.domain.file.model.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

}

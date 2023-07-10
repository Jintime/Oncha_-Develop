package com.oncha.oncha_web.domain.file.model;

import com.oncha.oncha_web.domain.productBoard.model.ProductBoard;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "file_info")
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String originalFileName;
    @Column
    private String storedFileName;
    @Column
    private String url;

    public FileInfo (String originalFileName, String storedFileName, String url) {
        this.originalFileName = originalFileName;
        this.storedFileName = storedFileName;
        this.url = url;
    }

}

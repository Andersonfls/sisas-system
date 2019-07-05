package com.minea.sisas.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
    prefix = "file"
)
public class FileStorageProperties {
    private String uploadDir;

    public FileStorageProperties(){
    }

    public String getUploadDir() {
        if(uploadDir == null){
            uploadDir = "./pastaParaUploads";
        }
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}

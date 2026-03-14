package com.filehandlingsystem.fileHandling.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConfigurationProperties("storage")
public class FileStorageProperties {

    private static  String UPLOAD_DIR = "fileUploads/";

    public String getLocation(){
        return UPLOAD_DIR;
    }

    public void setLocation(String UPLOAD_DIR ){
        this.UPLOAD_DIR = UPLOAD_DIR;
    }
}

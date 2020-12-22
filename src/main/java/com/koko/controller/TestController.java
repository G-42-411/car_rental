package com.koko.controller;

import com.koko.util.FileUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 13629
 * @create 2020/12/21 15:15
 */
@RestController
public class TestController {
    Logger log = LoggerFactory.getLogger(TestController.class);
    @PostMapping("/upload")
    public String upload(MultipartFile file){
        String filePath = "";
        try {
            filePath = FileUploadUtils.upload(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(filePath);
        return filePath;
    }
}

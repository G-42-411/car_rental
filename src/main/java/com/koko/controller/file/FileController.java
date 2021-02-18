package com.koko.controller.file;

import com.koko.util.FileRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 13629
 * @create 2021/2/18 11:18
 */
@Slf4j
@RestController
public class FileController {

    @PostMapping("/upload")
    public String upload(MultipartFile file) {
        String filePath = "";
        try {
            filePath = FileRequestUtils.upload(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(filePath);
        return filePath;
    }
}

package com.koko.util;

import com.koko.exception.FileSizeLimitExceededException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author 13629
 * @create 2020/12/21 14:28
 */
@Component
public class FileUploadUtils {
    public Logger log = LoggerFactory.getLogger(FileUploadUtils.class);

    @Value("${file.default-storage-address}")
    public static String BASE_DIR;
    public static long DEFAULT_SIZE = 10 * 1024 * 1024;

    public static String upload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        if (fileSize >= DEFAULT_SIZE) {
            throw new FileSizeLimitExceededException();
        }
        String filePath = BASE_DIR + fileName;
        File storageFile = new File(filePath);
        file.transferTo(storageFile);
        return filePath;
    }

}

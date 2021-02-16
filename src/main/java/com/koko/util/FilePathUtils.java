package com.koko.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author 13629
 * @create 2020/12/25 16:54
 */
@Component
public class FilePathUtils {

    @Value("file.base-download-path")
    private static String baseDownloadPath;
    @Value("file.base-upload-path")
    private static String baseUploadPath;

    public static String getBaseDownloadPath() {
        return baseDownloadPath;
    }

    public static String getBaseUploadPath() {
        return baseUploadPath;
    }
}

package com.koko.util;

import com.koko.exception.FileSizeLimitExceededException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 13629
 * @create 2020/12/21 14:28
 */
@Component
public class FileRequestUtils {
    public Logger log = LoggerFactory.getLogger(FileRequestUtils.class);

    public static long DEFAULT_SIZE = 10 * 1024 * 1024;

    /**
     * 文件上传
     */
    public static String upload(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        if (fileSize >= DEFAULT_SIZE) {
            throw new FileSizeLimitExceededException();
        }
        String filePath = FilePathUtil.getBaseUploadPath() + fileName;
        File storageFile = new File(filePath);
        file.transferTo(storageFile);
        return filePath;
    }

    /**
     * 文件下载
     */
    public void download(String fileName, HttpServletResponse response) {
        FileInputStream fis = null;
        OutputStream out = null;
        try {
            if (StringUtils.isEmpty(fileName)) {
                throw new Exception("文件名错误，请检查后重试");
            }
            String filePath = FilePathUtil.getBaseDownloadPath() + fileName;
            String realFile = fileName + "-" + System.currentTimeMillis();
            response.setContentType("application/x-msdownload;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + realFile);
            File file = new File(filePath);
            fis = new FileInputStream(file);
            out = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            log.error("文件下载失败");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

package com.koko.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author 13629
 * @create 2020/12/22 15:24
 */
@RestController
public class CommonController {

    private final Logger log = LoggerFactory.getLogger(CommonController.class);
    @Value("${file.default-download-address}")
    private String DOWNLOAD_PATH;

    public void upload(String fileName, HttpServletResponse response) {
        FileInputStream fis = null;
        OutputStream out = null;
        try {
            if (StringUtils.isEmpty(fileName)) {
                throw new Exception("文件名错误，请检查后重试");
            }
            String filePath = DOWNLOAD_PATH + fileName;
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

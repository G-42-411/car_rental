package com.koko.controller.file;

import cn.ucloud.ufile.util.MimeTypeUtil;
import com.alibaba.fastjson.JSONObject;
import com.koko.dto.CommonResult;
import com.koko.pojo.Car;
import com.koko.service.CarImgService;
import com.koko.service.CarService;
import com.koko.util.FileRequestUtils;
import com.koko.util.UCloudUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author 13629
 * @create 2021/2/18 11:18
 */
@Slf4j
@RestController
public class FileController {

    @Autowired
    private UCloudUtils uCloudUtils;

    @PostMapping("/upload")
    public CommonResult upload(@RequestPart("file") MultipartFile file) {
        String uuid = UUID.randomUUID().toString();
        String fileName = file.getOriginalFilename();
        HashMap<String, String> map = new HashMap<>();
        String filePath = null;
        try {
            filePath = uCloudUtils.upload(file.getInputStream(), file.getSize(), file.getContentType(), Objects.requireNonNull(file.getOriginalFilename()));
            log.info(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("uuid", uuid);
        map.put("fileName", fileName);
        map.put("filePath", filePath);

        return CommonResult.ok(map);
    }
}

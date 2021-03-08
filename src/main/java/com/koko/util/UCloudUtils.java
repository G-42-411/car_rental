package com.koko.util;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.koko.enums.ResultStatus;
import com.koko.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author 13629
 * @create 2021/2/19 16:49
 */
@Component
public class UCloudUtils {

    @Value("${ucloud.ufile.public-key}")
    private String publicKey;

    @Value("${ucloud.ufile.private-key}")
    private String privateKey;

    @Value("${ucloud.ufile.bucketName}")
    private String bucketName;

    @Value("${ucloud.ufile.region}")
    private String region;

    @Value("${ucloud.ufile.proxySuffix}")
    private String proxySuffix;

    @Value("${ucloud.ufile.expires}")
    private Integer expires;

    @Value("${ucloud.ufile.folder}")
    private String folder;

    public String upload(InputStream stream, long contentLength, String mimeType, String fileName) {
        String generatedFileName;
        String[] filePath = fileName.split("\\.");
        if (filePath.length > 1) {
            generatedFileName = UUID.randomUUID().toString() + "." + filePath[filePath.length - 1];
        } else {
            throw new CustomizeException(ResultStatus.FILE_UPLOAD_ERROR);
        }
        try {
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
            ObjectConfig config = new ObjectConfig(region, proxySuffix);
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(stream, contentLength, mimeType)
                    .nameAs(folder + generatedFileName)
                    .toBucket(bucketName)
                    .execute();
            if (response != null && response.getRetCode() == 0) {
//                String url = UfileClient.object(objectAuthorization, config)
//                        .getDownloadUrlFromPrivateBucket(generatedFileName, bucketName, expires)
//                        .createUrl();
                return "http://dreamland.cn-bj.ufileos.com/car-rental/" + generatedFileName;
            } else {
                throw new CustomizeException(ResultStatus.FILE_UPLOAD_ERROR);
            }

        } catch (UfileClientException | UfileServerException e) {
            e.printStackTrace();
            throw new CustomizeException(ResultStatus.FILE_UPLOAD_ERROR);
        }
    }
}

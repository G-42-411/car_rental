package com.koko;

import cn.ucloud.ufile.exception.UfileFileException;
import cn.ucloud.ufile.util.MimeTypeUtil;
import com.koko.dao.UserMapper;
import com.koko.pojo.User;
import com.koko.util.UCloudUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
class CarRentalApplicationTests {

    @Autowired
    private UCloudUtils uCloudUtils;

    @Autowired
    private UserMapper userMapper;

    private Logger log = LoggerFactory.getLogger(CarRentalApplicationTests.class);

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("root");
    }

    @Test
    void fileUploadTest() throws FileNotFoundException, UfileFileException {
        File file = new File("D:/zoo-test.txt");
        String mimeType = MimeTypeUtil.getMimeType(file);
        String upload = uCloudUtils.upload(new FileInputStream(file), file.length(), mimeType, "zoo-test.txt");
        System.out.println(upload);
    }

}

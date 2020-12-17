package com.koko;

import com.koko.util.JwtHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
class CarRentalApplicationTests {

    Logger log = LoggerFactory.getLogger(CarRentalApplicationTests.class);
    @Test
    void contextLoads() {

    }

}

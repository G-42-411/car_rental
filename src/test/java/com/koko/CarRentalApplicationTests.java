package com.koko;

import cn.ucloud.ufile.exception.UfileFileException;
import cn.ucloud.ufile.util.MimeTypeUtil;
import com.alibaba.fastjson.JSONArray;
import com.koko.config.AlipayConfig;
import com.koko.dao.CarMapper;
import com.koko.dao.MenuMapper;
import com.koko.dao.UserMapper;
import com.koko.dto.CommentDto;
import com.koko.pojo.*;
import com.koko.service.RoleService;
import com.koko.service.UserService;
import com.koko.util.DateUtils;
import com.koko.util.ObjectUtils;
import com.koko.util.OrderUtils;
import com.koko.util.UCloudUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class CarRentalApplicationTests {

    @Autowired
    private UCloudUtils uCloudUtils;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AlipayConfig alipayConfig;

    private final Logger log = LoggerFactory.getLogger(CarRentalApplicationTests.class);

    @Test
    void contextLoads() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("root");
    }

    @Test
    void fileUploadTest() throws FileNotFoundException, UfileFileException {
        File file = new File("D:/珍藏壁纸/壁纸/wallhaven-qd2wpd.jpg");
        String mimeType = MimeTypeUtil.getMimeType(file);
        String upload = uCloudUtils.upload(new FileInputStream(file), file.length(), mimeType, "zoo-test.png");
        System.out.println(upload);
    }

    @Test
    void ArrayToJson() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        String s = JSONArray.toJSONString(list);
        System.out.println(s);
    }

    @Test
    void sqlTest() {
//        List<Car> cars = carMapper.selectAll();
//        System.out.println(cars.get(0));
//        boolean isSuccess = userService.addUser("Dalang", "123456");
//        System.out.println(isSuccess);
        String test = "[123,456]";
        String[] strings = StringUtils.tokenizeToStringArray(test, "[,]");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    @Test
    void dateTest() {
        Date now = new Date();
        Date date = DateUtils.transferFormat(now);
        System.out.println(date.toString());
    }

    @Test
    void utilsTest() {
        Comment comment = new Comment();
        CommentDto commentDto = new CommentDto();
        commentDto.setId(1);
        commentDto.setContent("测试");
        ObjectUtils.cloneBean(comment, commentDto);
        log.info(comment.toString());
    }

    @Test
    void mapperTest() {
//        Menu menu = new Menu();
//        menu.setName("test2");
//        menuMapper.insertSelective(menu);
//        Menu menu1 = menuMapper.selectByPrimaryKey(1);
//        System.out.println(menu1);

//        Role role = roleService.getRole(1);
//        System.out.println(role);
        Menu A = new Menu();
        A.setName("A");
        Menu B = new Menu();
        B.setName("B");
        Menu C = new Menu();
        C.setName("C");
        ArrayList<Menu> menus = new ArrayList<>();
        menus.add(A);
        menus.add(B);
        menus.add(C);
        ArrayList<Menu> children = new ArrayList<>();
        children.add(B);
        children.add(C);
        A.setChildren(children);
        ArrayList<Menu> temp = new ArrayList<>();
        temp.add(C);
        B.setChildren(temp);
        System.out.println(menus);


//        System.out.println(users);
    }

    @Test
    void compare() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(2);
//        list.add(4);
//        int len = list.size();
//        Integer month = list.get(list.size() - 1);
//        for (int i = month - 1; i >= 0 ; i--){
//            if (list.get(i).equals(month)){
//             continue;
//            }
//            list.add(i, list.get());
//
//        }
//        List< Integer> list = new ArrayList< Integer>(Collections.nCopies(60,0));
//        System.out.println(list);

//        String s = "陕西省西安市高新区";
//        int i = s.indexOf("省");
//        int j = s.indexOf("市");
//        String substring = s.substring(i+1, j+1);
//        System.out.println(substring);

//        String s = OrderUtils.checkListNumber();
//        System.out.println(s);

        System.out.println(alipayConfig.getAppId());
    }

}

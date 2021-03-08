package com.koko.service.impl;

import com.koko.dao.CarImgMapper;
import com.koko.pojo.CarImg;
import com.koko.service.CarImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/24 12:47
 */
@Slf4j
@Service
public class CarImgServiceImpl implements CarImgService {

    @Autowired
    private CarImgMapper carImgMapper;

    @Override
    public int addCarImg(Integer carId, String imgUrl) {
        return carImgMapper.addByCarId(carId, imgUrl);
    }

    @Override
    public List<String> queryBycarId(Integer carId) {
        return carImgMapper.selectByCarId(carId);
    }

    @Override
    public int delectByCarId(Integer carId) {
        return carImgMapper.deleteByCarId(carId);
    }
}

package com.koko.service;

import com.koko.pojo.CarImg;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/24 12:46
 */
public interface CarImgService {

    int addCarImg(Integer carId, String imgUrl);

    List<String> queryBycarId(Integer carId);

    int delectByCarId(Integer carId);
}

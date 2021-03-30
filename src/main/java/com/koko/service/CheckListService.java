package com.koko.service;

import com.koko.dto.CheckListDto;
import com.koko.pojo.CheckList;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/28 22:33
 */
public interface CheckListService {
    int add(CheckList checkList);

    int delete(Integer id);

    int update(CheckList checkList);

    List<CheckList> select(CheckList checkList);

    List<CheckList> selectByDto(CheckListDto checkListDto);

    List<CheckList> select();
}

package com.koko.dao;

import com.koko.dto.CheckListDto;
import com.koko.pojo.CheckList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckList record);

    int insertSelective(CheckList record);

    CheckList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckList record);

    int updateByPrimaryKey(CheckList record);

    List<CheckList> selectBySelective(CheckList record);

    List<CheckList> selectBySelectiveDto(CheckListDto record);
}
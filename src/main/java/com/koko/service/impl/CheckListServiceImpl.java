package com.koko.service.impl;

import com.koko.dao.CheckListMapper;
import com.koko.dto.CheckListDto;
import com.koko.pojo.CheckList;
import com.koko.service.CheckListService;
import com.koko.util.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 13629
 * @create 2021/2/28 22:33
 */
@Service
public class CheckListServiceImpl implements CheckListService {

    @Autowired
    private CheckListMapper checkListMapper;

    @Override
    public int add(CheckList checkList) {
        return checkListMapper.insert(checkList);
    }

    @Override
    public int delete(Integer id) {
        return checkListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(CheckList checkList) {
        return checkListMapper.updateByPrimaryKeySelective(checkList);
    }

    @Override
    public List<CheckList> select(CheckList checkList) {
        return checkListMapper.selectBySelective(checkList);
    }

    @Override
    public List<CheckList> selectByDto(CheckListDto checkListDto) {
        return checkListMapper.selectBySelectiveDto(checkListDto);
    }
}

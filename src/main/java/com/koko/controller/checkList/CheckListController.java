package com.koko.controller.checkList;

import com.koko.dto.CheckListDto;
import com.koko.dto.CommonResult;
import com.koko.pojo.CheckList;
import com.koko.service.CheckListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 13629
 * @create 2021/2/28 22:36
 */
@Slf4j
@RestController
public class CheckListController {

    @Autowired
    private CheckListService checkListService;

    @GetMapping("/getCheckList")
    public CommonResult getCheckList(){
        List<CheckList> checkLists = checkListService.select();
        return CommonResult.ok(checkLists);
    }

    @PostMapping("/getCheckListDto")
    public CommonResult getCheckListDto(@RequestBody CheckListDto checkListDto){
        List<CheckList> checkLists = checkListService.selectByDto(checkListDto);
        return CommonResult.ok(checkLists);
    }

    @GetMapping("/deleteCheckList")
    public CommonResult deleteCheckList(Integer number){
        checkListService.delete(number);
        return CommonResult.ok();
    }
    @PostMapping("/updateCheckList")
    public CommonResult updateCheckList(@RequestBody CheckList checkList){
        checkListService.update(checkList);
        return CommonResult.ok();
    }
    @PostMapping("/addCheckList")
    public CommonResult addCheckList(@RequestBody CheckList checkList){
        checkListService.add(checkList);
        return CommonResult.ok();
    }

}

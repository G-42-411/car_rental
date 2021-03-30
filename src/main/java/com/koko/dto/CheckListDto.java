package com.koko.dto;

import com.koko.pojo.CheckList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 13629
 * @create 2021/3/1 19:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckListDto {
    private String number;
    private String question;
    private String orderNumber;
    private String operator;
    private String startTime;
    private String endTime;
}

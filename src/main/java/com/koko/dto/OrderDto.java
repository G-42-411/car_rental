package com.koko.dto;

import com.koko.pojo.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 13629
 * @create 2021/3/9 17:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Order order;
    private Integer status;
}

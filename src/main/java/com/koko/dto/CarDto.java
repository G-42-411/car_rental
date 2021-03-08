package com.koko.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 13629
 * @create 2021/3/6 21:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarDto {
    private Integer id;

    private String number;

    private String type;

    private String brand;

    private String name;

    private String colour;

    private Integer price;

    private Integer rentPrice;

    private Integer deposit;

    private Integer isRenting;

    private String description;

    private Integer storefrontId;
}

package com.koko.dto;

import com.koko.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 13629
 * @create 2021/2/26 14:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleDto {
    private User user;
    private Integer roleId;
}

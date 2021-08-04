package com.cykj.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author guoquansen
 * @date 2021/8/5 3:54 上午
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoPwd {

    private Integer userId;
    private String userPassword;
    private String password;
}

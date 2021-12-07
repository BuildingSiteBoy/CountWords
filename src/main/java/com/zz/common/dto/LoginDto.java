package com.zz.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author zz
 */
@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "账户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}

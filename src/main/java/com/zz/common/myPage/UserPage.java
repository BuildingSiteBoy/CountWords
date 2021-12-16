package com.zz.common.myPage;

import com.zz.entity.AdminUser;
import lombok.Data;

import java.util.List;

/**
 * @author zz
 */
@Data
public class UserPage {
    private int pages;
    private int total;
    private List<AdminUser> users;
}

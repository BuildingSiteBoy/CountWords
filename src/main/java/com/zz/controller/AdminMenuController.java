package com.zz.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
@RestController
@RequestMapping("/admin-menu")
public class AdminMenuController {

    public void test() {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("aaa");
        StringBuilder sb = new StringBuilder();
        sb.append("bbb");
    }

}

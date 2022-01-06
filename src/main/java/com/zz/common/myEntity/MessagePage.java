package com.zz.common.myEntity;

import com.zz.entity.FunListen;
import com.zz.entity.FunMessage;
import lombok.Data;

import java.util.List;

/**
 * @author zz
 */
@Data
public class MessagePage {
    private int pages;
    private int total;
    private List<FunMessage> messages;
}

package com.zz.common.myEntity;

import com.zz.entity.FunBook;
import com.zz.entity.FunListen;
import lombok.Data;

import java.util.List;

/**
 * @author zz
 */
@Data
public class ListenPage {
    private int pages;
    private int total;
    private List<FunListen> listens;
}

package com.zz.common.myEntity;

import com.zz.entity.FunListen;
import com.zz.entity.FunWord;
import lombok.Data;

import java.util.List;

/**
 * @author zz
 */
@Data
public class WordPage {
    private int pages;
    private int total;
    private List<FunWord> words;
}

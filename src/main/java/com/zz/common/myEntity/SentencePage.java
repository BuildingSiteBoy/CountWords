package com.zz.common.myEntity;

import com.zz.entity.FunListen;
import com.zz.entity.FunSentence;
import lombok.Data;

import java.util.List;

/**
 * @author zz
 */
@Data
public class SentencePage {
    private int pages;
    private int total;
    private List<FunSentence> sentences;
}

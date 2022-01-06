package com.zz.common.myEntity;

import com.zz.entity.FunBook;
import lombok.Data;

import java.util.List;

/**
 * @author zz
 */
@Data
public class BookPage {
    private int pages;
    private int total;
    private List<FunBook> books;
}

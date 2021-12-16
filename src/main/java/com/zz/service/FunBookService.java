package com.zz.service;

import com.zz.entity.FunBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
public interface FunBookService extends IService<FunBook> {
    /**
     * 通过书名模糊查询书籍
     * @param title 书名
     * @return list
     */
    List<FunBook> listLikeByTitle(String title);

    /**
     * 通过作者模糊查询书籍
     * @param author 作者
     * @return list
     */
    List<FunBook> listLikeByAuthor(String author);

    /**
     * 通过出版社查询书籍
     * @param press 出版社
     * @return list
     */
    List<FunBook> listLikeByPress(String press);

    /**
     * 根据书名判断书籍是否存在
     * @param title 书名
     * @return true: 存在 false: 不存在
     */
    boolean isExistByTitle(String title);

    /**
     * 插入书籍
     * @param book 被插入的书籍
     * @return 0：单词已存在 1：插入成功 2：插入失败
     */
    int insertBook(FunBook book);
}

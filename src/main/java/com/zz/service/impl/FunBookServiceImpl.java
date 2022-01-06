package com.zz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.common.myEntity.BookPage;
import com.zz.entity.FunBook;
import com.zz.mapper.FunBookMapper;
import com.zz.service.FunBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class FunBookServiceImpl extends ServiceImpl<FunBookMapper, FunBook> implements FunBookService {
    @Autowired
    FunBookMapper bookMapper;

    @Override
    public BookPage listBooksByPage(int size, int page) {
        IPage<FunBook> iPage = page(new Page<>(page, size));

        BookPage bookPage = new BookPage();
        bookPage.setPages((int)iPage.getPages());
        bookPage.setTotal((int)iPage.getTotal());
        bookPage.setBooks(iPage.getRecords());

        return bookPage;
    }

    @Override
    public List<FunBook> listLikeByTitle(String title) {
        return bookMapper.selectList(
                new QueryWrapper<FunBook>().like("title", title)
        );
    }

    @Override
    public List<FunBook> listLikeByAuthor(String author) {
        return bookMapper.selectList(
                new QueryWrapper<FunBook>().like("author", author)
        );
    }

    @Override
    public List<FunBook> listLikeByPress(String press) {
        return bookMapper.selectList(
                new QueryWrapper<FunBook>().eq("press", press)
        );
    }

    @Override
    public boolean isExistByTitle(String title) {
        return null != bookMapper.selectOne(
                new QueryWrapper<FunBook>().eq("title", title)
        );
    }

    @Override
    public int insertBook(FunBook book) {
        if (isExistByTitle(book.getTitle())) {
            return 0;
        } else {
            if (bookMapper.insert(book) == 1) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}

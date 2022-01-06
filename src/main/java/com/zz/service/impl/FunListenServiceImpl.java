package com.zz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.common.myEntity.BookPage;
import com.zz.common.myEntity.ListenPage;
import com.zz.entity.FunBook;
import com.zz.entity.FunListen;
import com.zz.mapper.FunListenMapper;
import com.zz.service.FunListenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
@Service
public class FunListenServiceImpl extends ServiceImpl<FunListenMapper, FunListen> implements FunListenService {

    @Override
    public ListenPage listListensByPage(int size, int page) {
        IPage<FunListen> iPage = page(new Page<>(page, size));

        ListenPage listenPage = new ListenPage();
        listenPage.setPages((int)iPage.getPages());
        listenPage.setTotal((int)iPage.getTotal());
        listenPage.setListens(iPage.getRecords());

        return listenPage;
    }
}

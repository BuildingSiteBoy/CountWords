package com.zz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zz.common.myEntity.BookPage;
import com.zz.common.myEntity.MessagePage;
import com.zz.entity.FunBook;
import com.zz.entity.FunMessage;
import com.zz.mapper.FunMessageMapper;
import com.zz.service.FunMessageService;
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
public class FunMessageServiceImpl extends ServiceImpl<FunMessageMapper, FunMessage> implements FunMessageService {

    @Override
    public MessagePage listMessagesByPage(int size, int page) {
        IPage<FunMessage> iPage = page(new Page<>(page, size));

        MessagePage messagePage = new MessagePage();
        messagePage.setPages((int)iPage.getPages());
        messagePage.setTotal((int)iPage.getTotal());
        messagePage.setMessages(iPage.getRecords());

        return messagePage;
    }
}

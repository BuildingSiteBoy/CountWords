package com.zz.service;

import com.zz.common.myEntity.BookPage;
import com.zz.common.myEntity.MessagePage;
import com.zz.entity.FunMessage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
public interface FunMessageService extends IService<FunMessage> {
    /**
     * 信息的分页查询
     * @param size 页面大小
     * @param page 当前页
     * @return list
     */
    MessagePage listMessagesByPage(int size, int page);
}

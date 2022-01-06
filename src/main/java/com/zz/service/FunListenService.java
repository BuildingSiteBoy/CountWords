package com.zz.service;

import com.zz.common.myEntity.ListenPage;
import com.zz.entity.FunListen;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
public interface FunListenService extends IService<FunListen> {
    /**
     * 听力的分页查询
     * @param size 页面大小
     * @param page 当前页
     * @return list
     */
    ListenPage listListensByPage(int size, int page);
}

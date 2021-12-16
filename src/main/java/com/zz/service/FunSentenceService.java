package com.zz.service;

import com.zz.entity.FunSentence;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-11-25
 */
public interface FunSentenceService extends IService<FunSentence> {

    /**
     * 获取每日一句
     * @return FunSentence
     */
    FunSentence getOneSentence();

    /**
     * 根据内容判断是否存在
     * @param content 内容
     * @return true: 存在 false: 不存在
     */
    boolean isExistByContent(String content);
}

package com.zz.service;

import com.zz.entity.FunWord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
public interface FunWordService extends IService<FunWord> {

    /**
     * 通过输入的单词模糊查询所有单词
     * @param word 输入单词
     * @return list
     */
    List<FunWord> listLikeByWord(String word);

    /**
     * 通过输入的单词词义模糊查询
     * @param means 单词词义
     * @return list
     */
    List<FunWord> listLikeByMeans(String means);

    /**
     * 根据单词等级查询单词
     * @param grade 单词等级
     * @return list
     */
    List<FunWord> listLikeByGrade(int grade);

    /**
     * 根据单词名判断单词是否存在
     * @param word 单词
     * @return true: 存在 false: 不存在
     */
    boolean isExistByWord(String word);

    /**
     * 插入单词
     * @param word 被插入的单词
     * @return 0：单词已存在 1：插入成功 2：插入失败
     */
    int insertWord(FunWord word);
}

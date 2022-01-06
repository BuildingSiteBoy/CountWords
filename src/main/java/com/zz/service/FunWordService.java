package com.zz.service;

import com.zz.common.myEntity.BookPage;
import com.zz.common.myEntity.WordInfo;
import com.zz.common.myEntity.WordPage;
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
     * 单词的分页查询
     * @param size 页面大小
     * @param page 当前页
     * @return page
     */
    WordPage listWordsByPage(int size, int page);

    /**
     * 通过输入的单词模糊查询所有单词
     * @param word 输入单词
     * @return list
     */
    List<FunWord> listLikeByWord(String word);

    /**
     * 根据内容分页模糊查询
     * @param word 输入单词
     * @param size 页面大小
     * @param page 当前页
     * @return page
     */
    WordPage listLikeByWordPage(String word, int size, int page);

    /**
     * 通过输入的单词词义模糊查询
     * @param means 单词词义
     * @return list
     */
    List<FunWord> listLikeByMeans(String means);

    /**
     * 根据内容分页模糊查询
     * @param means 输入单词
     * @param size 页面大小
     * @param page 当前页
     * @return page
     */
    WordPage listLikeByMeansPage(String means, int size, int page);

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

    /**
     * ----------------------------------前台任务-------------------------------
     * 根据单词等级查询学习信息
     * @param grade 单词等级
     * @return WordInfo
     */
    WordInfo findInfoByGrade(int grade);

    /**
     * 根据等级顺序学习单词接口
     * @param grade 单词等级
     * @return FunWord
     */
    FunWord findWordByOrder(int grade);

    /**
     * 根据等级乱序学习单词接口
     * @param grade 单词等级
     * @return FunWord
     */
    FunWord findWordDisorder(int grade);

    /**
     * 根据等级重新学习单词：令study = 0, remember = 0
     * @param grade 单词等级
     * @return int
     */
    int restartByGrade(Integer grade);

    /**
     * 学习单词：令study = 1
     * @param word 被学习的单词
     * @return int
     */
    int studyWord(FunWord word);

    /**
     * 记住单词：令remember = 1
     * @param word 被记住的单词
     * @return int
     */
    int rememberWord(FunWord word);

    /**
     * 收藏/取消收藏单词
     * @param word 被收藏的单词
     * @return int
     */
    int collectionWord(FunWord word);

    /**
     * 查看收藏的单词
     * @return
     */
    List<FunWord> listCollectedWords();
}

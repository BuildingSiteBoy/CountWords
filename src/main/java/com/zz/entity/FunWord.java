package com.zz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author zZeng
 * @since 2021-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FunWord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单词id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 难度等级
     */
    private Integer grade;

    /**
     * 单词
     */
    private String word;

    /**
     * 读音
     */
    private String audio;

    /**
     * 词性
     */
    private String part;

    /**
     * 词义
     */
    private String means;

    /**
     * 例句
     */
    private String example;

    /**
     * 解释
     */
    private String trans;

    /**
     * 是否学习
     */
    private Integer study;

    /**
     * 是否记得
     */
    private Integer remember;

    /**
     * 是否收藏
     */
    private Integer collection;


}

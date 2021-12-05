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
 * @since 2021-11-25
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

    private String name;

    private String audio;

    private String explain;

    private String example;

    private Integer grade;

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

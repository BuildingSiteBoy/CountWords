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
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FunSentence implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章句子id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    private String trans;

    private Integer listen;


}

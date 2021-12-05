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
public class TestType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩类型id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;


}

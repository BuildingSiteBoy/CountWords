package com.zz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
public class AdminMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String path;

    private String name;

    private String nameZh;

    private String iconCls;

    private String component;

    private Integer parentId;

    @TableField(exist = false)
    private List<AdminMenu> children;
}

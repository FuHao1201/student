package com.project.student.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 *  学院实体类
 * </p>
 *
 * @author FuHao
 * @since 2021-02-27
 */
@Data
public class MajorInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学院id
     */
    @TableField
    private Integer collegeId;

    /**
     * 专业名称
     */
    @TableField
    private String name;

    /**
     * 创建时间
     */
    @TableField
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    @TableField
    private Integer createBy;

    /**
     * 最后一次修改时间
     */
    @TableField
    private LocalDateTime lastUpdateTime;

    /**
     * 最后一次修改人id
     */
    @TableField
    private Integer lastUpdateBy;

    /************************* 手动加入字段 *************************/

    /**
     * 学院名称
     */
    @TableField(exist=false)
    private String collegeName;

    /**
     * 入学年份
     */
    @TableField(exist=false)
    private String year;
}

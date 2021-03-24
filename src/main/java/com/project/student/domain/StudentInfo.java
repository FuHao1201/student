package com.project.student.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 *  学生信息实体类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
@Data
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 专业id
     */
    private Integer majorId;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学号
     */
    private String schoolNumber;

    /**
     * 出生日期
     */
    private LocalDate birthday;

    /**
     * 身份证号码
     */
    private String identity;

    /**
     * 籍贯
     */
    private String place;

    /**
     * 民族
     */
    private String nation;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    private Integer createBy;

    /**
     * 最后一次修改时间
     */
    private LocalDateTime lastUpdateTime;

    /**
     * 最后一次修改人id
     */
    private Integer lastUpdateBy;

    /**
     * 专业名称
     */
    @TableField(exist = false)
    private String majorName;

    /**
     * 学院名称
     */
    @TableField(exist = false)
    private String collegeName;

    /**
     * 入学年份
     */
    @TableField(exist = false)
    private String year;
}

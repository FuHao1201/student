package com.project.student.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
public class CollegeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学院名称
     */
    private String name;

    /**
     * 入学年份
     */
    private String year;

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
}

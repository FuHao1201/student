package com.project.student.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
/**
 * <p>
 *  用户信息实体类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 是否启用（Y:启用N:禁用）
     */
    private String enableFlag;

    /**
     * 是否删除（Y:删除N:未删除）
     */
    @TableLogic
    private String deleteFlag;

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

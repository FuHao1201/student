package com.project.student.dto;

import com.project.student.common.PageModel;
import com.project.student.domain.UserInfo;
import lombok.Data;

/**
 * <p>
 *  用户信息分页实体类
 * </p>
 *
 * @author FuHao
 * @since 2021-02-27
 */
@Data
public class UserInfoPage extends PageModel<UserInfo> {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;
}

package com.project.student.dto;

import com.project.student.common.PageModel;
import com.project.student.domain.UserInfo;
import lombok.Data;

/**
 * <p>
 *  用户信息分页实体类
 * </p>
 *
 * @author YL
 * @since 2021-02-27
 */
@Data
public class UserInfoPage extends PageModel<UserInfo> {

    private String loginName;

}

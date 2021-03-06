package com.project.student.dto;

import com.project.student.common.PageModel;
import com.project.student.domain.UserInfo;
import lombok.Data;

@Data
public class UserInfoPage extends PageModel<UserInfo> {

    private String loginName;

}

package com.project.student.service;

import com.project.student.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.student.dto.UserInfoPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public interface UserInfoService extends IService<UserInfo> {

    /**
     * 用户列表分页
     * @param userInfoPage 参数
     * @return 分页列表
     */
    UserInfoPage listPagedUser(UserInfoPage userInfoPage);
}

package com.project.student.service;

import com.project.student.domain.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 登录
     * @param loginName 登录名
     * @return
     */
    UserInfo login(String loginName);
}

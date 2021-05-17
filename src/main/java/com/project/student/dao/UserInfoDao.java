package com.project.student.dao;

import com.project.student.domain.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.student.dto.UserInfoPage;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FuHao
 * @since 2021-02-27
 */
public interface UserInfoDao extends BaseMapper<UserInfo> {

    /**
     * 用户列表分页
     * @param userInfoPage 参数
     * @return 分页列表
     */
    UserInfoPage listPagedUser(UserInfoPage userInfoPage);
}

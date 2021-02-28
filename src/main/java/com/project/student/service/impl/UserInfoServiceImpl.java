package com.project.student.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.domain.UserInfo;
import com.project.student.dao.UserInfoDao;
import com.project.student.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, UserInfo> implements UserInfoService {

    @Override
    public UserInfo login(String loginName) {
        return this.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getLoginName,loginName));
    }
}

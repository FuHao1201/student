package com.project.student.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.project.student.common.CurrentUser;
import com.project.student.common.UserContext;
import com.project.student.domain.UserInfo;
import com.project.student.dao.UserInfoDao;
import com.project.student.dto.UserInfoPage;
import com.project.student.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
    public UserInfoPage listPagedUser(UserInfoPage userInfoPage) {
        return getBaseMapper().listPagedUser(userInfoPage);
    }

    @Override
    public void saveOrUpdateHead(String avatar) {
        UserInfo userInfo = CurrentUser.getUser().getUserInfo();
        userInfo.setAvatar(avatar);
        this.saveOrUpdate(userInfo);
        UserContext userContextNew = new UserContext();
        userContextNew.setUserInfo(userInfo);
        UserContext userContext = (UserContext) SecurityUtils.getSubject().getPrincipal();
        if (userContext != null || ObjectUtil.isNotEmpty(userContext.getUserInfo().getId())){
            BeanUtils.copyProperties(userContextNew, userContext);
        }
    }
}

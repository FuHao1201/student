package com.project.student.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.project.student.common.BooleanEnum;
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

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuHao
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
        updateLoginUser(userInfo);
    }

    @Override
    public void changePass(UserInfo userInfo) {
        userInfo.setLastUpdateBy(CurrentUser.getUser().getUserInfo().getId());
        userInfo.setLastUpdateTime(LocalDateTime.now());
        saveOrUpdate(userInfo);
        updateLoginUser(userInfo);
    }

    @Override
    public boolean saveOrUpdateUserInfo(UserInfo userInfo) {
        if (ObjectUtil.isEmpty(userInfo.getId())){
            userInfo.setPassword(SecureUtil.md5(userInfo.getPassword()));
            userInfo.setCreateBy(CurrentUser.getUser().getUserInfo().getId());
            userInfo.setCreateTime(LocalDateTime.now());
            userInfo.setEnableFlag(BooleanEnum.YES.getCode());
            userInfo.setDeleteFlag(BooleanEnum.NO.getCode());
        }else {
            userInfo.setLastUpdateBy(CurrentUser.getUser().getUserInfo().getId());
            userInfo.setLastUpdateTime(LocalDateTime.now());
        }
        saveOrUpdate(userInfo);
        //如果修改的是当前登录人的信息，则需要更新登录信息
        if (ObjectUtil.equal(userInfo.getId(),CurrentUser.getUser().getUserInfo().getId())){
            UserInfo user = this.getById(userInfo.getId());
            updateLoginUser(user);
        }
        return true;
    }

    /**
     * 修改登录人信息
     * @param userInfo 登录人信息
     */
    public void updateLoginUser(UserInfo userInfo) {
        //修改登录人信息
        UserContext userContextNew = new UserContext();
        userContextNew.setUserInfo(userInfo);
        UserContext userContext = (UserContext) SecurityUtils.getSubject().getPrincipal();
        if (userContext != null || ObjectUtil.isNotEmpty(userContext.getUserInfo().getId())){
            BeanUtils.copyProperties(userContextNew, userContext);
        }
    }
}

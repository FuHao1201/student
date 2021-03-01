package com.project.student.common;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.domain.UserInfo;
import com.project.student.service.UserInfoService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  用户认证及授权
 * </p>
 *
 * @author YL
 * @since 2021-02-27
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        UserContext userContext = new UserContext();
        //查出是否有此用户
        UserInfo user = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getLoginName,token.getUsername()).eq(UserInfo::getEnableFlag,1).eq(UserInfo::getDeleteFlag,0));
        if (ObjectUtil.isEmpty(user)) {
            // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
            throw new UnknownAccountException();
        }
        userContext.setUserInfo(user);
        return new SimpleAuthenticationInfo(userContext, user.getPassword(), getName());
    }
}

package com.project.student.common;

import cn.hutool.core.util.ObjectUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.UnknownSessionException;

/**
 * <p>
 *  获取上下文
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public class CurrentUser {

    /**
     * 获取登录人信息
     * @return
     */
    public static UserContext getUser(){
        Object principal = SecurityUtils.getSubject().getPrincipal();
        if (principal instanceof UserContext){
            return (UserContext) principal;
        }
        return new UserContext();
    }
}

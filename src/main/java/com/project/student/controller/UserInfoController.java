package com.project.student.controller;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.common.BaseController;
import com.project.student.common.GlobalConstants;
import com.project.student.common.JsonResult;
import com.project.student.domain.UserInfo;
import com.project.student.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 跳转主页
     * @return 首页
     */
    @GetMapping("/main")
    public ModelAndView main() {
        return view("main");
    }

    /**
     * 跳转主页
     * @return 首页
     */
    @GetMapping("/home")
    public ModelAndView home() {
        return view("home");
    }

    /**
     * 登录
     * @param user 查询参数
     * @return json
     */
    @PostMapping("/login")
    public JsonResult<UserInfo> login(UserInfo user) {
        Subject subject = SecurityUtils.getSubject();
        user.setPassword(SecureUtil.md5(user.getPassword()));
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
            user.getLoginName(),
            user.getPassword()
        );
        try {
            subject.login(usernamePasswordToken);
            return jr(GlobalConstants.SUCCESS,"登录成功");
        } catch (UnknownAccountException e) {
            return jr(GlobalConstants.ERROR,"用户名错误");
        } catch (IncorrectCredentialsException e) {
            return jr(GlobalConstants.ERROR,"密码错误");
        }
    }

    /**
     * 获取当前登录人信息
     * @return json
     */
    @GetMapping("/getLoginUser")
    public JsonResult<UserInfo> getLoginUser() {
        String loginName = (String) SecurityUtils.getSubject().getPrincipal();
        UserInfo userInfo = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getLoginName,loginName).eq(UserInfo::getEnableFlag,1).eq(UserInfo::getDeleteFlag,0));
        if (ObjectUtil.isNotEmpty(userInfo)){
            return jr(GlobalConstants.SUCCESS,"查询成功",userInfo);
        }
        return jr(GlobalConstants.ERROR,"查询失败");
    }

    /**
     * 退出
     * @return json
     */
    @GetMapping("/layout")
    public ModelAndView layout() {
        SecurityUtils.getSubject().logout();
        return new ModelAndView("/");
    }
}

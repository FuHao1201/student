package com.project.student.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.project.student.common.*;
import com.project.student.domain.UserInfo;
import com.project.student.dto.UserInfoPage;
import com.project.student.service.UserInfoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        UserContext userContext = CurrentUser.getUser();
        if (ObjectUtil.isNotEmpty(userContext.getUserInfo())){
            return jr(GlobalConstants.SUCCESS,"查询成功",userContext.getUserInfo());
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
        return new ModelAndView("index");
    }

    /**
     * 跳转用户有关的界面
     * @return 界面
     */
    @GetMapping("/listPagedUserPage")
    public ModelAndView listPagedUserPage() {
        return view("user/listPagedUser");
    }

    /**
     * 跳转用户有关的界面
     * @return 界面
     */
    @GetMapping("/listPagedUser")
    public JsonResult<UserInfoPage> listPagedUser(UserInfoPage userInfoPage) {
        UserInfoPage page = userInfoService.listPagedUser(userInfoPage);
        if (CollUtil.isEmpty(page.getRecords())){
            return jr(GlobalConstants.ERROR,"查询失败");
        }
        return jr("0","查询成功",page);
    }
}

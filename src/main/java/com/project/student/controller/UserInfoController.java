package com.project.student.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.common.*;
import com.project.student.domain.UserInfo;
import com.project.student.dto.UserInfoPage;
import com.project.student.service.UserInfoService;
import com.sun.istack.internal.NotNull;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;

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
            return jr(GlobalConstants.ERROR,"登录名错误");
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
     * @return
     */
    @GetMapping("/layout")
    public ModelAndView layout() {
        SecurityUtils.getSubject().logout();
        return new ModelAndView("index");
    }

    /**
     * 用户列表页面
     * @return 界面
     */
    @GetMapping("/listPagedUserPage")
    public ModelAndView listPagedUserPage() {
        return view("user/listPagedUser");
    }
    /**
     * 修改用户页面
     * @return 界面
     */
    @GetMapping("/editUserPage")
    public ModelAndView userUpdata(int id) {

        return view("user/userUpdata");
    }

    /**
     * 用户列表
     * @param userInfoPage 分页参数
     * @return 分页
     */
    @GetMapping("/listPagedUser")
    public JsonResult<UserInfoPage> listPagedUser(UserInfoPage userInfoPage) {
        UserInfoPage page = userInfoService.listPagedUser(userInfoPage);
        if (CollUtil.isEmpty(page.getRecords())){
            return jr(GlobalConstants.ERROR,"查询失败");
        }
        return jr("0","查询成功",page);
    }

    /**
     * 个人资料页面
     * @return
     */
    @GetMapping("/myInfoPage")
    public ModelAndView myInfoPage(ModelMap model) {
        model.put("user", CurrentUser.getUser().getUserInfo());
        return view("user/myInfo", model);
    }

    /**
     * 上传头像
     * @return
     */
    @PostMapping("/uploadHead")
    public JsonResult<String> uploadHead(HttpServletRequest request, MultipartFile[] file) {
        //获得文件名字
        String fileName = file[0].getOriginalFilename();
        try {
            //上传目录地址
            String uploadDir= ResourceUtils.getURL("classpath:").getPath()+"static/upload/image/";
            //如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if(!dir.exists()) {
                dir.mkdir();
            }
            executeUpload(uploadDir, file[0]);
        } catch (Exception e) {
            return jr(GlobalConstants.ERROR,"上传失败");
        }
        // 将头像路径保存到数据库
        String url= "/upload/image/"+fileName;
        userInfoService.saveOrUpdateHead(url);
        return jr(GlobalConstants.SUCCESS,"上传成功", url);
    }

    /**
     * 修改个人信息
     * @return
     */
    @PostMapping("/updateMyInfo")
    @ResponseBody
    public JsonResult updateMyInfo(UserInfo userInfo) {
        if (!StrUtil.equals(userInfo.getLoginName(),CurrentUser.getUser().getUserInfo().getLoginName())){
            //验证登录名是否已被使用
            UserInfo user = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getLoginName, userInfo.getLoginName()).eq(UserInfo::getDeleteFlag,BooleanEnum.NO.getCode()));
            if (user != null){
                return jr(GlobalConstants.ERROR,"登录名已被使用");
            }
        }
        userInfo.setLastUpdateBy(CurrentUser.getUser().getUserInfo().getId());
        userInfo.setLastUpdateTime(LocalDateTime.now());
        if (userInfoService.saveOrUpdate(userInfo)){
            //修改登录人信息
            UserContext userContextNew = new UserContext();
            UserInfo userInfoNew = CurrentUser.getUser().getUserInfo();
            userInfoNew.setLoginName(userInfo.getLoginName());
            userInfoNew.setUserName(userInfo.getUserName());
            userContextNew.setUserInfo(userInfoNew);
            UserContext userContext = (UserContext) SecurityUtils.getSubject().getPrincipal();
            if (userContext != null || ObjectUtil.isNotEmpty(userContext.getUserInfo().getId())){
                BeanUtils.copyProperties(userContextNew, userContext);
            }
            return jr(GlobalConstants.SUCCESS,"修改成功");
        }
        return jr(GlobalConstants.ERROR,"修改失败");
    }

    /**
     *
     * @param ids
     * @return
     */
    @PostMapping("/removeUser")
    public JsonResult removeUser(@NotNull @RequestParam(value = "ids[]") String[] ids){
        if(userInfoService.removeByIds(Arrays.asList(ids))) {
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }
        return jr(GlobalConstants.ERROR,"删除失败");
    }

}

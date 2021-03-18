package com.project.student.controller;


import cn.hutool.core.collection.CollUtil;
import com.project.student.common.BaseController;
import com.project.student.common.GlobalConstants;
import com.project.student.common.JsonResult;
import com.project.student.dto.StudentInfoPage;
import com.project.student.dto.UserInfoPage;
import com.project.student.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/studentInfo")
public class StudentInfoController extends BaseController {

    @Autowired
    private StudentInfoService studentInfoService;

    /**
     * 用户列表页面
     * @return 界面
     */
    @GetMapping("/listPagedStudentPage")
    public ModelAndView listPagedStudentPage() {
        return view("student/listPagedStudent");
    }


    /**
     * 用户列表页面
     * @return 界面
     */
    @GetMapping("/listPagedStudent")
    public JsonResult<StudentInfoPage> listPagedStudent(StudentInfoPage studentInfoPage) {
        StudentInfoPage page = studentInfoService.listPagedStudent(studentInfoPage);
        if (CollUtil.isEmpty(page.getRecords())){
            return jr(GlobalConstants.ERROR,"查询失败");
        }
        return jr("0","查询成功",page);
    }
}

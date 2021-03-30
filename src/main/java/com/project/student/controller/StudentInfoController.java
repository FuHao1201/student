package com.project.student.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.common.BaseController;
import com.project.student.common.CurrentUser;
import com.project.student.common.GlobalConstants;
import com.project.student.common.JsonResult;
import com.project.student.domain.CollegeInfo;
import com.project.student.domain.MajorInfo;
import com.project.student.domain.StudentInfo;
import com.project.student.dto.StudentInfoPage;
import com.project.student.dto.UserInfoPage;
import com.project.student.service.CollegeInfoService;
import com.project.student.service.MajorInfoService;
import com.project.student.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
@RequestMapping("/studentInfo")
public class StudentInfoController extends BaseController {

    @Autowired
    private StudentInfoService studentInfoService;

    @Autowired
    private CollegeInfoService collegeInfoService;

    @Autowired
    private MajorInfoService majorInfoService;

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

    /**
     * 编辑学生页跳转
     * @param model
     * @param id 学生id
     * @return
     */
    @GetMapping("/addOrUpdateStudentPage")
    public ModelAndView addOrUpdateMajorPage(ModelMap model, Integer id) {
        List<CollegeInfo> colleges = new ArrayList<>();
        List<MajorInfo> majorInfos = new ArrayList<>();
        StudentInfo studentInfo = new StudentInfo();
        if (ObjectUtil.isNotEmpty(id)){
            studentInfo = studentInfoService.getStudentById(id);
            if (studentInfo != null){
                MajorInfo majorInfo = majorInfoService.getById(studentInfo.getMajorId());
                CollegeInfo collegeInfo = collegeInfoService.getById(majorInfo.getCollegeId());
                if (collegeInfo != null){
                    majorInfo.setCollegeName(collegeInfo.getName());
                    majorInfo.setYear(collegeInfo.getYear());
                    colleges = collegeInfoService.list(new LambdaQueryWrapper<CollegeInfo>().eq(CollegeInfo::getYear,collegeInfo.getYear()));
                    if (majorInfo != null){
                        majorInfos = majorInfoService.list(new LambdaQueryWrapper<MajorInfo>().eq(MajorInfo::getCollegeId,majorInfo.getCollegeId()));
                    }
                }
            }
        }
        model.put("student", studentInfo);
        model.put("years", collegeInfoService.getAllYear());
        model.put("colleges", colleges);
        model.put("majors", majorInfos);
        return view("student/addOrUpdateStudent", model);
    }

    /**
     * 新增/修改学生信息
     * @param studentInfo 学生信息
     * @return
     */
    @PostMapping("/addOrUpdateStudent")
    @ResponseBody
    public JsonResult addOrUpdateStudent(StudentInfo studentInfo) {
        if (ObjectUtil.isNotEmpty(studentInfo.getId())){
            studentInfo.setLastUpdateBy(CurrentUser.getUser().getUserInfo().getId());
            studentInfo.setLastUpdateTime(LocalDateTime.now());
        }else {
            studentInfo.setCreateBy(CurrentUser.getUser().getUserInfo().getId());
            studentInfo.setCreateTime(LocalDateTime.now());
        }
        if (studentInfoService.saveOrUpdate(studentInfo)){
            return jr(GlobalConstants.SUCCESS,"编辑学生成功");
        }
        return jr(GlobalConstants.ERROR,"编辑学生失败");
    }

    /**
     * 验证学号是否被使用
     * @param schoolNumber 学号
     * @return
     */
    @PostMapping("/compareSchoolNum")
    @ResponseBody
    public JsonResult compareSchoolNum(String schoolNumber) {
        StudentInfo studentInfo = studentInfoService.getOne(new LambdaQueryWrapper<StudentInfo>().eq(StudentInfo::getSchoolNumber,schoolNumber));
        if (studentInfo == null){
            return jr(GlobalConstants.SUCCESS,"");
        }
        return jr(GlobalConstants.ERROR,"");
    }
}

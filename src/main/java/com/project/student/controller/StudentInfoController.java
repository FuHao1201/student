package com.project.student.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.common.BaseController;
import com.project.student.common.GlobalConstants;
import com.project.student.common.JsonResult;
import com.project.student.domain.CollegeInfo;
import com.project.student.domain.MajorInfo;
import com.project.student.dto.StudentInfoPage;
import com.project.student.dto.UserInfoPage;
import com.project.student.service.CollegeInfoService;
import com.project.student.service.MajorInfoService;
import com.project.student.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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
     * 编辑专业页跳转
     * @param model
     * @param id 学院id
     * @return
     */
    @GetMapping("/addOrUpdateMajorPage")
    public ModelAndView addOrUpdateMajorPage(ModelMap model, Integer id) {
        List<CollegeInfo> colleges = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(id)){
            MajorInfo majorInfo = majorInfoService.getById(id);
            CollegeInfo collegeInfo = collegeInfoService.getById(majorInfo.getCollegeId());
            if (collegeInfo != null){
                majorInfo.setCollegeName(collegeInfo.getName());
                majorInfo.setYear(collegeInfo.getYear());
                colleges = collegeInfoService.list(new LambdaQueryWrapper<CollegeInfo>().eq(CollegeInfo::getYear,collegeInfo.getYear()));
            }
            model.put("major", majorInfo);
        }else {
            model.put("major", new MajorInfo());
        }
        model.put("years", collegeInfoService.getAllYear());
        model.put("colleges", colleges);
        return view("student/addOrUpdateMajor", model);
    }
}

package com.project.student.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.project.student.common.BaseController;
import com.project.student.common.CurrentUser;
import com.project.student.common.GlobalConstants;
import com.project.student.common.JsonResult;
import com.project.student.domain.CollegeInfo;
import com.project.student.domain.MajorInfo;
import com.project.student.dto.CollegeInfoPage;
import com.project.student.dto.MajorInfoPage;
import com.project.student.service.CollegeInfoService;
import com.project.student.service.MajorInfoService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/majorInfo")
public class MajorInfoController extends BaseController {

    @Autowired
    private MajorInfoService majorInfoService;

    @Autowired
    private CollegeInfoService collegeInfoService;

    /**
     * 专业列表页面
     * @return 界面
     */
    @GetMapping("/listPagedMajorPage")
    public ModelAndView listPagedMajorPage() {
        return view("major/listPagedMajor");
    }

    /**
     * 专业列表
     * collegeInfoPage 查询参数
     * @return
     */
    @GetMapping("/listPagedMajor")
    public JsonResult<MajorInfoPage> listPagedMajor(MajorInfoPage majorInfoPage) {
        MajorInfoPage page = majorInfoService.listPagedMajor(majorInfoPage);
        if (CollUtil.isEmpty(page.getRecords())){
            return jr(GlobalConstants.ERROR,"查询失败");
        }
        return jr("0","查询成功",page);
    }

    /**
     * 删除专业
     * @param ids
     * @return
     */
    @PostMapping("/removeMajor")
    public JsonResult removeMajor(@NotNull @RequestParam(value = "ids[]") String[] ids){
        if(majorInfoService.removeByIds(Arrays.asList(ids))) {
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }
        return jr(GlobalConstants.ERROR,"删除失败");
    }

    /**
     * 编辑专业页跳转
     * @param model
     * @param id 学院id
     * @return
     */
    @GetMapping("/addOrUpdateMajorPage")
    public ModelAndView addOrUpdateMajorPage(ModelMap model, Integer id) {
        if (ObjectUtil.isNotEmpty(id)){
            MajorInfo majorInfo = majorInfoService.getById(id);
            CollegeInfo collegeInfo = collegeInfoService.getById(majorInfo.getCollegeId());
            if (collegeInfo != null){
                majorInfo.setCollegeName(collegeInfo.getName());
                majorInfo.setYear(collegeInfo.getYear());
            }
            model.put("major", majorInfo);
        }else {
            model.put("major", new MajorInfo());
        }
        return view("major/addOrUpdateMajor", model);
    }

    /**
     * 新增/修改专业信息
     * @param majorInfo 专业信息
     * @return
     */
    @PostMapping("/addOrUpdateMajor")
    @ResponseBody
    public JsonResult addOrUpdateMajor(MajorInfo majorInfo) {
        if (ObjectUtil.isNotEmpty(majorInfo.getId())){
            majorInfo.setLastUpdateBy(CurrentUser.getUser().getUserInfo().getId());
            majorInfo.setLastUpdateTime(LocalDateTime.now());
        }else {
            majorInfo.setCreateBy(CurrentUser.getUser().getUserInfo().getId());
            majorInfo.setCreateTime(LocalDateTime.now());
        }
        if (majorInfoService.saveOrUpdate(majorInfo)){
            return jr(GlobalConstants.SUCCESS,"编辑学院成功");
        }
        return jr(GlobalConstants.ERROR,"编辑学院失败");
    }
}

package com.project.student.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.project.student.common.*;
import com.project.student.domain.CollegeInfo;
import com.project.student.domain.MajorInfo;
import com.project.student.dto.CollegeInfoPage;
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
@RequestMapping("/collegeInfo")
public class CollegeInfoController extends BaseController {

    @Autowired
    private CollegeInfoService collegeInfoService;

    @Autowired
    private MajorInfoService majorInfoService;

    /**
     * 学院列表页面
     * @return 界面
     */
    @GetMapping("/listPagedCollegePage")
    public ModelAndView listPagedCollegePage() {
        return view("college/listPagedCollege");
    }

    /**
     * 学院列表
     * collegeInfoPage 查询参数
     * @return
     */
    @GetMapping("/listPagedCollege")
    public JsonResult<CollegeInfoPage> listPagedCollege(CollegeInfoPage collegeInfoPage) {
        CollegeInfoPage page = collegeInfoService.listPagedCollege(collegeInfoPage);
        if (CollUtil.isEmpty(page.getRecords())){
            return jr(GlobalConstants.ERROR,"查询失败");
        }
        return jr("0","查询成功",page);
    }

    /**
     * 删除学院
     * @param ids
     * @return
     */
    @PostMapping("/removeCollege")
    public JsonResult removeCollege(@NotNull @RequestParam(value = "ids[]") String[] ids){
        int notRemove = 0;
        for (String id : ids){
            //验证学院下是否存在专业
            List<MajorInfo> majorInfos = majorInfoService.list(new LambdaQueryWrapper<MajorInfo>().eq(MajorInfo::getCollegeId,id));
            if (CollUtil.isNotEmpty(majorInfos)){
                notRemove ++;
                continue;
            }
            collegeInfoService.removeById(id);
        }
        if(notRemove == 0) {
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }else {
            return jr(GlobalConstants.ERROR,"有"+notRemove+"个学院存在专业");
        }
    }

    /**
     * 编辑学院页跳转
     * @param model
     * @param id 学院id
     * @return
     */
    @GetMapping("/addOrUpdateCollegePage")
    public ModelAndView addOrUpdateCollegePage(ModelMap model, Integer id) {
        if (ObjectUtil.isNotEmpty(id)){
            CollegeInfo collegeInfo = collegeInfoService.getById(id);
            model.put("college", collegeInfo);
        }else {
            model.put("college", new CollegeInfo());
        }
        return view("college/addOrUpdateCollege", model);
    }

    /**
     * 新增/修改学院信息
     * @param collegeInfo 学院信息
     * @return
     */
    @PostMapping("/addOrUpdateCollege")
    @ResponseBody
    public JsonResult addOrUpdateCollege(CollegeInfo collegeInfo) {
        if (ObjectUtil.isNotEmpty(collegeInfo.getId())){
            collegeInfo.setLastUpdateBy(CurrentUser.getUser().getUserInfo().getId());
            collegeInfo.setLastUpdateTime(LocalDateTime.now());
            CollegeInfo collegeOld = collegeInfoService.getOne(new LambdaQueryWrapper<CollegeInfo>().eq(CollegeInfo::getYear,collegeInfo.getYear()).eq(CollegeInfo::getName,collegeInfo.getName()).ne(CollegeInfo::getId,collegeInfo.getId()));
            if (collegeOld != null){
                return jr(GlobalConstants.ERROR,"学院已经存在");
            }
        }else {
            collegeInfo.setCreateBy(CurrentUser.getUser().getUserInfo().getId());
            collegeInfo.setCreateTime(LocalDateTime.now());
            CollegeInfo collegeOld = collegeInfoService.getOne(new LambdaQueryWrapper<CollegeInfo>().eq(CollegeInfo::getYear,collegeInfo.getYear()).eq(CollegeInfo::getName,collegeInfo.getName()));
            if (collegeOld != null){
                return jr(GlobalConstants.ERROR,"学院已经存在");
            }
        }
        if (collegeInfoService.saveOrUpdate(collegeInfo)){
            return jr(GlobalConstants.SUCCESS,"编辑学院成功");
        }
        return jr(GlobalConstants.ERROR,"编辑学院失败");
    }

    /**
     * 获取所有年份
     * @return
     */
    @GetMapping("/getAllYear")
    @ResponseBody
    public JsonResult<List<CollegeInfo>> getAllYear(){
        return jr(collegeInfoService.getAllYear());
    }

    /**
     * 根据年份获取学院名称
     * @return
     */
    @GetMapping("/getCollege")
    @ResponseBody
    public JsonResult<List<CollegeInfo>> getCollege(Integer year){
        return jr(collegeInfoService.list(new LambdaQueryWrapper<CollegeInfo>().eq(CollegeInfo::getYear,year)));
    }
}

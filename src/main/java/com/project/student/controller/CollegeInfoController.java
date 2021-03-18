package com.project.student.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.project.student.common.*;
import com.project.student.domain.CollegeInfo;
import com.project.student.dto.CollegeInfoPage;
import com.project.student.service.CollegeInfoService;
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
@RequestMapping("/collegeInfo")
public class CollegeInfoController extends BaseController {

    @Autowired
    private CollegeInfoService collegeInfoService;

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
        if(collegeInfoService.removeByIds(Arrays.asList(ids))) {
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }
        return jr(GlobalConstants.ERROR,"删除失败");
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
        }else {
            collegeInfo.setCreateBy(CurrentUser.getUser().getUserInfo().getId());
            collegeInfo.setCreateTime(LocalDateTime.now());
        }
        if (collegeInfoService.saveOrUpdate(collegeInfo)){
            return jr(GlobalConstants.SUCCESS,"编辑学院成功");
        }
        return jr(GlobalConstants.ERROR,"编辑学院失败");
    }
}

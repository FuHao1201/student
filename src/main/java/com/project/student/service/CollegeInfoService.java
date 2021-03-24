package com.project.student.service;

import com.project.student.domain.CollegeInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.student.dto.CollegeInfoPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public interface CollegeInfoService extends IService<CollegeInfo> {

    /**
     * 学院分页列表
     * @param collegeInfoPage 查询参数
     * @return
     */
    CollegeInfoPage listPagedCollege(CollegeInfoPage collegeInfoPage);

    List<CollegeInfo> getAllYear();
}

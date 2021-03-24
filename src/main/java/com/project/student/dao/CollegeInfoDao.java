package com.project.student.dao;

import com.project.student.domain.CollegeInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.student.dto.CollegeInfoPage;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public interface CollegeInfoDao extends BaseMapper<CollegeInfo> {

    /**
     * 学院分页列表
     * @param collegeInfoPage 查询参数
     * @return
     */
    CollegeInfoPage listPagedCollege(CollegeInfoPage collegeInfoPage);

    List<CollegeInfo> getAllYear();
}

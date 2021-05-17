package com.project.student.dao;

import com.project.student.domain.MajorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.student.dto.MajorInfoPage;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author FuHao
 * @since 2021-02-27
 */
public interface MajorInfoDao extends BaseMapper<MajorInfo> {

    /**
     * 学院分页列表
     * @param majorInfoPage 查询参数
     * @return
     */
    MajorInfoPage listPagedMajor(MajorInfoPage majorInfoPage);
}

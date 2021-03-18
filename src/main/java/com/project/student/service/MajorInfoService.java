package com.project.student.service;

import com.project.student.domain.MajorInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.student.dto.MajorInfoPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public interface MajorInfoService extends IService<MajorInfo> {

    /**
     * 专业分页列表
     * @param majorInfoPage 查询参数
     * @return
     */
    MajorInfoPage listPagedMajor(MajorInfoPage majorInfoPage);
}

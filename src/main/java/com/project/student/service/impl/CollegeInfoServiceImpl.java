package com.project.student.service.impl;

import com.project.student.domain.CollegeInfo;
import com.project.student.dao.CollegeInfoDao;
import com.project.student.dto.CollegeInfoPage;
import com.project.student.service.CollegeInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuHao
 * @since 2021-02-27
 */
@Service
public class CollegeInfoServiceImpl extends ServiceImpl<CollegeInfoDao, CollegeInfo> implements CollegeInfoService {

    @Override
    public CollegeInfoPage listPagedCollege(CollegeInfoPage collegeInfoPage) {
        return getBaseMapper().listPagedCollege(collegeInfoPage);
    }

    @Override
    public List<CollegeInfo> getAllYear() {
        return getBaseMapper().getAllYear();
    }
}

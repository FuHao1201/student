package com.project.student.service.impl;

import com.project.student.domain.MajorInfo;
import com.project.student.dao.MajorInfoDao;
import com.project.student.dto.MajorInfoPage;
import com.project.student.service.MajorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author FuHao
 * @since 2021-02-27
 */
@Service
public class MajorInfoServiceImpl extends ServiceImpl<MajorInfoDao, MajorInfo> implements MajorInfoService {

    @Override
    public MajorInfoPage listPagedMajor(MajorInfoPage majorInfoPage) {
        return getBaseMapper().listPagedMajor(majorInfoPage);
    }
}

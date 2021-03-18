package com.project.student.service.impl;

import com.project.student.domain.StudentInfo;
import com.project.student.dao.StudentInfoDao;
import com.project.student.dto.StudentInfoPage;
import com.project.student.service.StudentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
@Service
public class StudentInfoServiceImpl extends ServiceImpl<StudentInfoDao, StudentInfo> implements StudentInfoService {

    @Override
    public StudentInfoPage listPagedStudent(StudentInfoPage studentInfoPage) {
        return getBaseMapper().listPagedStudent(studentInfoPage);
    }
}

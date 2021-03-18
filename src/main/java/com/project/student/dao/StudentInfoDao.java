package com.project.student.dao;

import com.project.student.domain.StudentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.project.student.dto.StudentInfoPage;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public interface StudentInfoDao extends BaseMapper<StudentInfo> {

    StudentInfoPage listPagedStudent(StudentInfoPage studentInfoPage);
}

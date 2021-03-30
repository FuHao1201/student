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

    /**
     * 学生分页列表
     * @param studentInfoPage 查询参数
     * @return
     */
    StudentInfoPage listPagedStudent(StudentInfoPage studentInfoPage);

    /**
     * 根据学生id获取学生信息
     * @param id 学生id
     * @return
     */
    StudentInfo getStudentById(Integer id);
}

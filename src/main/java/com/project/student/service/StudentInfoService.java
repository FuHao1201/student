package com.project.student.service;

import com.project.student.domain.StudentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.student.dto.StudentInfoPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YM
 * @since 2021-02-27
 */
public interface StudentInfoService extends IService<StudentInfo> {

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

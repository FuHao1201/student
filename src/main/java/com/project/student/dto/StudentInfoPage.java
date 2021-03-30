package com.project.student.dto;

import com.project.student.common.PageModel;
import com.project.student.domain.StudentInfo;
import lombok.Data;

@Data
public class StudentInfoPage extends PageModel<StudentInfo> {

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 入学年份
     */
    private String year;

    /**
     * 学号
     */
    private String schoolNumber;
}

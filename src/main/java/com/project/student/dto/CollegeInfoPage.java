package com.project.student.dto;

import com.project.student.common.PageModel;
import com.project.student.domain.CollegeInfo;
import lombok.Data;

@Data
public class CollegeInfoPage extends PageModel<CollegeInfo> {

    /**
     * 学院名
     */
    private String name;

    /**
     * 入学年份
     */
    private String year;
}

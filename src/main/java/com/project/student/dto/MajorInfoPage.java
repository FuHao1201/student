package com.project.student.dto;

import com.project.student.common.PageModel;
import com.project.student.domain.MajorInfo;
import lombok.Data;

@Data
public class MajorInfoPage extends PageModel<MajorInfo> {

    /**
     * 专业名称
     */
    private String name;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 入学年份
     */
    private String year;
}

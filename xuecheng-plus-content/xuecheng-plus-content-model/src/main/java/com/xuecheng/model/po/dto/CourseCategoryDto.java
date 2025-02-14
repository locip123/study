package com.xuecheng.model.po.dto;

import com.xuecheng.model.po.CourseCategory;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class CourseCategoryDto extends CourseCategory {
    private List<CourseCategoryDto> childrenTreeNodes;
}

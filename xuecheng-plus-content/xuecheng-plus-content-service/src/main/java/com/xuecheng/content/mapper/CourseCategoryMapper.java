package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.model.po.CourseCategory;
import com.xuecheng.model.po.dto.CourseCategoryDto;

import java.util.List;

/**
 * <p>
 * 课程分类 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface CourseCategoryMapper extends BaseMapper<CourseCategory> {

    public List<CourseCategoryDto> selectTreeNode(String id);

}

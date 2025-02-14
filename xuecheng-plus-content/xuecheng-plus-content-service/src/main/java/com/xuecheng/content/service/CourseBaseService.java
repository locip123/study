package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.model.po.dto.AddCourseDto;
import com.xuecheng.model.po.dto.CourseBaseInfoDto;
import com.xuecheng.model.po.dto.QueryCourseParamsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


public interface CourseBaseService {
    public PageResult list(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto);

    public CourseBaseInfoDto addCourse(Long companyId,AddCourseDto addCourseDto);
}

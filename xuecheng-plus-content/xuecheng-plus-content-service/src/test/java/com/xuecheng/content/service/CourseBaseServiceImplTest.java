package com.xuecheng.content.service;

import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.service.impl.CourseBaseServiceImpl;
import com.xuecheng.model.po.dto.QueryCourseParamsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseBaseServiceImplTest {
    @Autowired
    CourseBaseServiceImpl courseBaseService;
    @Test
    void list() {
        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1l);
        pageParams.setPageSize(5l);
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");
        PageResult list = courseBaseService.list(pageParams, queryCourseParamsDto);
        System.out.println(list.toString());
    }
}
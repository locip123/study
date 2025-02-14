package com.xuecheng.content.api;


import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.service.impl.CourseBaseServiceImpl;
import com.xuecheng.model.po.CourseBase;
import com.xuecheng.model.po.dto.AddCourseDto;
import com.xuecheng.model.po.dto.CourseBaseInfoDto;
import com.xuecheng.model.po.dto.QueryCourseParamsDto;
import errorHandle.XueChengPlusException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "课程信息")
@RestController
public class CourseBaseInfoController {
    @Autowired
    CourseBaseServiceImpl courseBaseService;
    @ApiOperation(value = "查询所有课程信息")
    @PostMapping("course/list")
    public PageResult<CourseBase> list(@ApiParam("分页查询") PageParams pageParams, @ApiParam("查询参数")@RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto){
        PageResult pageResult = courseBaseService.list(pageParams, queryCourseParamsDto);

        return pageResult;
    }

    @ApiOperation(value = "添加课程")
    @PostMapping("/course")
    public CourseBaseInfoDto addCourse(@RequestBody AddCourseDto addCourseDto){
        if (addCourseDto.getName().equals("")) {
            throw new XueChengPlusException("kong");
        }
        CourseBaseInfoDto courseBaseInfoDto = courseBaseService.addCourse(123456L, addCourseDto);

        return courseBaseInfoDto;
    }

}

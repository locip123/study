package com.xuecheng.content.api;


import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.model.po.CourseBase;
import com.xuecheng.model.po.dto.QueryCourseParamsDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(tags = "课程信息")
@RestController
public class CourseBaseInfoController {
    @ApiOperation(value = "查询所有课程信息")
    @PostMapping("course/list")
    public PageResult<CourseBase> list(@ApiParam("分页查询") PageParams pageParams, @ApiParam("查询参数")@RequestBody(required = false) QueryCourseParamsDto queryCourseParamsDto){

        return null;
    }
}

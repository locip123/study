package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.mapper.CourseMarketMapper;
import com.xuecheng.content.service.CourseBaseService;
import com.xuecheng.model.po.CourseBase;
import com.xuecheng.model.po.CourseMarket;
import com.xuecheng.model.po.dto.AddCourseDto;
import com.xuecheng.model.po.dto.CourseBaseInfoDto;
import com.xuecheng.model.po.dto.QueryCourseParamsDto;
import errorHandle.XueChengPlusException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class CourseBaseServiceImpl implements CourseBaseService {
    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Autowired
    CourseMarketMapper courseMarketMapper;

    @Override
    @Transactional
    public PageResult list(PageParams pageParams, QueryCourseParamsDto queryCourseParamsDto) {
        LambdaQueryWrapper<CourseBase> courseBaseLambdaQueryWrapper = new LambdaQueryWrapper<>();
        courseBaseLambdaQueryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()),CourseBase::getName,queryCourseParamsDto.getCourseName());
        courseBaseLambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,queryCourseParamsDto.getAuditStatus());
        courseBaseLambdaQueryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getPublishStatus()),CourseBase::getStatus,queryCourseParamsDto.getPublishStatus());
        Page<CourseBase> courseBasePage = new Page<>(pageParams.getPageNo(),pageParams.getPageSize());
        Page<CourseBase> selectPage = courseBaseMapper.selectPage(courseBasePage, courseBaseLambdaQueryWrapper);
        PageResult<CourseBase> courseBasePageResult = new PageResult<CourseBase>(selectPage.getRecords(),selectPage.getTotal(),selectPage.getPages(),pageParams.getPageSize());
        return courseBasePageResult;
    }
    @Transactional
    @Override
    public CourseBaseInfoDto addCourse(Long companyId,AddCourseDto dto) {
        if (StringUtils.isBlank(dto.getName())) {
            throw new XueChengPlusException("课程信息为空");
        }

        if (StringUtils.isBlank(dto.getMt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getSt())) {
            throw new RuntimeException("课程分类为空");
        }

        if (StringUtils.isBlank(dto.getGrade())) {
            throw new RuntimeException("课程等级为空");
        }

        if (StringUtils.isBlank(dto.getTeachmode())) {
            throw new RuntimeException("教育模式为空");
        }

        if (StringUtils.isBlank(dto.getUsers())) {
            throw new RuntimeException("适应人群为空");
        }

        if (StringUtils.isBlank(dto.getCharge())) {
            throw new RuntimeException("收费规则为空");
        }
        CourseBase courseBase = new CourseBase();
        BeanUtils.copyProperties(dto,courseBase);
        courseBase.setCompanyId(companyId);
        courseBase.setCreateDate(LocalDateTime.now());
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");
        int insert = courseBaseMapper.insert(courseBase);
        if (insert<=0){
            throw new RuntimeException("插入失败");
        }

        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto,courseMarket);
        Long id = courseBase.getId();
        courseMarket.setId(id);
        saveCourseMarket(courseMarket);
        CourseBaseInfoDto courseBaseInfo = getCourseBaseInfo(id);

        return courseBaseInfo;
    }

    //查询课程信息
    public CourseBaseInfoDto getCourseBaseInfo(long courseId){

        //从课程基本信息表查询
        CourseBase courseBase = courseBaseMapper.selectById(courseId);
        if(courseBase==null){
            return null;
        }
        //从课程营销表查询
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        //组装在一起
        CourseBaseInfoDto courseBaseInfoDto = new CourseBaseInfoDto();
        BeanUtils.copyProperties(courseBase,courseBaseInfoDto);
        if(courseMarket!=null){
            BeanUtils.copyProperties(courseMarket,courseBaseInfoDto);
        }



        return courseBaseInfoDto;

    }

    //单独写一个方法保存营销信息，逻辑：存在则更新，不存在则添加
    private int saveCourseMarket(CourseMarket courseMarketNew){

        //参数的合法性校验
        String charge = courseMarketNew.getCharge();
        if(StringUtils.isEmpty(charge)){
            throw new RuntimeException("收费规则为空");
        }
        //如果课程收费，价格没有填写也需要抛出异常

        //从数据库查询营销信息,存在则更新，不存在则添加
        Long id = courseMarketNew.getId();//主键
        CourseMarket courseMarket = courseMarketMapper.selectById(id);
        if(courseMarket == null){
            //插入数据库
            int insert = courseMarketMapper.insert(courseMarketNew);
            return insert;
        }else{
            //将courseMarketNew拷贝到courseMarket
            BeanUtils.copyProperties(courseMarketNew,courseMarket);
            courseMarket.setId(courseMarketNew.getId());
            //更新
            int i = courseMarketMapper.updateById(courseMarket);

            return i;
        }


    }
}

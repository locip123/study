package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.content.mapper.CourseCategoryMapper;
import com.xuecheng.content.service.CourseCategoryService;
import com.xuecheng.model.po.CourseCategory;
import com.xuecheng.model.po.dto.CourseCategoryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;
    @Override
    @Transactional
    public List<CourseCategoryDto> selectTreeNode(String id) {
        List<CourseCategoryDto> courseCategoryDtos = courseCategoryMapper.selectTreeNode("1");
        courseCategoryDtos.remove(0);
        for (CourseCategoryDto cou:
             courseCategoryDtos) {
            String couId = cou.getId();
            LambdaQueryWrapper<CourseCategory> courseCategoryLambdaQueryWrapper = new LambdaQueryWrapper<>();
            courseCategoryLambdaQueryWrapper.eq(CourseCategory::getParentid,couId);
            List<CourseCategory> courseCategories = courseCategoryMapper.selectList(courseCategoryLambdaQueryWrapper);
            ArrayList<CourseCategoryDto> childCourseCategoryDtos = new ArrayList<>();
            for (CourseCategory cours:
                 courseCategories) {
                CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
                BeanUtils.copyProperties(cours,courseCategoryDto);
                childCourseCategoryDtos.add(courseCategoryDto);
            }
            cou.setChildrenTreeNodes(childCourseCategoryDtos);
        }
        return courseCategoryDtos;
    }
}

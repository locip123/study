package com.xuecheng.content.mapper;

import com.xuecheng.model.po.CourseCategory;
import com.xuecheng.model.po.dto.CourseCategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseCategoryMapperTest {
    @Autowired
    CourseCategoryMapper courseCategoryMapper;

    @Test
    void selectTreeNode() {
        List<CourseCategoryDto> courseCategoryDtos = courseCategoryMapper.selectTreeNode("1");
        System.out.println(courseCategoryDtos.toString());

    }
}
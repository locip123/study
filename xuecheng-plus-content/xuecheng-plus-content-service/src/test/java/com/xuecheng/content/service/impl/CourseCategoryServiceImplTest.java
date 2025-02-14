package com.xuecheng.content.service.impl;


import com.xuecheng.model.po.dto.CourseCategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseCategoryServiceImplTest {
    @Autowired
    CourseCategoryServiceImpl courseCategoryService;
    @Test
    public void selectTreeNode() {
        List<CourseCategoryDto> courseCategoryDtoList=courseCategoryService.selectTreeNode("1");
        System.out.println(courseCategoryDtoList);
    }
}
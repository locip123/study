package com.xuecheng.content.api;

import com.xuecheng.content.service.impl.CourseCategoryServiceImpl;
import com.xuecheng.model.po.dto.CourseCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseCategoryController {
    @Autowired
    CourseCategoryServiceImpl courseCategoryService;
    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryDto> queryTreeNodes(String id){
        List<CourseCategoryDto> courseCategoryDtoList=courseCategoryService.selectTreeNode(id);
        return courseCategoryDtoList;
    }
}

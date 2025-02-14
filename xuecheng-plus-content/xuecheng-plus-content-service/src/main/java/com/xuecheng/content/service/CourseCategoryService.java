package com.xuecheng.content.service;

import com.xuecheng.model.po.dto.CourseCategoryDto;

import java.util.List;

public interface CourseCategoryService {
    public List<CourseCategoryDto> selectTreeNode(String id);
}

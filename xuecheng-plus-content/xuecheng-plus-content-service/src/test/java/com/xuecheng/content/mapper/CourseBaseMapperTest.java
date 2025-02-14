package com.xuecheng.content.mapper;

import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.model.po.CourseBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseBaseMapperTest {
    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Test
    public void list(){
        CourseBase courseBase = courseBaseMapper.selectById(18);
        System.out.println(courseBase.toString());

    }
}

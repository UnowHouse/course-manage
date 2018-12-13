package com.unow.mapper;

import com.unow.pojo.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.mapper
 *  @文件名:   CourseMapperTest
 *  @创建者:   Unow
 *  @创建时间:  2018/12/10 16:35
 *  @描述：    TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseMapperTest {
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void t1(){
        Course course = new Course();
        course.setPid(2L);
        List<Course> list = courseMapper.select(course);
//        System.out.println(list.get(0).getName());
    }
}
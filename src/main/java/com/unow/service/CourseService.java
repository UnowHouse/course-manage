package com.unow.service;


import com.unow.common.ExceptionEnum;
import com.unow.common.MyException;
import com.unow.mapper.CourseMapper;
import com.unow.pojo.Course;
import com.unow.vo.NodeBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.service
 *  @文件名:   CourseService
 *  @创建者:   Unow
 *  @创建时间:  2018/12/10 17:18
 *  @描述：    TODO
 */
@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 通过Pid查找子节点s
     * @param pid
     * @return
     */
    public List<Course> queryCourseByPid(Long pid) {
        Course course = new Course();
        course.setPid(pid);
        List<Course> list = courseMapper.select(course);
        if(CollectionUtils.isEmpty(list)){
            throw new MyException(ExceptionEnum.NOT_NODES);
        }
        return list;
    }

    /**
     * 插入节点
     * @param nodeBody
     * @return
     */
    @Transient
    public Course insertCourse(NodeBody nodeBody) {
        Course course = new Course();
        course.setPid(nodeBody.getPid());
        course.setIsParent(false);
        course.setCreateTime(new Date());
        course.setRecentTime(new Date());
        course.setText(nodeBody.getText());
        int insert = courseMapper.insert(course);
        if(!nodeBody.getParentStatu()){
            courseMapper.updateParentStatus(nodeBody.getPid(),true);
        }
        if(insert != 1){
            throw new MyException(ExceptionEnum.ERROR_INSERT_NODES);
        }
        return course;
    }

    /**
     * 更新节点
     * @param id
     * @param text
     * @return
     */
    public Course updateCourse(Long id, String text,Long pid) {
        Course course = new Course();
        course.setText(text);
        course.setId(id);
        course.setPid(pid);
        course.setRecentTime(new Date());
        int i = courseMapper.updateByPrimaryKeySelective(course);
        if(i != 1){
            throw new MyException(ExceptionEnum.ERROR_UPDATE_NODES);
        }
        return course;
    }


    @Transient
    public void deleteNode(Long id) {

        int i = courseMapper.deleteByPrimaryKey(id);
        courseMapper.deleteNodesByPid(id);
        if(i != 1) {
            throw new MyException(ExceptionEnum.ERROR_DELETE_NODES);
        }

    }


}

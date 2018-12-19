package com.unow.web;

import com.unow.pojo.Course;
import com.unow.service.CourseService;
import com.unow.vo.NodeBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.web
 *  @文件名:   CourseController
 *  @创建者:   Unow
 *  @创建时间:  2018/12/10 17:17
 *  @描述：    TODO
 */
@Slf4j
@RestController
@RequestMapping("api")
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 根据parentid查询该分类下的子分类
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Course>> queryCourseByPid(@RequestParam(value="pid",defaultValue = "0")Long pid){
        return  ResponseEntity.ok(courseService.queryCourseByPid(pid));
    }

    /**
     * 添加节点
     * @param nodeBody
     * @return
     */
    @PostMapping("add")
    public ResponseEntity<Course> insertNode(@RequestBody NodeBody nodeBody){
        return ResponseEntity.ok(courseService.insertCourse(nodeBody));
    }

    /**
     * 修改节点
     * @param id
     * @param text
     * @return
     */
    @PutMapping("edit")
    public ResponseEntity<Course> updateNode(@RequestParam(name="id",required = true)Long id,
                                             @RequestParam(name="text",required = false)String text,
                                             @RequestParam(name="pid",required = false)Long pid){
        return ResponseEntity.ok(courseService.updateCourse(id,text,pid));
    }

    /**
     * 删除节点
     * @param id
     * @return
     */
    @DeleteMapping("delete")
    public ResponseEntity<Void> deleteNode(@RequestParam(name="id") Long id){
        courseService.deleteNode(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

//    @GetMapping("search")
//    public ResponseEntity<List<Course>>

}


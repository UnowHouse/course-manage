package com.unow.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.pojo
 *  @文件名:   Course
 *  @创建者:   Unow
 *  @创建时间:  2018/12/10 16:31
 *  @描述：    TODO
 */
@Data
@Table(name = "course")
public class Course {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long pid;
    private String text;
    private Boolean isParent;
    @Transient
    private List<Course> nodes;
    private Date createTime;
    private Date recentTime;


}

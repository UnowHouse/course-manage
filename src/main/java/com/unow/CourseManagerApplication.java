package com.unow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow
 *  @文件名:   CourseManagerApplication
 *  @创建者:   Unow
 *  @创建时间:  2018/12/10 16:30
 *  @描述：    TODO
 */

@SpringBootApplication
@MapperScan("com.unow.mapper")
public class CourseManagerApplication {

    public static void main(String [] args){
        SpringApplication.run(CourseManagerApplication.class,args);
    }

}

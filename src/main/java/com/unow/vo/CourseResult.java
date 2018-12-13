package com.unow.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.vo
 *  @文件名:   CourseResult
 *  @创建者:   Unow
 *  @创建时间:  2018/12/11 14:56
 *  @描述：    TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResult<T> {
    private Long pageTotal;
    private Long total;
    private List<T> items;

}

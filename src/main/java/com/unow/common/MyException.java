package com.unow.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.common
 *  @文件名:   MyException
 *  @创建者:   Unow
 *  @创建时间:  2018/12/14 0:30
 *  @描述：    TODO
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyException extends RuntimeException{
    private ExceptionEnum exceptionEnum;
}

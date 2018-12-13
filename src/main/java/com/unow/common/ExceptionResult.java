package com.unow.common;

import lombok.Data;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.common
 *  @文件名:   ExceptionResult
 *  @创建者:   Unow
 *  @创建时间:  2018/12/14 0:35
 *  @描述：    TODO
 */
@Data
public class ExceptionResult {

    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em){
        this.status = em.getCode();
        this.message = em.getMsg();
        this.timestamp = System.currentTimeMillis();
    }
}

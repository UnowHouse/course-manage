package com.unow.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.common
 *  @文件名:   ExceptionHandle
 *  @创建者:   Unow
 *  @创建时间:  2018/12/14 0:38
 *  @描述：    TODO
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResult> handleException(MyException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
    }

}

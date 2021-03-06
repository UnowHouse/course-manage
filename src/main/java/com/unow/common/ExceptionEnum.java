package com.unow.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.common
 *  @文件名:   ExceptionEnum
 *  @创建者:   Unow
 *  @创建时间:  2018/12/14 0:41
 *  @描述：    TODO
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ExceptionEnum {
    NOT_NODES(404,"不存在该节点"),
    ERROR_INSERT_NODES(500,"新增节点失败"),
    ERROR_UPDATE_NODES(500,"更新节点失败"),
    ERROR_DELETE_NODES(500,"删除节点失败"),
    ERROR_LOGIN(400,"账号密码错误"),
    ERROR_REGISTER(400,"账号已存在"),
    ERROR_INSERT_MANAGER(500,"新增管理员失败"),
    NOT_LOGIN(401,"不在登录状态"),

    ;

    private int code;
    private String msg;
}

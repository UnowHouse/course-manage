package com.unow.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.pojo
 *  @文件名:   Manager
 *  @创建者:   Unow
 *  @创建时间:  2018/12/17 23:27
 *  @描述：    TODO
 */
@Table(name = "manager")
@Data
public class Manager {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String username;
    private String password;
    private String sickname;
}

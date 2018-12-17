package com.unow.mapper;

import com.unow.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.mapper
 *  @文件名:   ManagerMapper
 *  @创建者:   Unow
 *  @创建时间:  2018/12/17 23:27
 *  @描述：    TODO
 */
public interface ManagerMapper extends Mapper<Manager> {

    @Select("SELECT * FROM `manager` WHERE `username` = #{username} AND `password` = #{password}")
    Manager queryManager(@Param("username")String username,@Param("password")String password);

}

package com.unow.mapper;

import com.unow.pojo.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.mapper
 *  @文件名:   CourseMapper
 *  @创建者:   Unow
 *  @创建时间:  2018/12/10 16:34
 *  @描述：    TODO
 */

public interface CourseMapper extends Mapper<Course> {

    @Update(" UPDATE `course` SET `is_parent` = #{isParent} WHERE `id` = #{pid} ")
    void updateParentStatus(@Param("pid")Long id,@Param("isParent") Boolean isParent);

    @Delete("DELETE FROM `course` WHERE `pid` = #{pid}")
    void deleteNodesByPid(@Param("pid")Long pid);
}

package com.unow.vo;

/*
 *  @项目名：  course-manager 
 *  @包名：    com.unow.vo
 *  @文件名:   NodeBody
 *  @创建者:   Unow
 *  @创建时间:  2018/12/12 9:16
 *  @描述：    TODO
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeBody {
    private String text;
    private Long pid;
    private Boolean parentStatu;
}

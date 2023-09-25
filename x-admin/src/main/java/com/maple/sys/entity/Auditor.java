package com.maple.sys.entity;


import lombok.Data;


@Data
public class Auditor {

    //审核员编号
    private Integer USERNAME;
    //审核员密码
    private String PASSWORD;
    //审核员身份证
    private String a_id;
    //头像
    private String AVATAR;


}

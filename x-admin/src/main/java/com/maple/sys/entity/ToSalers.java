package com.maple.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "TO_SALERS")
public class ToSalers {
    @TableField(value = "SALERID")
    private int salerId;
    @TableField(value = "SALERNAME")
    private String salerName;
    @TableField(value = "PHONE")
    private String phone;
    @TableField(value = "ADDRESS")
    private String address;
    @TableField(value = "REMARK")
    private String remark;
    @TableField(value = "RESULT")
    private String result;
}

package com.maple.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.maple.sys.entity.ToSalers;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ToSalersService extends IService<ToSalers> {
    List<ToSalers> selectAll();

    ToSalers getById(Integer id);

    ToSalers getByName(String name);

    void addSalers(String salername,String phone,String address,String remark,String result);

    ToSalers getSalerById(Integer id);
    void updateResult(int result,int salerId);
}

package com.maple.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.maple.sys.entity.ToSalers;
import com.maple.sys.mapper.AuditorMapper;
import com.maple.sys.mapper.ToSalersMapper;
import com.maple.sys.service.ToSalersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToSalersServiceImpl extends ServiceImpl<ToSalersMapper,ToSalers> implements ToSalersService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ToSalersMapper toSalersMapper;
    @Override
    public List<ToSalers> selectAll() {
        return toSalersMapper.selectAll();

    }

    @Override
    public ToSalers getById(Integer id) {
        return null;
    }

    @Override
    public ToSalers getSalerById(Integer id) {
        return toSalersMapper.getSalerById(id);
    }

    @Override
    public void updateResult(int result, int salerId) {
        toSalersMapper.updateResult(result,salerId);
    }

    @Override
    public void updateById(String salername, String phone, String address, String remark, int salerId) {
        toSalersMapper.updateById(salername,phone,address,remark,salerId);
    }

    @Override
    public ToSalers getByName(String name) {
        return toSalersMapper.getByName(name);
    }

    @Override
    public void addSalers(String salername, String phone, String address, String remark) {
        toSalersMapper.addSalers(salername,phone,address,remark);
    }
}

package com.maple.sys.service.impl;


import com.alibaba.fastjson2.JSON;
import com.maple.sys.entity.Auditor;
import com.maple.sys.mapper.AuditorMapper;
import com.maple.sys.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class AuditorServiceImpl implements AuditorService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AuditorMapper auditorMapper;
    @Override
    public List<Auditor> selectAll() {
        List<Auditor> auditors = auditorMapper.selectAll();
        return auditors;
    }

    @Override
    public Auditor getById(Integer id) {
        Auditor auditor = auditorMapper.getById(id);
        return auditor;
    }

    @Override
    public Map<String, Object> login(Auditor auditor) {
        //根据用户名和密码进行查询
        Integer USERNAME = auditor.getUSERNAME();
        String PASSWORD = auditor.getPASSWORD();
        Auditor login = auditorMapper.login(USERNAME, PASSWORD);

        //结果不为空，则需要生成token返回
        if (login != null) {
            //暂时用uuid
            String key = "auditor:" + UUID.randomUUID();
            //存储redis
//            login.setA_pw(null);
            redisTemplate.opsForValue().set(key,login,30, TimeUnit.MINUTES);
            //返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token", key);
            return data;
        }


        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //根据token，从redis中获取用户信息
        Object o = redisTemplate.opsForValue().get(token);
        if(o!=null){
            Auditor auditor = JSON.parseObject(JSON.toJSONString(o),Auditor.class);
            Map<String,Object> data = new HashMap<>();
            data.put("USERNAME",auditor.getUSERNAME());
            data.put("PASSWORD",auditor.getPASSWORD());
            data.put("AVATAR",auditor.getAVATAR());
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);

    }
}

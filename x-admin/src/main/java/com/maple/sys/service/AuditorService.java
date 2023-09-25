package com.maple.sys.service;

import com.maple.sys.entity.Auditor;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface AuditorService {

    List<Auditor> selectAll();
    Auditor getById(Integer id);

    Map<String, Object> login(Auditor auditor);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);
}

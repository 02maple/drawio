package com.maple.sys.controller;


import com.maple.common.vo.Result;
import com.maple.sys.entity.Auditor;
import com.maple.sys.service.AuditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/auditors")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    @GetMapping("/all")
    public Result<List<Auditor>> selectAll(){
        List<Auditor> all = auditorService.selectAll();
        return Result.success(all,"查询成功");
    }

    @GetMapping("/{id}")
    public Auditor getById(@PathVariable Integer id){
        return auditorService.getById(id);
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Auditor auditor){
        Map<String, Object> data = auditorService.login(auditor);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(20002,"用户名或密码错误");

    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token){
        //根据token获取用户的信息，redis
        Map<String, Object> data =auditorService.getUserInfo(token);
        if(data != null){
            return Result.success(data);
        }
        return Result.fail(20003,"登录信息无效，请重新登陆");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token){
        auditorService.logout(token);
        return Result.success();
    }

    @GetMapping


}

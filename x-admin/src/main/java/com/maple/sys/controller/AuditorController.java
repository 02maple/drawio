package com.maple.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.maple.common.vo.Result;
import com.maple.sys.entity.Auditor;
import com.maple.sys.entity.ToSalers;
import com.maple.sys.service.AuditorService;
import com.maple.sys.service.ToSalersService;
import lombok.experimental.PackagePrivate;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auditors")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    @Autowired
    private ToSalersService toSalersService;

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

    @GetMapping("/lists")
    public Result<Map<String,Object>> getSalerList(@RequestParam(value = "salerName",required = false) String salerName,
                                                   @RequestParam(value = "phone",required = false) String phone,
                                                   @RequestParam(value = "pageNo") Long pageNo,
                                                   @RequestParam(value = "pageSize") Long pageSize){

        LambdaQueryWrapper<ToSalers> wrapper = new LambdaQueryWrapper<>();
        //          做非空判断
        wrapper.like(StringUtils.hasLength(salerName),ToSalers::getSalerName,salerName);
        wrapper.like(StringUtils.hasLength(phone),ToSalers::getPhone,phone);
        wrapper.orderByAsc(ToSalers::getSalerId);

        Page<ToSalers> page = new Page<>(pageNo,pageSize);

        toSalersService.page(page,wrapper);

        Map<String,Object> data = new HashMap<>();
        data.put("total",page.getTotal());
        data.put("rows",page.getRecords());
        System.out.println(data);

        return Result.success(data);
    }

    @PostMapping             //需要json前后端转化
    public Result<?> addSalers (@RequestBody ToSalers toSalers){
//        toSalersService.save(toSalers);
        toSalersService.addSalers(toSalers.getSalerName(),toSalers.getPhone(),toSalers.getAddress(),toSalers.getRemark(),toSalers.getResult());
        return Result.success("添加成功");
    }

    @PutMapping             //需要json前后端转化
    public Result<?> updateSalers (@RequestBody ToSalers toSalers){
        toSalersService.updateById(toSalers);
        return Result.success("修改成功");
    }

    @GetMapping("/sid/{id}")
    public Result<ToSalers> getSalerById(@PathVariable("id") Integer id){
        ToSalers saler = toSalersService.getSalerById(id);
        return Result.success(saler);
    }

    @PostMapping("/upresult")
    public Result<?> updateResult(@RequestParam("result") int result,@RequestParam("salerId") int salerId){
        toSalersService.updateResult(result,salerId);
        return Result.success("状态修改成功");
    }

}

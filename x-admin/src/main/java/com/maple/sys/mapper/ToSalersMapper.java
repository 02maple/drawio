package com.maple.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.maple.sys.entity.Auditor;
import com.maple.sys.entity.ToSalers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ToSalersMapper extends BaseMapper<ToSalers> {

    @Select(" select * from USER01.TO_SALERS ")
    List<ToSalers> selectAll();

    @Select("select * from USER01.TO_SALERS where SALERID = #{id}")
    ToSalers getSalerById(Integer id);

    @Select("select * from USER01.TO_SALERS where SALERNAME= #{name}")
    ToSalers getByName(String name);

    @Insert("insert into USER01.TO_SALERS (SALERNAME, PHONE, ADDRESS, REMARK, RESULT)" +
            "values (#{salername},#{phone},#{address},#{remark},#{result})")
    void addSalers(String salername,String phone,String address,String remark,String result);

    @Update("update USER01.TO_SALERS set RESULT = #{result} where SALERID = #{salerId}")
    void updateResult(int result,int salerId);

//    @Update("update USER01.TO_SALERS set ")
}

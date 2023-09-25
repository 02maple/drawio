package com.maple.sys.mapper;

import com.maple.sys.entity.Auditor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.tomcat.Jar;


import java.util.List;

/**
 * Mapper接口
 *
 * @author maple
 * @since 2023-9-23
 */


@Mapper
public interface AuditorMapper{
    @Select(" select * from USER01.\"auditor\" ")
    List<Auditor> selectAll();

    @Select("select * from USER01.\"auditor\" where username = #{id}")
    Auditor getById(Integer id);

    @Select("select * from USER01.\"auditor\" where username = #{id} and password = #{pw}")
    Auditor login(Integer id,String pw);
}

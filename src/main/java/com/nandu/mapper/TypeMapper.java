package com.nandu.mapper;

import com.nandu.pojo.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeMapper {
    @Select("select * from t_type")
    List<Type> findallType();

    @Select("select * from t_type where id = #{id}")
    Type findTypebyId(@Param("id")Long id);

    @Select("select * from t_type where name = #{name}")
    Type findTypebyName(@Param("name") String name);  //用于判断添加重复的类

    List<Type> findTopType();

    @Insert("insert into t_type(name) values(#{type.name})")
    int addType(@Param("type")Type type);

    @Update("update t_type set name = #{type.name} where id = #{type.id}")
    int updateType(@Param("type")Type type);

    @Delete("delete from t_type where id=#{id}")
    int delType(@Param("id")Long id);

    List<Type> findBlogType();
}

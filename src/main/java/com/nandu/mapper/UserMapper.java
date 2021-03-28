package com.nandu.mapper;

import com.nandu.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from t_user where username = #{username} and password = #{password}")
    User findUser(@Param("username")String username,@Param("password")String password);
}

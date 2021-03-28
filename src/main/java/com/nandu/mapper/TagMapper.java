package com.nandu.mapper;

import com.nandu.pojo.Tag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper {
    @Select("select * from t_tag")
    List<Tag> findallTag();

    @Select("select * from t_tag where id = #{id}")
    Tag findTagbyId(@Param("id")Long id);

    @Select("select * from t_tag where name = #{name}")
    Tag findTagbyName(@Param("name") String name);

    List<Tag> findTopTag();

    @Insert("insert into t_tag(name) values(#{tag.name})")
    int addTag(@Param("tag")Tag tag);

    @Update("update t_tag set name = #{tag.name} where id = #{tag.id}")
    int updateTag(@Param("tag")Tag tag);

    @Delete("delete from t_tag where id=#{id}")
    int delTag(@Param("id")Long id);

    List<Tag> findBlogTag();
}


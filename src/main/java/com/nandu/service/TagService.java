package com.nandu.service;

import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagService {

    List<Tag> findallTag();

    Tag findTagbyId(@Param("id")Long id);

    Tag findTagbyName(@Param("name") String name);

    List<Tag> findTopTag();

    int addTag(@Param("tag")Tag tag);

    int updateTag(@Param("tag")Tag tag);

    int delTag(@Param("id")Long id);

    PageInfo<Tag> findForPage(Integer page, Integer size);

    List<Tag> findTagByString(String tagIds);

    List<Tag> findBlogTag();
}

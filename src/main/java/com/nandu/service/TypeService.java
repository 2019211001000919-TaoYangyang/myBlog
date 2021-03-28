package com.nandu.service;

import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Type;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TypeService {

    List<Type> findallType();

    Type findTypebyId(Long id);

    Type findTypebyName(String name);

    List<Type> findTopType();

    int addType(Type type);

    int updateType(Type type);

    int delType(Long id);

    PageInfo<Type> findForPage(Integer page, Integer size);

    List<Type> findBlogType();
}

package com.nandu.service;

import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Blog;
import com.nandu.pojo.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BlogService {
    List<Blog> findall(Map<String,Object> map);

    Blog findbyId(Long id);

    int addBlog(Blog blog);

    int updateBlog(Blog blog);

    int delBlog(Long id);

    PageInfo<Blog> findForPage(Integer page,Integer size,Map map);

    PageInfo<Blog> findIndexBlog(Integer page,Integer size);

    int delBlogAndTagbyBid(@Param("id") Long id);

    List<Blog> findRecommendBlog();

    Blog findDetailedBlog(Long id);

    PageInfo<Blog> getSearchBlog(Integer page,Integer size,String query);

    PageInfo<Blog> findByTypeId(Integer page,Integer size, Long id);

    PageInfo<Blog> findByTagId(Integer page,Integer size, Long id);

    Map<String,List<Blog>> archiveBlog();  //归档博客

    int countBlog();

    int addView(@Param("id") Long id);
}

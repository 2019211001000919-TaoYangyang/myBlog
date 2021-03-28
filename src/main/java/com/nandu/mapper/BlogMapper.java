package com.nandu.mapper;

import com.nandu.pojo.Blog;
import com.nandu.pojo.BlogAndTag;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface BlogMapper{

    List<Blog> findall(@Param("map") Map<String,Object> map);

    Blog findbyId(@Param("id") Long id);

    List<Blog> findIndexBlog();

    int addBlog(@Param("blog") Blog blog);

    int updateBlog(@Param("blog") Blog blog);

    @Delete("delete from t_blog where id = #{id}")
    int delBlog(@Param("id") Long id);

    @Insert(" insert into t_blog_tags(tags_id,blogs_id) values (#{tagsId},#{blogsId})")
    int saveBlogAndTag(BlogAndTag blogAndTag);

    @Delete("delete from t_blog_tags where blogs_id = #{id}")
    int delBlogAndTagbyBid(@Param("id") Long id);

    @Select("select id, title from t_blog where recommend = 1 order by update_time desc;")
    List<Blog> findRecommendBlog();

    Blog findDetailedBlog(Long id);

    List<Blog> getSearchBlog(String query);

    List<Blog> findByTypeId(Long id);

    List<Blog> findByTagId(Long id);

    List<String> findGroupYear();

    List<Blog> findByYear(String year);

    @Update("update t_blog set views = views + 1 where id = #{id}")
    int addView(@Param("id") Long id);
}

package com.nandu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nandu.exception.NotFoundException;
import com.nandu.mapper.BlogMapper;
import com.nandu.pojo.Blog;
import com.nandu.pojo.BlogAndTag;
import com.nandu.pojo.Tag;
import com.nandu.utils.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> findall(Map<String,Object> map) {
        return blogMapper.findall(map);
    }

    @Override
    public Blog findbyId(Long id) {
        if (id == null || id < 0){
            throw new NotFoundException("请选择一条记录进行操作...");
        }
        return blogMapper.findbyId(id);
    }


    @Override
    public List<Blog> findRecommendBlog() {
        return blogMapper.findRecommendBlog();
    }

    @Override
    public Blog findDetailedBlog(Long id) {
        Blog blog = blogMapper.findDetailedBlog(id);
        if (blog == null) {
            throw new NotFoundException("该博客不存在");
        }
        String content = blog.getContent();
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));  //将Markdown格式转换成html
        return blog;
    }

    @Override
    public int addBlog(Blog blog) {
        blog.setCreateTime(new Timestamp(new Date().getTime()));
        blog.setUpdateTime(new Timestamp(new Date().getTime()));
        blog.setViews(0);
        //保存博客
        blogMapper.addBlog(blog);
        //保存博客后才能获取自增的id
        Long id = blog.getId();
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            //新增时无法获取自增的id,在mybatis里修改
            blogAndTag = new BlogAndTag(tag.getId(),id);
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return 1;
    }

    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Timestamp(new Date().getTime()));
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(), blog.getId());
            blogMapper.saveBlogAndTag(blogAndTag);
        }
        return blogMapper.updateBlog(blog);
    }

    @Override
    public int delBlog(Long id) {
        return blogMapper.delBlog(id);
    }

    @Override
    public PageInfo<Blog> findForPage(Integer page, Integer size, Map map) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }
        PageHelper.startPage(page,size);

        List<Blog> blogs = blogMapper.findall(map);

        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        return blogPageInfo;
    }

    @Override
    public PageInfo<Blog> getSearchBlog(Integer page, Integer size , String query) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }
        PageHelper.startPage(page,size);

        List<Blog> searchBlog = blogMapper.getSearchBlog(query);

        PageInfo<Blog> searchBlogPageInfo = new PageInfo<>(searchBlog);

        return searchBlogPageInfo;
    }

    @Override
    public int countBlog() {
        Map<String,Object> map = new HashMap<>();
        map.put("title",null);
        map.put("typeId",null);
        map.put("recommend",null);
        return blogMapper.findall(map).size();
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years = blogMapper.findGroupYear();
        Set<String> set = new HashSet<>(years);  //set去掉重复的年份
        Map<String, List<Blog>> map = new HashMap<>();
        for (String year : set) {
            map.put(year, blogMapper.findByYear(year));
        }
        return map;
    }

    @Override
    public PageInfo<Blog> findByTagId(Integer page, Integer size, Long id) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }
        PageHelper.startPage(page,size);

        List<Blog> blogs = blogMapper.findByTagId(id);

        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        return blogPageInfo;
    }

    @Override
    public PageInfo<Blog> findByTypeId(Integer page,Integer size, Long id) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }
        PageHelper.startPage(page,size);

        List<Blog> blogs = blogMapper.findByTypeId(id);

        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        return blogPageInfo;
    }

    @Override
    public PageInfo<Blog> findIndexBlog(Integer page, Integer size) {
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 5;
        }
        PageHelper.startPage(page,size);

        List<Blog> blogs = blogMapper.findIndexBlog();

        PageInfo<Blog> blogPageInfo = new PageInfo<>(blogs);

        return blogPageInfo;
    }

    @Override
    public int delBlogAndTagbyBid(Long id) {
        return blogMapper.delBlogAndTagbyBid(id);
    }

    @Override
    public int addView(Long id) {
        return blogMapper.addView(id);
    }
}

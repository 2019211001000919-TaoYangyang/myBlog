package com.nandu.controller.user;

import com.github.pagehelper.PageInfo;
import com.nandu.mapper.CommentMapper;
import com.nandu.pojo.Blog;
import com.nandu.service.BlogService;
import com.nandu.service.CommentService;
import com.nandu.service.TagService;
import com.nandu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;


    @GetMapping({"/","/index"})
    public String index(Model model,
                        @RequestParam(defaultValue = "1",value = "page")Integer page,
                        @RequestParam(defaultValue = "5",value = "size") Integer size){

        model.addAttribute("pageInfo",blogService.findIndexBlog(page,size));
        model.addAttribute("topType",typeService.findTopType());
        model.addAttribute("topTag",tagService.findTopTag());
        model.addAttribute("recommendBlogs",blogService.findRecommendBlog());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1",value = "page")Integer page,
                         @RequestParam(defaultValue = "5",value = "size") Integer size,
                         @RequestParam String query, Model model){
        PageInfo<Blog> searchBlog = blogService.getSearchBlog(page,size,query);
        model.addAttribute("pageInfo", searchBlog);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String toBlog(@PathVariable Long id, Model model){
        blogService.addView(id);
        Blog blog = blogService.findDetailedBlog(id);
        model.addAttribute("comments",commentService.getCommentByBlogId(id));
        model.addAttribute("blog", blog);
        return "blog";
    }

}

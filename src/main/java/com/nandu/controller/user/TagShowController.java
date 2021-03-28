package com.nandu.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Blog;
import com.nandu.pojo.Tag;
import com.nandu.service.BlogService;
import com.nandu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable Long id,
                        @RequestParam(defaultValue = "1",value = "page")Integer page,
                        @RequestParam(defaultValue = "5",value = "size") Integer size,                        Model model){
        List<Tag> tags = tagService.findBlogTag();
        //-1从导航点过来的
        if (id == -1){
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("pageInfo", blogService.findByTagId(page,size,id));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}

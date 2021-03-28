package com.nandu.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Blog;
import com.nandu.pojo.Type;
import com.nandu.service.BlogService;
import com.nandu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable Long id,
                        @RequestParam(defaultValue = "1",value = "page")Integer page,
                        @RequestParam(defaultValue = "5",value = "size") Integer size,
                        Model model){
        List<Type> types = typeService.findBlogType();
        //-1从导航点过来的
        if (id == -1){
            id = types.get(0).getId();
        }
        model.addAttribute("types", types);
        model.addAttribute("pageInfo",  blogService.findByTypeId(page,size,id));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}

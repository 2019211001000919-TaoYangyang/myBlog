package com.nandu.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Tag;
import com.nandu.pojo.Type;
import com.nandu.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(@RequestParam(defaultValue = "1",value = "page")Integer page,
                        @RequestParam(defaultValue = "5",value = "size") Integer size,
                        Model model){
        PageInfo<Tag> tagPageInfo = tagService.findForPage(page, size);
        model.addAttribute("tagPageInfo",tagPageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toInputPage(){
        return "admin/tags-input";
    }

    //增加tag
    @PostMapping("/tags/add")
    public String InputFormHandler(Tag tag, RedirectAttributes attributes, BindingResult result){
        Tag existTag=tagService.findTagbyName(tag.getName());
        if(existTag!=null){
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/tags/input";
        }
        if(tagService.addTag(tag) != 0){
            attributes.addFlashAttribute("message","添加成功！");
        } else {
            attributes.addFlashAttribute("message","添加失败！请重试");
        }
        return "redirect:/admin/tags";
    }

    //到tag更新页面
    @GetMapping("/tags/{id}/input")
    public String toUpdatePage(@PathVariable Long id, Integer page,Model model) {
        model.addAttribute("tag", tagService.findTagbyId(id));
        model.addAttribute("page",page);
        return "admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String updateFormHandler(Tag tag,Integer page, RedirectAttributes attributes) {
        if (tagService.updateTag(tag) != 0) {
            attributes.addFlashAttribute("message", "更新成功");
        } else {
            attributes.addFlashAttribute("message", "更新失败，请重试");
        }
        return "redirect:/admin/tags?page=" + page;
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        if(tagService.delTag(id)!=0){
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/tags";
    }
}

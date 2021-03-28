package com.nandu.controller.admin;

import com.github.pagehelper.PageInfo;
import com.nandu.pojo.Type;
import com.nandu.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypesController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@RequestParam(defaultValue = "1",value = "page")Integer page,
                        @RequestParam(defaultValue = "5",value = "size") Integer size,
                        Model model){
        PageInfo<Type> typePageInfo = typeService.findForPage(page, size);
        model.addAttribute("typePageInfo",typePageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toInputPage(){
        return "admin/types-input";
    }


    @PostMapping("/types/add")
    public String InputFormHandler(Type type, RedirectAttributes attributes, BindingResult result){
        Type existType=typeService.findTypebyName(type.getName());
        if(existType!=null){
            attributes.addFlashAttribute("message", "不能添加重复的类");
            return "redirect:/admin/types/input";
        }
        if(typeService.addType(type) != 0){
            attributes.addFlashAttribute("message","添加成功！");
        } else {
            attributes.addFlashAttribute("message","添加失败！请重试");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String toUpdatePage(@PathVariable Long id, Integer page,Model model) {
        model.addAttribute("type", typeService.findTypebyId(id));
        model.addAttribute("page",page);
        return "admin/types-update";
    }

    @PostMapping("/types/update")
    public String updateFormHandler(Type type,Integer page, RedirectAttributes attributes) {
        if (typeService.updateType(type) != 0) {
            attributes.addFlashAttribute("message", "更新成功");
        } else {
            attributes.addFlashAttribute("message", "更新失败，请重试");
        }
        return "redirect:/admin/types?page=" + page;
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        if(typeService.delType(id)!=0){
            attributes.addFlashAttribute("message", "删除成功");
        }
        return "redirect:/admin/types";
    }


}

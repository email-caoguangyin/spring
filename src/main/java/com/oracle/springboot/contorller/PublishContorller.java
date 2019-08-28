package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublishContorller {
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String publish(Model model, Question question){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        if(question.getTitle()==null||question.getTitle()==""){
            model.addAttribute("error","标题不能为空");
            return "/publish";
        }
        if(question.getDescription()==null||question.getDescription()==""){
            model.addAttribute("error","内容不能为空");
            return "/publish";
        }
        if(question.getTag()==null||question.getTag()==""){
            model.addAttribute("error","标签不能为空");
            return "/publish";
        }
        return "index";
    }
}

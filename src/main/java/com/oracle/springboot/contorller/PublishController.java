package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.CommentModel2;
import com.oracle.springboot.cache.TagCache;
import com.oracle.springboot.enums.QuestionStatusEnum;
import com.oracle.springboot.exception.CustomizeErrorCode;
import com.oracle.springboot.exception.CustomizeException;
import com.oracle.springboot.bean.QuestionModel;
import com.oracle.springboot.pojo.Question;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.CommentService;
import com.oracle.springboot.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/** 问题处理
 *
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    /**
     *
     * @return 进行发布问题页面
     */
    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    /**
     *
     * @param model
     * @param question
     * @param request
     * @return 发布问题
     */
    @PostMapping("/publish")
    public String publish(Model model, Question question, HttpServletRequest request){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("tags", TagCache.get());
        User user=(User)request.getSession().getAttribute("user");
        if (yanzhen(question, model, user)) return "/publish";

        question.setCreatedate(new Date());
        question.setDianzan(0);
        question.setPinglun(0);
        question.setYuedu(0);
        question.setCreateuser(user.getId());
        question.setStatus(QuestionStatusEnum.QUESTION_EXIST.getType());
        questionService.addQuestion(question);

        return "redirect:/";
    }

    /**
     *u
     * @param id
     * @param model
     * @return 问题详情
     */
    @GetMapping("/question/{id}")
    public String  doQuestion(@PathVariable(name="id") Long id,Model model){
        questionService.updateYueDu(id);
        QuestionModel questionModel=questionService.getQuestionById(id);
        List<QuestionModel> questionModelList=questionService.selectTag(questionModel);
        if (questionModel ==null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        List<CommentModel2> commentList=commentService.getCommentByParentId(id);
        model.addAttribute("comment" ,commentList);
        model.addAttribute("question",questionModel);
        //相关问题
        model.addAttribute("questionModelList",questionModelList);

        model.addAttribute("tags", TagCache.get());
        return "question";
    }

    /**
     *
     * @param id
     * @param model
     * @return 进行修改问题页面
     */
    @GetMapping("/updateQuestion/{id}")
    public String updateQuestion(@PathVariable(name = "id")Long id,Model model){
       QuestionModel questionModel=questionService.getQuestionById(id);
        model.addAttribute("title",questionModel.getTitle());
        model.addAttribute("description",questionModel.getDescription());
        model.addAttribute("tag",questionModel.getTag());
        model.addAttribute("id",questionModel.getId());
        model.addAttribute("tags", TagCache.get());
        return "updatequestion";
    }

    /**
     *
     * @param question
     * @return 修改问题
     */
    @PostMapping("/doUpdateQuestion")
    public String doUpdateQuestion(Question question ,Model model,HttpServletRequest request){
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("tags", TagCache.get());
        User user= (User) request.getSession().getAttribute("user");
        if (yanzhen(question, model, user)) return "/updatequestion";

        questionService.UpdateQuestion(question);
        return "redirect:/";
    }

    private boolean yanzhen(Question question, Model model, User user) {
        if (user ==null){
            model.addAttribute("error","用户未登录！");
            return true;
        }
        if(question.getTitle()==null||question.getTitle()==""){
            model.addAttribute("error","标题不能为空");
            return true;
        }
        if(question.getDescription()==null||question.getDescription()==""){
            model.addAttribute("error","内容不能为空");
            return true;
        }
        if(question.getDescription().length() >= 1500){
            model.addAttribute("error","内容太长！");
            return true;
        }
        if(question.getTag()==null||question.getTag()==""){
            model.addAttribute("error","标签不能为空");
            return true;
        }
        String invalid = TagCache.filterInvalid(question.getTag());
        if (StringUtils.isNotBlank(invalid)){
            model.addAttribute("error","非法标签"+ invalid);
            return true;
        }
        return false;
    }



}

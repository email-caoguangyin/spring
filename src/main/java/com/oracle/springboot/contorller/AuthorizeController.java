package com.oracle.springboot.contorller;

import com.oracle.springboot.bean.AccessTokenPojo;
import com.oracle.springboot.bean.GithubUser;
import com.oracle.springboot.pojo.User;
import com.oracle.springboot.provider.GithubProvider;
import com.oracle.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/** 从github 获取个人信息
 *
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clinentId;

    @Value("${github.client.secret}")
    private String clientsSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private UserService userService;


    /**
     *
     * @param code
     * @param state
     * @param request
     * @param model
     * @return 从github上获取个人信息
     */
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request, Model model, HttpServletResponse response){
        AccessTokenPojo accessTokenPojo=new AccessTokenPojo();
        accessTokenPojo.setClient_id(clinentId);
        accessTokenPojo.setClient_secret(clientsSecret);
        accessTokenPojo.setCode(code);
        accessTokenPojo.setState(state);
        accessTokenPojo.setRedirect_uri(redirectUri);
        String accessToken=githubProvider.getAccessToken(accessTokenPojo);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        if(githubUser!=null){
            User user=new User();
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setDianzan(0);
            user.setLoginname(githubUser.getId());
            userService.UserUpdateOrCreate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
           /* List<User> userList= userService.getUserByLongName(githubUser.getId());
            request.getSession().setAttribute("user",userList.get(0));*/
            model.addAttribute("token",user.getToken());
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }


    /**
     *
     * @param request
     * @param response
     * @return 登出
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";

    }
}

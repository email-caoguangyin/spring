package com.oracle.springboot.contorller;

import com.oracle.springboot.pojo.AccessTokenPojo;
import com.oracle.springboot.pojo.GithubUser;
import com.oracle.springboot.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeContorller {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clinentId;

    @Value("${github.client.secret}")
    private String clientsSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        AccessTokenPojo accessTokenPojo=new AccessTokenPojo();
        accessTokenPojo.setClient_id(clinentId);
        accessTokenPojo.setClient_secret(clientsSecret);
        accessTokenPojo.setCode(code);
        accessTokenPojo.setState(state);
        accessTokenPojo.setRedirect_uri(redirectUri);
        String accessToken=githubProvider.getAccessToken(accessTokenPojo);
        GithubUser githubUser=githubProvider.getUser(accessToken);
        System.out.println(githubUser.getName());
        if(githubUser!=null){
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }

    }
}

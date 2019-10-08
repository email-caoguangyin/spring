package com.oracle.springboot.interceptor;

import com.oracle.springboot.pojo.User;
import com.oracle.springboot.service.NotificationService;
import com.oracle.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies=request.getCookies();
        if (cookies !=null && cookies.length!=0){
            for (Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token=cookie.getValue();
                    List<User> user=userService.getUserByToken(token);
                    if(user.get(0) !=null){
                        request.getSession().setAttribute("user",user.get(0));
                        //通知数
                        Integer unreadCount = notificationService.unread(user.get(0));
                        request.getSession().setAttribute("unreadCount", unreadCount);

                    }

                    break;
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

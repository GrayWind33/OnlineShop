package graywind.shop.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import graywind.shop.bean.SessionData;
import graywind.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.lang.reflect.Method;
import java.util.Optional;

import static graywind.shop.interceptor.Constants.MOBILE_NUMBER_SESSION_KEY;
import static graywind.shop.interceptor.Constants.SESSION_KEY;
import static graywind.shop.interceptor.Constants.USER_CODE_SESSION_KEY;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserService userSvc;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        logger.info("------preHandle------");
        // 获取session
        HttpSession session = request.getSession(true);
        // 判断用户ID是否存在，不存在就跳转到登录界面
        if (session.getAttribute("username") == null) {
            logger.info("------:跳转到login页面！");
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        } else {
            session.setAttribute("username", session.getAttribute("username"));
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }
}

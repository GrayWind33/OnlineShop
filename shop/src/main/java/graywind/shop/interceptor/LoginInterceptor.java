package graywind.shop.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import graywind.shop.bean.SessionData;
import graywind.shop.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Optional;

import static graywind.shop.interceptor.Constants.MOBILE_NUMBER_SESSION_KEY;
import static graywind.shop.interceptor.Constants.SESSION_KEY;
import static graywind.shop.interceptor.Constants.USER_CODE_SESSION_KEY;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final static String SESSION_KEY_PREFIX = "session:";

    @Autowired
    private UserService userSvc;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String username = (String) request.getSession().getAttribute("username");
        if (Optional.ofNullable(username).map(String::length).orElse(0) > 0) {
            return true;
        }

        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        handlerSession(request);

        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();
        if (clazz.isAnnotationPresent(Auth.class) || method.isAnnotationPresent(Auth.class)) {
            if (request.getAttribute(USER_CODE_SESSION_KEY) == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            } else {
                return true;
            }
        }
        return true;

    }

    public void handlerSession(HttpServletRequest request) {
        String sessionId = request.getHeader(SESSION_KEY);
        if (Optional.ofNullable(sessionId).map(String::length).orElse(0) == 0) {
            sessionId = (String) request.getSession().getAttribute(SESSION_KEY);
        }
        if (Optional.ofNullable(sessionId).map(String::length).orElse(0) > 0) {
            SessionData model = userSvc.getSession(SESSION_KEY_PREFIX + sessionId);
            if (model == null) {
                return;
            }
            request.setAttribute(SESSION_KEY, sessionId);
            Integer userCode = model.getUserCode();
            if (userCode != null) {
                request.setAttribute(USER_CODE_SESSION_KEY, Long.valueOf(userCode));
            }
            String mobile = model.getMobileNumber();
            if (mobile != null) {
                request.setAttribute(MOBILE_NUMBER_SESSION_KEY, mobile);
            }
        }
        return;
    }
}

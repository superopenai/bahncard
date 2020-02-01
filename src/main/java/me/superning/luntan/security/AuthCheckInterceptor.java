package me.superning.luntan.security;
import me.superning.luntan.domain.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验拦截器 拦截所有http请求
 * @author superning
 */
@Component
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        /** 根据token的key 取到的token_value*/
        String token = request.getHeader(Constants.TOKEN_KEY);

        if (StringUtils.isEmpty(token))
        {
            throw new Exception("Header中缺少"+ Constants.TOKEN_KEY+"!!!!!");

        }
        if (!token.equals(Constants.TOKEN_VALUE))
        {
            throw new Exception("Header中"+ Constants.TOKEN_KEY+ "错误!");
        }

        AccessContext.setToken(token);
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        AccessContext.clearTokenKey();
    }
}

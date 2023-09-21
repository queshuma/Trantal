package com.shuzhi.system.config;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.JWTClaimsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/9/20
 *
 * @version 1.0
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        try {
            for (Cookie c:httpServletRequest.getCookies()) {
                JWSObject jwsObject = JWSObject.parse(c.getValue());
                Payload payload = jwsObject.getPayload();

                JWTClaimsSet claimsSet = JWTClaimsSet.parse(payload.toJSONObject());

                String userId = claimsSet.getSubject();
                return true;
            }
        }catch (Exception e) {
                logger.error("ERROR: " + e);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        try {

        } catch (Exception e) {

        }
    }


}

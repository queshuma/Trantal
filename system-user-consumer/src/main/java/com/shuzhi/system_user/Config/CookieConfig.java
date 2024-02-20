package com.shuzhi.system_user.Config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/9/19
 *
 * @version 1.0
 */

public class CookieConfig {
    public static final void setClientCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String info, String token) {
        if (httpServletRequest.getCookies() != null) {
            for (Cookie c:httpServletRequest.getCookies()) {
                c.setMaxAge(0);
                c.setPath("/");
                httpServletResponse.addCookie(c);
            }
        }
        Cookie cookie = new Cookie(info, token);
        cookie.setMaxAge(36000000);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    public static final void delClientCookie(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        for (Cookie c:httpServletRequest.getCookies()) {
            c.setMaxAge(0);
            c.setPath("/");
            httpServletResponse.addCookie(c);
        }

    }
}

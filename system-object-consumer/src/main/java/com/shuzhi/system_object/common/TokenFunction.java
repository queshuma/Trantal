package com.shuzhi.system_object.common;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jwt.JWTClaimsSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/2/8
 *
 * @version 1.0
 */

public class TokenFunction {
    public static Long tokenGetUserId(HttpServletRequest httpServletRequest) throws ParseException {
    String token = null;
    for (Cookie c:httpServletRequest.getCookies()) {
        if (c.getName().equals("token")) {
            token = c.getValue();
            break;
        }
    }
    if(token != null) {
        JWSObject jwsObject = JWSObject.parse(token);
        Payload payload = jwsObject.getPayload();

        JWTClaimsSet claimsSet = JWTClaimsSet.parse(payload.toJSONObject());

        String userId = claimsSet.getSubject();
        System.out.println(Long.valueOf(userId));
        return Long.valueOf(userId);
    }
    return null;

}

}

package com.shuzhi.system_user.Config;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2023/9/17
 *
 * @version 1.0
 */

public class JwtConfig {

    //设置默认存活时间为10小时
    public static String jwtGenerator(String userId, String userName, String userAccount, long userLevel) throws NoSuchAlgorithmException, JOSEException {
        // 生成RSA密钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();


        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();

        // 将公钥转换为Base64字符串
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        // 打印输出公钥字符串
        System.out.println("Public Key: " + publicKeyString);


        // 创建JWT Claims
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userId)
                //签发人
                .issuer("SHUZHI")
                //过期时间
                .expirationTime(new Date(new Date().getTime() + 3600 * 10000)) // 10小时后过期
                //自定义属性
                .claim("userName", userName)
                .claim( "userAccount", userAccount)
                .claim("userLevel", userLevel)
                .jwtID(UUID.randomUUID().toString())
                .build();

        // 创建JWS头
        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
//                .keyID("Trantal")
                .build();

        // 创建JWS对象
        SignedJWT signedJWT = new SignedJWT(header, claimsSet);

        // 使用私钥进行签名
        JWSSigner signer = new RSASSASigner((RSAPrivateKey) keyPair.getPrivate());
        signedJWT.sign(signer);

        // 将JWT令牌转换为字符串
        String jwtString = signedJWT.serialize();

        return jwtString;
    }

    public static void jwtChect(String cookie) {
        try {
            // 解析 JWT
            JWSObject jwsObject = JWSObject.parse(cookie);
            Payload payload = jwsObject.getPayload();

            JWTClaimsSet claimsSet = JWTClaimsSet.parse(payload.toJSONObject());

            String userId = claimsSet.getSubject();
            System.out.println(userId);
        }catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
}

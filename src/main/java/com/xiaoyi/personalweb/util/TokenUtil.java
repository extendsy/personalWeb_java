package com.xiaoyi.personalweb.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xiaoyi.personalweb.entity.User;

import java.util.Date;

public class TokenUtil {

    private static final long EXPIRE_TIME= 60*60*1000;
    private static final String TOKEN_SECRET="extendsMYY";  //密钥盐


    /**
     * 签名生成
     * @param user
     * @return
     */
    public static String sign(User user){

        String token = null;

        try {
            Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("userId", user.getId().toString())
                    .withExpiresAt(expiresAt)
                    // 使用了HMAC256加密算法。
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 签名验证
     * @param token
     * @return
     */
    public static boolean verify(String token){


        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("issuer: " + jwt.getIssuer());
            System.out.println("userId: " + jwt.getClaim("userId").asString());
            System.out.println("过期时间：" + jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 通过token获取用户id
     * @param token 用户token
     * @return 用户id
     */
    public static String getUserInfoByToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("userId").asString();
    }
}

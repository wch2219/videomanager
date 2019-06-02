package com.example.servicemanager.utils;

import org.apache.commons.codec.digest.Sha2Crypt;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;

public class DefaultTokenGenerate implements TokenGenerate {
    //利用spring注入request
    @Autowired
    private HttpServletRequest request;
    private static RsaJsonWebKey rsaJsonWebKey;

    //生成秘钥工具类
    static{
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String create(String username) {
        String token = null;
        try {
            //此处用到了apache commons codec的依赖
            //subject参数 此处使用了登陆者的ip地址，从而避免Token被盗用请求
            token = TokenUtils.create(Sha2Crypt.sha256Crypt(username.getBytes()),
                    "csdn.com",
                    DigestUtils.md5DigestAsHex(username.getBytes()),
                    request.getRemoteAddr(),
                    30, 2);
            /*
             *注意：此处省略了存储进Redis的过程 键值对分别为 Token:username
             */
        } catch (JoseException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public boolean valid(String token) throws InvalidJwtException {
        /*
         *注意：此处省略了从Redis中验证Token的存在以及根据Token取出username的过程
         */
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // the JWT must have an expiration time
                .setMaxFutureValidityInMinutes(60) // but the  expiration time can't be too crazy
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setExpectedIssuer("csdn.com") // whom the JWT needs to have been issued by
                .setExpectedAudience("username") // to whom the JWT is intended for
                .setRequireSubject()
                .setRequireNotBefore()
                .setExpectedSubject(request.getRemoteAddr())
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance
        //此处若验证失败 会抛出异常
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);
        //若无异常出现 则返回true
        return true;
    }
}

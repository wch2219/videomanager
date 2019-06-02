package com.example.servicemanager.utils;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

public class TokenUtils {
    private static RsaJsonWebKey rsaJsonWebKey;

    //生成秘钥工具类
    static{
        try {
            rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
        } catch (JoseException e) {
            e.printStackTrace();
        }
    }

    public static boolean volidateToken(String token) {
        return true;
    }
    /**
     *
     * @param keyID
     * @param issuer 发行者
     * @param audience 接受者
     * @param exprationTime 过期时间
     * @param notBeforeMinutesInThePast 过去有效时间
     * @return
     * @throws JoseException
     */
    public static String create(String keyID,String issuer,String audience,String subject,int exprationTime,int notBeforeMinutesInThePast) throws JoseException{


        rsaJsonWebKey.setKeyId(keyID);

        JwtClaims claims = new JwtClaims();
        //token发行者
        claims.setIssuer(issuer);
        claims.setAudience(audience); // to whom the token is intended to be sent
        //过期时间为现在起后30分钟
        claims.setExpirationTimeMinutesInTheFuture(30);

        claims.setGeneratedJwtId(); // a unique identifier for the token
        claims.setIssuedAtToNow();  // when the token was issued/created (now)
        claims.setSubject(subject);
        //考虑到时间偏差
        //设置在过去的两分钟内 token仍然有效，两分钟前无效
        claims.setNotBeforeMinutesInThePast(notBeforeMinutesInThePast);

        // A JWT is a JWS and/or a JWE with JSON claims as the payload.
        // In this example it is a JWS so we create a JsonWebSignature object.
        JsonWebSignature jws = new JsonWebSignature();

        jws.setPayload(claims.toJson());
        //设置私钥
        jws.setKey(rsaJsonWebKey.getPrivateKey());

        jws.setKeyIdHeaderValue(rsaJsonWebKey.getKeyId());

        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = jws.getCompactSerialization();
        return jwt;
    }
    //验证方法
    public static boolean valid(String token,String issuer,String audience,String subject) throws InvalidJwtException {
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireExpirationTime() // 必须有过期时间
                .setMaxFutureValidityInMinutes(60) // but the  expiration time can't be too crazy
                .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
                .setExpectedIssuer(issuer) // whom the JWT needs to have been issued by
                .setExpectedAudience(audience) // to whom the JWT is intended for
                .setRequireSubject()
                .setRequireNotBefore()
                .setExpectedSubject(subject)
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance
        JwtClaims jwtClaims = jwtConsumer.processToClaims(token);

        return true;
    }
}

package com.ssafy.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.rsocket.RSocketSecurity;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * jwt 토큰 유틸 정의.
 */
@Component
public class JwtTokenUtil {
    private static String secretKey;
    private static Integer expirationTime;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ssafy.com";
    
    @Autowired
	public JwtTokenUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") Integer expirationTime) {
		this.secretKey = secretKey;
		this.expirationTime = expirationTime;
	}
    
	public void setExpirationTime() {
    		//JwtTokenUtil.expirationTime = Integer.parseInt(expirationTime);
    		JwtTokenUtil.expirationTime = expirationTime;
	}

	public static JWTVerifier getVerifier() {
        return JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();
    }

    /*

    토큰을 발급해야 하는 대상은  어드민, 상담사 , 데스크인데
    .withAudience()    토큰 대상자를 설정하는건데   여기에  어드민 상담사 데스크를 구분하게끔 넣어도 되는건지?????
    1은 어드민  2는 상담사  3은 데스크 이런식으로..

     */


//
//    public static String getToken(String userId) {
//    		Date expires = JwtTokenUtil.getTokenExpiration(expirationTime);
//        return JWT.create()
//                .withSubject(userId)  //기본키
//                .withAudience("1")
//                .withExpiresAt(expires)
//                .withIssuer(ISSUER)
//                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
//                .sign(Algorithm.HMAC512(secretKey.getBytes()));
//    }



    public static String getToken(String userId,String role) {
        Date expires = JwtTokenUtil.getTokenExpiration(expirationTime);

        return JWT.create()
                .withClaim("role",role)  //payload에 추가하기
                .withSubject(userId)  //기본키
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }




    public static Date getTokenExpiration(int expirationTime) {
    		Date now = new Date();
    		return new Date(now.getTime() + expirationTime);
    }

    public static void handleError(String token) {
        JWTVerifier verifier = JWT
                .require(Algorithm.HMAC512(secretKey.getBytes()))
                .withIssuer(ISSUER)
                .build();

        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void handleError(JWTVerifier verifier, String token) {
        try {
            verifier.verify(token.replace(TOKEN_PREFIX, ""));
        } catch (AlgorithmMismatchException ex) {
            throw ex;
        } catch (InvalidClaimException ex) {
            throw ex;
        } catch (SignatureGenerationException ex) {
            throw ex;
        } catch (SignatureVerificationException ex) {
            throw ex;
        } catch (TokenExpiredException ex) {
            throw ex;
        } catch (JWTCreationException ex) {
            throw ex;
        } catch (JWTDecodeException ex) {
            throw ex;
        } catch (JWTVerificationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        }
    }
}

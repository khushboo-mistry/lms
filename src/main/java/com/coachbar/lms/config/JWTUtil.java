//package com.coachbar.lms.config;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//@Component
//public class JWTUtil {
//
//    
//    @Value("${app.jwtSecret}")
//    private String secret;
//    @Value("${app.jwtExpirationInMs}")
//    private int jwtExpirationInMs;
//
//    public String generateToken(String email, Long customerId) throws IllegalArgumentException, JWTCreationException {
//        return JWT.create()
//                .withSubject("User Details")
//                .withClaim("email", email)
//                .withClaim("customer", customerId)
//                .withIssuedAt(new Date())
//                .withIssuer("coachbar")
//                .sign(Algorithm.HMAC256(secret));
//    }
//
//    private DecodedJWT verifyJwtToken(String token) {
//		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
//                .withSubject("User Details")
//                .withIssuer("coachbar")
//                .build();
//        DecodedJWT jwt = verifier.verify(token);
//		return jwt;
//	}
//    
//    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
//        DecodedJWT jwt = verifyJwtToken(token);
//        return jwt.getClaim("email").asString();
//    }
//
//    public Long validateTokenAndRetrieveCustomerId()throws JWTVerificationException {
//    	String token = SecurityUtils.getAuthorizationToken();
//    	if(token == null) {
//    		throw new JWTVerificationException("Invalid JWT Token in Bearer Header");
//    	}
//        DecodedJWT jwt = verifyJwtToken(token);
//        return jwt.getClaim("customer").asLong();
//    }
//
//}
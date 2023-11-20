package com.project.springecommerceapi.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import com.project.springecommerceapi.common.AppConstants;
import com.project.springecommerceapi.common.UserPrincipal;
import com.project.springecommerceapi.enumeration.TokenType;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtTokenService {

    @Value("${jwt_secret}")
    private String jwtSecret;

    public String generateToken(UserPrincipal userPrincipal, TokenType tokenType) {
        String[] claims = getClaimsFromUser(userPrincipal);
        return JWT.create()
                .withSubject(userPrincipal.getUsername())
                .withArrayClaim(AppConstants.AUTHORITIES, claims)
                .withClaim("tokenType", tokenType.name())
                .withIssuedAt(new Date())
                .withExpiresAt(calculateExpiration(tokenType))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public Date calculateExpiration(TokenType tokenType) {
        switch (tokenType) {
            case AUTHENTICATION_TOKEN:
                return new Date(System.currentTimeMillis() + AppConstants.JWT_AUTHENTICATION_EXPIRATION);
            case EMAIL_VERIFICATION_TOKEN:
                return new Date(System.currentTimeMillis() + AppConstants.JWT_VERIFY_EMAIL_EXPIRATION);
            case PASSWORD_RESET_TOKEN:
                return new Date(System.currentTimeMillis() + AppConstants.JWT_RESET_PASSWORD_EXPIRATION);
            default:
                return new Date(System.currentTimeMillis() + AppConstants.JWT_AUTHENTICATION_EXPIRATION);
        }
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        String[] claims = getClaimsFromToken(token);
        return Arrays.stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public String getSubjectFromToken(String token) {
        JWTVerifier jwtVerifier = getJwtVerifier();
        return jwtVerifier.verify(token).getSubject();
    }

    public Boolean isTokenValid(String email, String token, TokenType expectedTokenType) {
        JWTVerifier jwtVerifier = getJwtVerifier();
        return StringUtils.isNotEmpty(email) && !isTokenExpired(jwtVerifier, token)
                && hasExpectedTokenType(jwtVerifier, token, expectedTokenType);
    }

    private Boolean isTokenExpired(JWTVerifier jwtVerifier, String token) {
        Date expiration = jwtVerifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private Boolean hasExpectedTokenType(JWTVerifier jwtVerifier, String token, TokenType expectedTokenType) {
        String tokenType = jwtVerifier.verify(token).getClaim("tokenType").asString();
        return tokenType.equals(expectedTokenType.name());
    }

    private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
        List<String> authorities = new ArrayList<>();
        userPrincipal.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));
        return authorities.toArray(new String[0]);
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier jwtVerifier = getJwtVerifier();
        return jwtVerifier.verify(token).getClaim(AppConstants.AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJwtVerifier() {
        JWTVerifier jwtVerifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(jwtSecret);
            jwtVerifier = JWT.require(algorithm).build();
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(AppConstants.TOKEN_UNVERIFIABLE);
        }
        return jwtVerifier;
    }
}

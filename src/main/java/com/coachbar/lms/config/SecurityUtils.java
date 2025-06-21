//package com.coachbar.lms.config;
//
//import java.util.Optional;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//public final class SecurityUtils {
//
//    public static String getXApiKey() {
//
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        String token =
//                ((ServletRequestAttributes) requestAttributes)
//                        .getRequest().getHeader("x-api-key");
//        if (StringUtils.hasText(token)) {
//            return token;
//        }
//        return null;
//    }
//    
//    /*
//	*/
//    public static String getAuthorizationToken() {
//        String token =
//                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
//                        .getRequest().getHeader(HttpHeaders.AUTHORIZATION);
//        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
//            return token.substring(7);
//        }
//        return null;
//    }
//    
//    public static String getEmailFromAuthorizationToken() {
//		AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
//			    SecurityContextHolder.getContext().getAuthentication();
//        return auth.getPrincipal().toString();
//    }
//
//    public static boolean isCurrentUserAuthenticated() {
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        return Optional.ofNullable(securityContext.getAuthentication())
//                .map(authentication -> authentication.getAuthorities().stream()
//                        .noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ANONYMOUS")))
//                .orElse(false);
//    }
//
//
//}

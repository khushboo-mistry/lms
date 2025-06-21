//package com.coachbar.lms.config;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.auth0.jwt.exceptions.JWTVerificationException;
//
//@Component
//public class JWTFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//    
//    @Autowired 
//    private JWTUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain) throws ServletException, IOException {
//		
//    	boolean checkForV2Apis = request.getRequestURI().contains("/v2");
//		if(checkForV2Apis) {
//	    	boolean checkForV2SwaggerOpenApis = request.getRequestURI().contains("/v2/api-docs") || request.getRequestURI().contains("/v2/active");
//	    	if(!checkForV2SwaggerOpenApis) {
//	    		String authHeader = request.getHeader("Authorization");
//	    		if(authHeader != null && !authHeader.isEmpty() && authHeader.startsWith("Bearer ")){
//	    			String jwt = authHeader.substring(7);
//	    			if(jwt == null || jwt.isEmpty()){
//	    				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
//	    			}else {
//	    				try{
//	    					String email = jwtUtil.validateTokenAndRetrieveSubject(jwt);
//	    					UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//	    					UsernamePasswordAuthenticationToken authToken =
//	    							new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
//	    					if(SecurityContextHolder.getContext().getAuthentication() == null){
//	    						SecurityContextHolder.getContext().setAuthentication(authToken);
//	    					}
//	    				}catch(JWTVerificationException exc){
//	    					response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
//	    				}
//	    			}
//	    		}else {
//	    			String aesHeader = request.getHeader("x-api-key");
//	    			if(aesHeader == null) {
//	    				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//	    			}
//	    		}
//	    	}
//    	}
//        filterChain.doFilter(request, response);
//    }
//}
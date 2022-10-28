//package com.wen.richresource.security;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtTokenAuthenticationFilter extends GenericFilterBean {
//
//    private JwtUtil JwtUtil;
//
//    public JwtTokenAuthenticationFilter(JwtUtil JwtUtil) {
//        this.JwtUtil = JwtUtil;
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
//            throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
///*
//        try {
//            String token = JwtUtil.resolveToken(request);
//            if (token != null && JwtUtil.validateToken(token)) {
//                Authentication auth = JwtUtil.getAuthentication(token);
//
//                if (auth != null) {
//                    SecurityContextHolder.getContext().setAuthentication(auth);
//                }
//            }
//        } catch (InvalidJwtAuthenticationException e) {
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.getWriter().write("Invalid token");
//            response.getWriter().flush();
//            return;
//        }*/
//        filterChain.doFilter(req, res);
//    }
//}

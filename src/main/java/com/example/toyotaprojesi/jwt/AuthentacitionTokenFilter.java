package com.example.toyotaprojesi.jwt;

import com.example.toyotaprojesi.Service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthentacitionTokenFilter extends OncePerRequestFilter {


    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            System.out.println("AAAAA");
            String jwtToken = jwtParser(request);
            System.out.println(jwtToken);

            if (jwtToken != null && jwtProvider.validateJwtToken(jwtToken)) {
                System.out.println("AAAAA");

                String username = jwtProvider.getUsernameFromJwtToken(jwtToken);
                System.out.println("AAAAA");

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                System.out.println("AAAAA");

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("AAAAA");

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            logger.error("CANNOT", e);
        }
        filterChain.doFilter(request, response);
    }

    private String jwtParser(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth!=null && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }
        return null;
    }
}

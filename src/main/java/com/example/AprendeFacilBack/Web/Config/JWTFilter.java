package com.example.AprendeFacilBack.Web.Config;

import com.example.AprendeFacilBack.Domain.services.JWTService;
import com.example.AprendeFacilBack.Domain.services.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailService userDetailService;

    @Autowired
    public JWTFilter(JWTService jwtService, UserDetailService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7).trim();
        try {
            if (!jwtService.isValid(token)) {
                filterChain.doFilter(request, response);
                return;
            }

            String userName = jwtService.getUsername(token);

            User user = (User) userDetailService.loadUserByUsername(userName);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    user.getUsername(), user.getPassword(), user.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        filterChain.doFilter(request, response);
    }
}

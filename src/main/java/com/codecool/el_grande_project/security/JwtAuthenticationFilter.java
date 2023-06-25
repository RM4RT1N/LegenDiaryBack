package com.codecool.el_grande_project.security;

import com.codecool.el_grande_project.repository.TokenRepository;
import com.codecool.el_grande_project.service.CustomUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private  JwtGenerator jwtGenerator;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private TokenRepository tokenRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
            String token = getJwtFromRequest(request);
            List<String> blacklistedTokens = tokenRepository.findAll().stream().map(token1 -> token1.getToken()).toList();
            if (blacklistedTokens.contains(token)){
                throw new ServletException("Token forbiden");
            }else {
                if (StringUtils.hasText(token) && jwtGenerator.isTokenValid(token)){
                    String username = jwtGenerator.getUserNameFromJwt(token);

                    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request,response);
            }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}

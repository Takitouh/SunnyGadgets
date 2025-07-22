package com.SunnyGadgetsProject.SunnyGadgets_v1.security.config.filters;

import com.SunnyGadgetsProject.SunnyGadgets_v1.utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtTokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(token != null) {
            DecodedJWT decodedJWT = jwtUtils.verifyToken(token.substring(7));
            String username = jwtUtils.getUsernameFromToken(decodedJWT);
            String auth = jwtUtils.getSpecificClaimFromToken(decodedJWT, "authorities").asString();

            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auth);
            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request, response);

    }
}

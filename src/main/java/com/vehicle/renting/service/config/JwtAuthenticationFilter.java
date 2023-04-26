package com.vehicle.renting.service.config;

import com.vehicle.renting.service.healper.JwtUtil;
import com.vehicle.renting.service.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
 /*   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

     //validate
        String requestTokenHeader= request.getHeader("Authentication");
        String username=null;
        String jwttoken=null;
    //null and formate
        if(requestTokenHeader!=null&&requestTokenHeader.startsWith("Bearer "))
        {
            jwttoken=requestTokenHeader.substring(7);
            try
            {
              username= this.jwtUtil.getUsernameFromToken(jwttoken);
            }
            catch(Exception e)
            {
               e.printStackTrace();
            }
            UserDetails userDetails=this.customUserDetailsService.loadUserByUsername(username);

            //security
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
            {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
            else
            {
                System.out.print("token is not validated");
            }

        }
        filterChain.doFilter(request,response);

    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String jwt = parseJwt(req);

        if (jwt != null && jwtUtil.validateToken(jwt)) {
            String username = jwtUtil.getUsernameFromToken(jwt);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(req,res);

    }

    private String parseJwt(HttpServletRequest request){
        String header =request.getHeader("Authorization");

        if(header!=null && header.startsWith("Bearer ")){
            return header.substring(7,header.length());
        }
        return null;
    }
}

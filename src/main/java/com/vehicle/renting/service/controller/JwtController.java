package com.vehicle.renting.service.controller;

import com.vehicle.renting.service.healper.JwtUtil;
import com.vehicle.renting.service.model.JwtRequest;
import com.vehicle.renting.service.model.JwtResponse;
import com.vehicle.renting.service.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/token")
public class JwtController {

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate")
public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
{
      System.out.println("data is get from the user");
      try
      {
          this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUserName(),jwtRequest.getUserPassword()));
      }
      catch(UsernameNotFoundException e)
      {
          e.printStackTrace();
         throw new Exception("this is bad cridencials");
      }
      catch(BadCredentialsException e)
      {
          e.printStackTrace();
          throw new Exception("bad credential");
      }
    UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUserName());
   String token=this.jwtUtil.generateToken(userDetails);  //token generate
    System.out.println(token);
    return  ResponseEntity.ok(new JwtResponse(token)); //json me  convert
}
}

package com.vehicle.renting.service.service;

import com.vehicle.renting.service.entity.Users;
import com.vehicle.renting.service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
     //   Users users = userRepo.findByuserEmail(username);
        //to get username from database:-
        Users users=userRepo.findByuserUserName(username);
        String userName = users.getUserName();
        String userPassword = users.getUserPassword();

        if (users != null) {

            return  new User(username,userPassword,new ArrayList<>());

        }
        else {

            throw new UsernameNotFoundException("user name not found");
        }
       /* if (username.equals("Durgesh"))
        {
            return new User("Durgesh", "Durgesh123", new ArrayList<>());
        } else                                                            //its hardcoded
        {
           throw new UsernameNotFoundException("user name not found");

        }*/



    }
}

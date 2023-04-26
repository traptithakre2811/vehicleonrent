package com.vehicle.renting.service.controller;

import com.vehicle.renting.service.entity.Users;
import com.vehicle.renting.service.service.UserService;
import com.vehicle.renting.service.userdto.requestdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveuser")
    public Users save(@RequestBody UserDto userDto) {
        return userService.saveUserData(userDto);
    }


//....................................................................................................
    @GetMapping("/fetchById/{id}")
    public Users fetchById(@PathVariable Long id)
    {
        return userService.fetchById(id);
    }
//.....................................................................................................
   //3 fetch by responce entity

    @GetMapping("/fetchuser")
    public ResponseEntity<List<Users>> fetchAll()
    {
        List<Users> list= userService.fetchAll();
        if(list.size()<=0)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else
        {
            return ResponseEntity.of(Optional.of(list));
        }
    }
//......................................................................................................



    @GetMapping("/fetchByResponseEntity")
    public ResponseEntity<?> fetchByEmail(@RequestParam String userEmail)
    {
        Users users=userService.fetchByEmail(userEmail);
        if(users==null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        else
        {

           // return ResponseEntity.of(Optional.of(users));//1st way
           return new ResponseEntity<Users>(users,HttpStatus.OK);//2nd way
        }
    }
//........................................................................................................
//......................................................................................................

    //Login API
//    @GetMapping("/fetchbyEMailpass/{email}/{pass}") //version 1.0 fetchEmailPass
//    public Users FetchByEmailPass(@PathVariable String email,@PathVariable String pass)
//    {
//        return userService.fetchByEmailPass(email,pass);
//    }
    @GetMapping("/fetchEmailpass")  //version 1.1 fetchEmailPass
    public String fetching(@RequestParam String email,@RequestParam String pass)
    {
        return userService.fetchByEmailPass(email,pass);
    }

//    @GetMapping("/fetchEmailpass") //version1.2 fetchEmailPass
//    public ResponseEntity<?> fetching(@RequestParam String email, @RequestParam String pass)
//    {
//        String s = userService.fetchByEmailPass(email, pass);
//        return ResponseEntity.notFound().build();
//    }
//.....................................................................................................................
//delete user by id
@DeleteMapping("/deleteUserById/{id}")
    public String deleteById(@PathVariable Long id)
    {
        return userService.deleteById(id);
    }


    @PutMapping("/update/{id}")
    public UserDto  upDateById(@RequestBody UserDto userDto,@PathVariable Long id)
    {
        UserDto users = userService.updateId(userDto, id);
        System.err.println(users.toString());
        return users;
    }
}

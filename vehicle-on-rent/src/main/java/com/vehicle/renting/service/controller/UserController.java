package com.vehicle.renting.service.controller;

import com.vehicle.renting.service.entity.Users;
import com.vehicle.renting.service.service.UserService;
import com.vehicle.renting.service.userdto.requestdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/saveuser")
    public Users save(@RequestBody UserDto userDto) {
        return userService.saveUserData(userDto);
    }

    @GetMapping("/fetchById/{id}")
    public Users fetchById(@PathVariable Long id)
    {
        return userService.fetchById(id);
    }

    @GetMapping("/fetchall")
    public List<Users> fetchALl()
    {
        return userService.fetchAll();
    }

//    @GetMapping("/fetchbyEMailpass/{email}/{pass}")
//    public Users FetchByEmailPass(@PathVariable String email,@PathVariable String pass)
//    {
//        return userService.fetchByEmailPass(email,pass);
//    }
    @GetMapping("/fetchEmailpass")
    public String fetching(@RequestParam String email,@RequestParam String pass)
    {
        return userService.fetchByEmailPass(email,pass);
    }
}

package com.vehicle.renting.service.service;

import com.vehicle.renting.service.entity.Users;
import com.vehicle.renting.service.userdto.requestdto.UserDto;

import java.util.List;

public interface UserService {

    Users saveUserData(UserDto userDto);
    Users fetchById(Long id);

    List<Users> fetchAll();

    String fetchByEmailPass(String email,String pass);




    //FetchByEmailOnly
    Users fetchByEmail(String email);

    //deleteById
    String deleteById(Long id);

    //updateByid
    UserDto updateId(UserDto userDto,Long id);

}

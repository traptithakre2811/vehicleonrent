package com.vehicle.renting.service.service.serviceimpl;

import com.vehicle.renting.service.entity.Address;
import com.vehicle.renting.service.entity.Users;
import com.vehicle.renting.service.repo.AddressRepo;
import com.vehicle.renting.service.repo.UserRepo;
import com.vehicle.renting.service.service.UserService;
import com.vehicle.renting.service.userdto.requestdto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;

    @Override
    public Users saveUserData(UserDto userDto) {
        Users users = new Users();
        users.setFirstName(userDto.getFirstName());
        users.setLastName(userDto.getLastName());
        users.setUserName(userDto.getUserName());
        users.setUserEmail(userDto.getUserEmail());
        users.setUserPassword(userDto.getUserPassword());
        users.setUserMobileNo(userDto.getUserMobileNo());

        Address address = new Address();
        address.setAdCity(userDto.getAdCity());
        address.setAddType(userDto.getAddType());

        Address address1 =addressRepo.save(address);

        users.setAddress(address1);
        return userRepo.save(users);

    }

    @Override
    public Users fetchById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<Users> fetchAll() {
        return userRepo.findAll();
    }

    @Override
    public String  fetchByEmailPass(String email, String pass)
    {
        Users users = userRepo.findByuserEmail(email);   //for the query we write only -> Users users = userRepo. findByuserEmai(email);
        if (null == users)
        {
            return  "invalid user";
        } else
        {
            if (users.getUserPassword().contains(pass))
            {
                return  "login success";
            }
        }
         return null;
      }
}

//    @Override
//    public Users fetchByEmailPass(String email, String pass) {
//        return userRepo.findByEmailPass(email,pass);
//    }



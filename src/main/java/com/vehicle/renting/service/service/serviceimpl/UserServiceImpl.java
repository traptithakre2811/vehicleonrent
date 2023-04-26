package com.vehicle.renting.service.service.serviceimpl;

import com.vehicle.renting.service.constant.Constant;
import com.vehicle.renting.service.entity.Address;
import com.vehicle.renting.service.entity.Users;
import com.vehicle.renting.service.exception.EmptyInputException;
import com.vehicle.renting.service.repo.AddressRepo;
import com.vehicle.renting.service.repo.UserRepo;
import com.vehicle.renting.service.service.UserService;
import com.vehicle.renting.service.userdto.requestdto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AddressRepo addressRepo;

    //save data with exception handling
    @Override
    public Users saveUserData(UserDto userDto) {
        if(userDto.getFirstName().isEmpty() || userDto.getLastName().isEmpty() || userDto.getUserEmail().isEmpty() || userDto.getAdCity().isEmpty() || userDto.getAddType().isEmpty() || userDto.getUserName().isEmpty()||userDto.getUserMobileNo()==null)
        {
          throw new EmptyInputException(Constant.Error_Code_601,Constant.Error_Message_601);
        }


        Users users = new Users();
        Address address = new Address();
        BeanUtils.copyProperties(userDto,users);
        BeanUtils.copyProperties(userDto,address);
         address =addressRepo.save(address);
        users.setAddress(address);
        return userRepo.save(users);

    }
//.............................................................................................................
    //fetch data with exception handling
    @Override
    public Users fetchById(Long id) {
       Optional <Users> byId = userRepo.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        }
        else {
            throw new EmptyInputException(602,"please enter valid id");
        }
    }

    /*@Override
       public Users fetchId(Long id)
       {
          Users byId=userRepo.FindById(id).get();
          if(byId==null)
          {
          return byId.get();
         }
         else{
         throw new EmptyInputException(602,"please enter valid id");
         }
       }

    */

//..............................................................................................................
    //fetch All data
    @Override
    public List<Users> fetchAll() {
        return userRepo.findAll();
    }
//..............................................................................................................
    //fetch by email and pass (login api)
    @Override
    public String  fetchByEmailPass(String email, String pass) {

        Users users = userRepo.findByuserEmail(email);//for the query we write only -> Users users = userRepo. findByuserEmai(email);


        if (users==null) {
            throw new EmptyInputException(Constant.Error_Code_602, Constant.Error_Message_602);


        }

        else {
            if(pass.isEmpty()){
                throw new EmptyInputException(Constant.Error_Code_604,Constant.Error_Message_604);
            }


            else if (users.getUserPassword().contentEquals(pass)  )
            {

                return "login success";
            }

            else {
                throw new EmptyInputException(Constant.Error_Code_603,Constant.Error_Message_603);
            }
        }


//...................................................................................................................


//    @Override
//    public Users fetchByEmailPass(String email, String pass) {
//        return userRepo.findByEmailPass(email,pass);
//    }


    }
//.............................................................................................................
    //fetch by email
    @Override
    public Users fetchByEmail(String email) {

        Users users1 = userRepo.findByuserEmail(email);
        if (email.isEmpty())
        {
            throw new EmptyInputException(Constant.Error_Code_601, Constant.Error_Message_601);
        }
        else if (users1 == null)
        {
            throw new EmptyInputException(Constant.Error_Code_602, Constant.Error_Message_602);
        }
       else
        {
            return users1;
        }


    }
//.................................................................................................................
    //delete user by id
    @Override
    public String deleteById(Long id) {
        Optional<Users> byId = userRepo.findById(id);
         if (byId.isPresent()) {
            userRepo.deleteById(id);
            return "this id deleted";
        }else {
            throw new EmptyInputException(Constant.Error_Code_605,Constant.Error_Message_605);

        }
    }
//.....................................................................................................................
    //user details update
    @Override
    public UserDto updateId(UserDto userDto, Long id) {

       Users users=userRepo.getById(id);
       Address address=addressRepo.getById(users.getAddress().getAddId());
       users.setUserEmail(userDto.getUserEmail());
       users.setUserMobileNo(userDto.getUserMobileNo());
        users.setLastName(userDto.getLastName());
        users.setUserPassword(userDto.getUserPassword());
       address.setAddType(userDto.getAddType());
       address.setAdCity(userDto.getAdCity());
        Users save = userRepo.save(users);


        return userDto;


        }

    }



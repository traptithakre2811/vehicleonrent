package com.vehicle.renting.service.userdto.requestdto;

import com.vehicle.renting.service.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto
{
    private String firstName;
    private String lastName;
    private String userName;
    private Long userMobileNo;
    private String userEmail;
    private String userPassword;
    private String  adCity;
    private String addType;
}

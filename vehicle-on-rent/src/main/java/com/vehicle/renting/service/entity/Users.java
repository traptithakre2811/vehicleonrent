package com.vehicle.renting.service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;
   private String firstName;
   private String lastName;
    private String userName;
   private Long userMobileNo;
   private String userEmail;
   private String userPassword;
   @OneToOne(cascade = CascadeType.ALL)
   private Address address;

}

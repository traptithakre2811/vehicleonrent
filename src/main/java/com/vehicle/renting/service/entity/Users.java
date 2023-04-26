package com.vehicle.renting.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)// Ignore Null Fields on the Class
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long userId;
    @Column(updatable = false,insertable =true)
   private String firstName;
   private String lastName;
    private String userName;
   private Long userMobileNo;
   @Column(updatable = true,insertable = true)//insertable always true because if we provide false so its not store useremail 1st time when we save data
   private String userEmail;

   private String userPassword;
   @OneToOne(cascade = CascadeType.ALL)
   private Address address;


//   @ManyToOne(cascade = CascadeType.ALL)
//   private Vendors vendors;

}

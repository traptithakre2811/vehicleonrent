package com.vehicle.renting.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)  // Ignore Null Fields on the Class
public class Vendors
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long vendorId;
    @Column(updatable = true,insertable = true)

   private String vendorName;
   @Column(updatable = true,insertable = true)
   private String vendorMobileNumber;
   @Column(updatable = false,insertable = true)
   private String vendorEmail;
   @Column(updatable = true,insertable = true)
   private String vendorPassword;
   @Column(updatable = true,insertable = true)//if we provide insertable false so its not insert data  1st time also
   private  String vendorUserName;

    @OneToOne(cascade = CascadeType.ALL)

    private Address addressVendor;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Vehicle> vehicle;
}

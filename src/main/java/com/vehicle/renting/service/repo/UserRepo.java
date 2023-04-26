package com.vehicle.renting.service.repo;

import com.vehicle.renting.service.entity.Users;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
   // Users findByuserEmail(String email);

//    @Query(value = "SELECT * FROM vehicle.users where user_email=?",nativeQuery = true)
//    Users findByuserEmai(String email);
//

    @Query(value = "SELECT * FROM vehicle.users where user_email=?",nativeQuery = true)
    Users findByuserEmail(String email);

    @Query(value = "SELECT * FROM vehicle.users where user_name=?",nativeQuery = true)
    Users findByuserUserName(String username);

}

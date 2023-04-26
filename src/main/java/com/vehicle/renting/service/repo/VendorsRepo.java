package com.vehicle.renting.service.repo;

import com.vehicle.renting.service.entity.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorsRepo extends JpaRepository<Vendors,Long> {
}

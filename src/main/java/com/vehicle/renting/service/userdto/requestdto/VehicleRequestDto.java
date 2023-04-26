package com.vehicle.renting.service.userdto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleRequestDto {
    public String vehicleName;
    public String vehicleNumber;
}

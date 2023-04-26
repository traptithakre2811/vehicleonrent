package com.vehicle.renting.service.userdto.responcedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VehicleResponaseDto {
    private Integer vehicleId;
    private String vehicleName;

    private  String vehicleNumber;
}

package com.vehicle.renting.service.userdto.responcedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponseDto {
    private Long addId;
    private String  adCity;
    private String addType;
}

package com.vehicle.renting.service.userdto.responcedto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vehicle.renting.service.userdto.requestdto.AddressRequestDto;
import com.vehicle.renting.service.userdto.requestdto.VehicleRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VendorResponseDto {
    private Long vendorId;
    private String vendorName;

    private String vendorMobileNumber;

    private String vendorEmail;

    private String vendorPassword;

    private  String vendorUserName;


    private AddressResponseDto addressVendor;


    private List<VehicleResponaseDto> vehicle;
}

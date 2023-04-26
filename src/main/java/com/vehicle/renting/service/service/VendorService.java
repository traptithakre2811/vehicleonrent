package com.vehicle.renting.service.service;

import com.vehicle.renting.service.userdto.requestdto.VehicleRequestDto;
import com.vehicle.renting.service.userdto.requestdto.VendorRequestDto;
import com.vehicle.renting.service.userdto.responcedto.VehicleResponaseDto;
import com.vehicle.renting.service.userdto.responcedto.VendorResponseDto;

public interface VendorService  {
 //   VendorDto saveVendor(VendorDto vendorDto);

    VendorResponseDto save(VendorRequestDto vendorRequestDto);

    String deleteVendorById(Long id);

    VendorResponseDto updateVendorById(Long id, VendorRequestDto vendorRequestDto);
}

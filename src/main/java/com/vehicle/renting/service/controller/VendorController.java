package com.vehicle.renting.service.controller;

import com.vehicle.renting.service.service.VendorService;
import com.vehicle.renting.service.service.serviceimpl.VendorServiceImpl;
import com.vehicle.renting.service.userdto.requestdto.VendorRequestDto;
import com.vehicle.renting.service.userdto.responcedto.VendorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/vendor/v1")
public class VendorController {
@Autowired
    private VendorService vendorService;
    @PostMapping("/save")
    public VendorResponseDto saveVendor(@RequestBody VendorRequestDto vendorRequestDto)
    {
        return vendorService.save(vendorRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteById(@PathVariable Long id)
    {
        return vendorService.deleteVendorById(id);
    }

    @PutMapping("/update/{id}")
    public VendorResponseDto updateById(@PathVariable Long id,@RequestBody VendorRequestDto vendorRequestDto)
    {
        return vendorService.updateVendorById(id,vendorRequestDto);
    }
}

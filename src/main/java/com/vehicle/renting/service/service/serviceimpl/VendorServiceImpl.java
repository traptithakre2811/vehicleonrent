package com.vehicle.renting.service.service.serviceimpl;

import com.vehicle.renting.service.entity.Address;
import com.vehicle.renting.service.entity.Vehicle;
import com.vehicle.renting.service.entity.Vendors;
import com.vehicle.renting.service.repo.AddressRepo;
import com.vehicle.renting.service.repo.VehicleRepo;
import com.vehicle.renting.service.repo.VendorsRepo;
import com.vehicle.renting.service.service.VendorService;
import com.vehicle.renting.service.userdto.requestdto.VehicleRequestDto;
import com.vehicle.renting.service.userdto.requestdto.VendorRequestDto;
import com.vehicle.renting.service.userdto.responcedto.AddressResponseDto;
import com.vehicle.renting.service.userdto.responcedto.VehicleResponaseDto;
import com.vehicle.renting.service.userdto.responcedto.VendorResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    @Autowired
    private VendorsRepo vendorsRepo;
    @Autowired
    private AddressRepo addressRepo1;
    @Autowired
    private VehicleRepo vehicleRepo1;

//................................................................................................................

//save vendor using DTO
    @Override
    public VendorResponseDto save(VendorRequestDto vendorRequestDto) {



//        Vendors vendors= new Vendors();
//        Address address=new Address();
//        List<Vehicle> list=new ArrayList<>();
//        BeanUtils.copyProperties(vendorRequestDto,vendors);
//        BeanUtils.copyProperties(vendorRequestDto.getAddressVendor(),address);
//        for (VehicleRequestDto vehicle1:vendorRequestDto.getVehicle()) {
//            Vehicle vehicle=new Vehicle();
//            BeanUtils.copyProperties(vehicle1,vehicle);
//            list.add(vehicle);
//        }
//        vendors.setAddressVendor(address);
////        List<Vehicle> vehicles = vehicleRepo1.saveAll(list);
//
//        vendors.setVehicle(list);
//
//        Vendors responseEntity = vendorsRepo.save(vendors);
//
//        VendorResponseDto vendorResponseDto=new VendorResponseDto();
//        AddressResponseDto addressResponseDto=new AddressResponseDto();
//        VehicleResponaseDto vehicleResponaseDto=new VehicleResponaseDto();
//        BeanUtils.copyProperties(responseEntity,vendorResponseDto);
//        BeanUtils.copyProperties(responseEntity.getAddressVendor(),addressResponseDto);
//        List<VehicleResponaseDto> list1=new ArrayList<>();
//        for(Vehicle vehicle1: responseEntity.getVehicle())
//        {
//            BeanUtils.copyProperties(vehicle1,vehicleResponaseDto);
//            list1.add(vehicleResponaseDto);
//        }
//        vendorResponseDto.setAddressVendor(addressResponseDto);
//
//        vendorResponseDto.setVehicle(list1);
//        return vendorResponseDto;


                Vendors vendors = new Vendors();
                BeanUtils.copyProperties(vendorRequestDto, vendors);
                Address address = new Address();
                BeanUtils.copyProperties(vendorRequestDto.getAddressVendor(), address);
                vendors.setAddressVendor(address);
                List<Vehicle> vehicles = vendorRequestDto.getVehicle().stream()
                        .map(vehicleDto -> {
                            Vehicle vehicle = new Vehicle();
                            BeanUtils.copyProperties(vehicleDto, vehicle);
                            return vehicle;
                        })
                        .collect(Collectors.toList());
                vendors.setVehicle(vehicles);
                Vendors savedVendors = vendorsRepo.save(vendors);
                return convertToVendorResponseDto(savedVendors);
            }

            private VendorResponseDto convertToVendorResponseDto(Vendors vendors) {
                VendorResponseDto vendorResponseDto = new VendorResponseDto();
                BeanUtils.copyProperties(vendors, vendorResponseDto);
                AddressResponseDto addressResponseDto = new AddressResponseDto();
                BeanUtils.copyProperties(vendors.getAddressVendor(), addressResponseDto);
                vendorResponseDto.setAddressVendor(addressResponseDto);
                List<VehicleResponaseDto> vehicleResponseDtos = vendors.getVehicle().stream()
                        .map(vehicle -> {
                            VehicleResponaseDto vehicleResponseDto = new VehicleResponaseDto();
                            BeanUtils.copyProperties(vehicle, vehicleResponseDto);
                            return vehicleResponseDto;
                        })
                        .collect(Collectors.toList());
                vendorResponseDto.setVehicle(vehicleResponseDtos);
                return vendorResponseDto;
            }







    //..................................................................................................................
   //Delete vendor ById
    @Override
    public String deleteVendorById(Long id) {
        vendorsRepo.deleteById(id);
        return "this id is deleted";
    }
//.....................................................................................................................
    @Override
    public VendorResponseDto updateVendorById(Long id, VendorRequestDto vendorRequestDto) {

        Vendors vendors = vendorsRepo.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));

        Address address = addressRepo1.getById(vendors.getVendorId());
        address.setAddType(vendorRequestDto.getAddressVendor().getAddType());
        address.setAdCity(vendorRequestDto.getAddressVendor().getAdCity());

        for (VehicleRequestDto vehicleRequestDto : vendorRequestDto.getVehicle()) {
            Vehicle vehicle = vehicleRepo1.getById(vendors.getVendorId());
            vehicle.setVehicleName(vehicleRequestDto.getVehicleName());
            vehicle.setVehicleNumber(vehicleRequestDto.getVehicleNumber());
            vehicleRepo1.save(vehicle);
        }

        vendorsRepo.save(vendors);

        VendorResponseDto vendorResponseDto = new VendorResponseDto();
        BeanUtils.copyProperties(vendorRequestDto, vendorResponseDto);

        return vendorResponseDto;

    }

}

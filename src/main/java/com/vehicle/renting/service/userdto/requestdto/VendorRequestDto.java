package com.vehicle.renting.service.userdto.requestdto;

import com.vehicle.renting.service.entity.Address;
import com.vehicle.renting.service.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendorRequestDto {
    public String vendorName;
    public String vendorMobileNumber;
    public String vendorEmail;
    public String vendorPassword;
    public String vendorUserName;
    public AddressRequestDto addressVendor;
    public ArrayList<VehicleRequestDto> vehicle;
}

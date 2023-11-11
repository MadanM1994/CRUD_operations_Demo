package com.madan.crud_operations_demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private int addressId;
    private int employeeId;
    private String street;
    private String city;
    private String state;
    private String zipCode;


}

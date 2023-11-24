package com.madan.crud_operations_demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private String streetName;
    private String city;
    private String state;
    private String zipCode;
}

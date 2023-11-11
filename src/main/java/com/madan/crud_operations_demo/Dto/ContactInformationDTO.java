package com.madan.crud_operations_demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactInformationDTO {
    private int contactInformationId;
    private int employeeId;
    private String email;
    private String phoneNumber;
}

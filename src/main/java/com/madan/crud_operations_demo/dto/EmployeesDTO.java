package com.madan.crud_operations_demo.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeesDTO {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private List<AddressDTO> addresses; // List of AddressDTO
    private List<ContactInformationDTO> contactInformation; // List of ContactInformationDTO

}

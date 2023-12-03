package com.madan.crud_operations_demo.dto;

import com.madan.crud_operations_demo.validation.annotations.EmailValidation;
import com.madan.crud_operations_demo.validation.annotations.NameValidation;
import com.madan.crud_operations_demo.validation.annotations.PhoneNumberValidation;
import com.madan.crud_operations_demo.validation.annotations.ZipCodeValidation;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDetailsDTO {
    @NameValidation
    private String firstname;
    @NameValidation
    private String lastname;
    @ZipCodeValidation
    private String zipcode;
    @EmailValidation
    private String email;
    @PhoneNumberValidation
    private String phoneNumber;

}

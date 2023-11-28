package com.madan.crud_operations_demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInformationDTO {
    private String email;
    private String phoneNumber;
}

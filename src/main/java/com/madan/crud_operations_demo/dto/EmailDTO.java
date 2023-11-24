package com.madan.crud_operations_demo.dto;

import com.madan.crud_operations_demo.constants.EmailStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {
    private String emailAddress;
    private EmailStatus emailStatus;
}

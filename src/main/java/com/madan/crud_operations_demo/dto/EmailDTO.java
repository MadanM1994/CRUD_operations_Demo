package com.madan.crud_operations_demo.dto;

import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.entity.Employees;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailDTO {

    private String emailAddress;
    private Email.EmailStatus emailStatus;
}

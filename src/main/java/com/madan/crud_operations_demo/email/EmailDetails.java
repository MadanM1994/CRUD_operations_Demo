package com.madan.crud_operations_demo.email;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailDetails {
    private List<String> emailIDs;
    private String subject;
    private String body;
}

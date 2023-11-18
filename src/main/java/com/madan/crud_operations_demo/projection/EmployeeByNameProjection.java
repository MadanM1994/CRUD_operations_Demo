package com.madan.crud_operations_demo.projection;

import java.util.Date;

public interface EmployeeByNameProjection {
    Integer getEmployeeId();
    String getEmail();
    Date getDateOfBirth();
    String getCity();
    String getFirstName();
    String getLastName();
}

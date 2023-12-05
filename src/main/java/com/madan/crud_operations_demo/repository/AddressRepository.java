package com.madan.crud_operations_demo.repository;

import com.madan.crud_operations_demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    @Modifying
    @Query("DELETE FROM Address a WHERE a.employees.employeeId= :employeeId")
    void deleteByEmployeeId(int employeeId);

}

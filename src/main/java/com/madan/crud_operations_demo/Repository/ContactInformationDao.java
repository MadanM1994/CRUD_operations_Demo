package com.madan.crud_operations_demo.Repository;

import com.madan.crud_operations_demo.Entity.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationDao extends JpaRepository<ContactInformation, Integer> {
}

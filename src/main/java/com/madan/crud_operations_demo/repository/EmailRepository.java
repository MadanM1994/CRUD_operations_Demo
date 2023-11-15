package com.madan.crud_operations_demo.repository;

import com.madan.crud_operations_demo.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email , Integer> {

}

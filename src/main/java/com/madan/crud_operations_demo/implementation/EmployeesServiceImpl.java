package com.madan.crud_operations_demo.implementation;

import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Employees;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.repository.AddressRepository;
import com.madan.crud_operations_demo.repository.ContactInformationRepository;
import com.madan.crud_operations_demo.repository.EmployeesRepository;
import com.madan.crud_operations_demo.service.DTOToEntityService;
import com.madan.crud_operations_demo.service.EmailJobService;
import com.madan.crud_operations_demo.service.EmployeesService;
import com.madan.crud_operations_demo.service.EntityToDTOService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceImpl implements EmployeesService {

    private final EmployeesRepository employeesRepository;
    private final ContactInformationRepository contactInformationRepository;
    private final AddressRepository addressRepository;
    private final EmailJobService emailJobService;
    private final EntityToDTOService entityToDTOService;
    private final DTOToEntityService dtoToEntityService;

    public EmployeesServiceImpl(EmployeesRepository employeesRepository,
                                ContactInformationRepository contactInformationRepository,
                                AddressRepository addressRepository,
                                EmailJobService emailJobService,
                                EntityToDTOService entityToDTOService,
                                DTOToEntityService dtoToEntityService) {
        this.employeesRepository = employeesRepository;
        this.contactInformationRepository = contactInformationRepository;
        this.addressRepository = addressRepository;
        this.emailJobService = emailJobService;
        this.entityToDTOService = entityToDTOService;
        this.dtoToEntityService = dtoToEntityService;
    }

    public List<EmployeesDTO> getAllEmployees() {
        return employeesRepository.findAll()
                .stream()
                .map(employee -> entityToDTOService.convertToEmployeesDTO(employee))
                .collect(Collectors.toList());
    }

    public EmployeesDTO getEmployeeById(int id) {
        if (employeesRepository.findById(id).isPresent()) {
            Optional<Employees> employ = employeesRepository.findById(id);
            return employ.map(employee -> entityToDTOService.convertToEmployeesDTO(employee)).orElse(null);
        }
        return null;
    }

    public List<EmployeeByNameProjection> getEmployeeDetailsByMatch(String name) {
        return employeesRepository.findEmployeeDetailsByFirstName(name);
    }

    public String addEmployee(EmployeesDTO employeesDTO) {
        // save employees data
        Employees employees = dtoToEntityService.convertToEmployees(employeesDTO);
        Employees newEmployee = employeesRepository.save(employees);
        // save address data
        List<Address> addresses = employeesDTO.getAddresses()
                .stream()
                .map(addressDTO -> {
                    Address address = dtoToEntityService.convertToAddress(addressDTO);
                    address.setEmployees(newEmployee);
                    address.setIsActive('Y');
                    address.setIsDeleted('N');
                    return address;
                }).collect(Collectors.toList());
        addressRepository.saveAll(addresses);
        // save contact information data
        List<ContactInformation> contactInformations = employeesDTO.getContactInformation()
                .stream()
                .map(contactInformationDTO -> {
                    ContactInformation contactInformation = dtoToEntityService.convertToContactInformation(contactInformationDTO);
                    contactInformation.setEmployees(newEmployee);
                    contactInformation.setIsActive('Y');
                    contactInformation.setIsDeleted('N');
                    return contactInformation;
                }).collect(Collectors.toList());
        // save email data
        for (ContactInformation contactInformation : contactInformations) {
            emailJobService.addEmployeeToEmail(contactInformation.getEmail());
        }
        contactInformationRepository.saveAll(contactInformations);
        return "Successfully Added ";
    }


    public Boolean deleteEmployeeById(int id) {
        Optional<Employees> employ = employeesRepository.findById(id);
        if (employ.isPresent()) {
            employeesRepository.deleteById(id);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public String updateById(int id, EmployeesDTO employeeDTO) {
        Optional<Employees> existingEmployeee = employeesRepository.findById(id);
        if (existingEmployeee.isPresent()) {
            Employees existingEmployee = existingEmployeee.get();
            Employees newEmployeeRecord = dtoToEntityService.convertToEmployees(employeeDTO);
            existingEmployee.setEmployeeId(id);
            existingEmployee.setFirstName(newEmployeeRecord.getFirstName());
            existingEmployee.setLastName(newEmployeeRecord.getLastName());
            existingEmployee.setDateOfBirth(newEmployeeRecord.getDateOfBirth());
            existingEmployee.setGender(newEmployeeRecord.getGender());
            existingEmployee.setIsActive(newEmployeeRecord.getIsActive());
            existingEmployee.setIsDeleted(newEmployeeRecord.getIsDeleted());
            employeesRepository.save(existingEmployee);
            return "Successfully Updated";
        }
        return "Update Failed";
    }
}










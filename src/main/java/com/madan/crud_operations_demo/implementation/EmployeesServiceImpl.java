package com.madan.crud_operations_demo.implementation;

import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Employees;
import com.madan.crud_operations_demo.exception.EmployeeNotFoundException;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.repository.AddressRepository;
import com.madan.crud_operations_demo.repository.ContactInformationRepository;
import com.madan.crud_operations_demo.repository.EmployeesRepository;
import com.madan.crud_operations_demo.service.DTOToEntityService;
import com.madan.crud_operations_demo.service.EmailJobService;
import com.madan.crud_operations_demo.service.EmployeesService;
import com.madan.crud_operations_demo.service.EntityToDTOService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
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
                                @Qualifier("emailJobServiceImpl") EmailJobService emailJobService,
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
        return employeesRepository.findById(id)
                .map(employee -> entityToDTOService.convertToEmployeesDTO(employee))
                .orElseThrow(() -> new EmployeeNotFoundException("The Employee record associated with the ID is not found. Please enter a valid employee ID"));
    }


    public List<EmployeeByNameProjection> getEmployeeDetailsByMatch(String name) {
        List<EmployeeByNameProjection> employeeDetails = employeesRepository.findEmployeeDetailsByFirstName(name);
        if (employeeDetails.isEmpty()) {
            throw new EmployeeNotFoundException("The Employee record associated with the given first name is not found Please enter valid employee Name");
        }
        return employeeDetails;
    }

    public String addEmployee(EmployeesDTO employeesDTO) {
        // save employees data
        Employees employees = dtoToEntityService.convertToEmployees(employeesDTO);
        Employees newEmployee = employeesRepository.save(employees);
        // save address data
        if (employeesDTO.getAddresses() != null && !employeesDTO.getAddresses().isEmpty()) {
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
        }
        // save contact information data
        if (employeesDTO.getContactInformation() != null && !employeesDTO.getContactInformation().isEmpty()) {
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
        }
        return "Successfully Added";
    }


    @Transactional
    public String deleteEmployeeById(int id) {
        Employees employ = employeesRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("The Employee record associated with the ID is not found. Please enter a valid employee ID"));
        ;
        try {
            contactInformationRepository.deleteByEmployeeId(id);
            addressRepository.deleteByEmployeeId(id);
            employeesRepository.deleteById(id);
            return ("The employee record has be successfully deleted");
        } catch (Exception e) {
            return ("There is some error while deleting the employee record with this id");
        }
    }
}










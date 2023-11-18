package com.madan.crud_operations_demo.service;

import com.madan.crud_operations_demo.dto.AddressDTO;
import com.madan.crud_operations_demo.dto.ContactInformationDTO;
import com.madan.crud_operations_demo.dto.EmailDTO;
import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Email;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.repository.AddressRepository;
import com.madan.crud_operations_demo.repository.ContactInformationRepository;
import com.madan.crud_operations_demo.repository.EmailRepository;
import com.madan.crud_operations_demo.repository.EmployeesRepository;
import com.madan.crud_operations_demo.entity.Employees;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeesService {

    private final EmployeesRepository employeesRepository;
    private final ContactInformationRepository contactInformationRepository;
    private final AddressRepository addressRepository;
    private final EmailRepository emailRepository;
    private final EmailService emailService;

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository,
                            ContactInformationRepository contactInformationRepository,
                            AddressRepository addressRepository,
                            EmailRepository emailRepository,
                            EmailService emailService) {
        this.employeesRepository = employeesRepository;
        this.contactInformationRepository = contactInformationRepository;
        this.addressRepository = addressRepository;
        this.emailRepository = emailRepository;
        this.emailService = emailService;
    }

    // Convert Employee Entity to DTO
    private EmployeesDTO convertToEmployeesDTO(Employees employee) {
        EmployeesDTO newEmployeesDTO = new EmployeesDTO();
        BeanUtils.copyProperties(employee, newEmployeesDTO);
        return newEmployeesDTO;
    }

    // Convert ContactInformation Entity to DTO
    private ContactInformationDTO convertTocontactInformationDTO(ContactInformation contactInformation) {
        ContactInformationDTO newContactInformationDTO = new ContactInformationDTO();
        BeanUtils.copyProperties(contactInformation, newContactInformationDTO);
        return newContactInformationDTO;
    }

    // Convert Address Entity to DTO
    private AddressDTO convertToaddressDTO(Address address) {
        AddressDTO newAddressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, newAddressDTO);
        return newAddressDTO;
    }

    // Convert Employees DTO to Entity
    private Employees convertToEmployees(EmployeesDTO employeesDTO) {
        Employees newEmployees = new Employees();
        BeanUtils.copyProperties(employeesDTO, newEmployees);
        newEmployees.setIsActive('Y');
        newEmployees.setIsDeleted('N');
        return newEmployees;
    }

    // Convert AddressDTO to Address entity
    private Address convertToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        // Additional properties setup if needed
        return address;
    }

    // Convert ContactInformationDTO to ContactInformation entity
    private ContactInformation convertToContactInformation(ContactInformationDTO contactInfoDTO) {
        ContactInformation contactInformation = new ContactInformation();
        BeanUtils.copyProperties(contactInfoDTO, contactInformation);
        // Additional properties setup if needed
        return contactInformation;
    }

    //  convert Email to EmailDTO
    private EmailDTO convertToEmailDTO (Email email) {
        EmailDTO newEmailDTO = new EmailDTO();
        BeanUtils.copyProperties(email, newEmailDTO);
        return newEmailDTO;
    }

    // Convert Email DTO to Email
    private Email convertToEmail(EmailDTO emailDTO){
        Email newEmails = new Email();
        BeanUtils.copyProperties(emailDTO, newEmails);
        return newEmails;
    }

    // GET ALL EMPLOYEES
    public List<EmployeesDTO> getAllEmployees() {
        return employeesRepository.findAll()
                .stream()
                .map(employee -> convertToEmployeesDTO(employee))
                .collect(Collectors.toList());
    }

    // GET EMPLOYEE BY ID
    public EmployeesDTO getEmployeeById(int id) {
        if (employeesRepository.findById(id).isPresent()) {
            Optional<Employees> employ = employeesRepository.findById(id);
            return employ.map(employee -> convertToEmployeesDTO(employee))
                    .orElse(null);
        }
        return null;
    }

    // ADD EMPLOYEE
    public String addEmployee(EmployeesDTO employeesDTO) {
        //1. convert employee DTO to employees
        Employees employees= convertToEmployees(employeesDTO);
        //2.save employees into the DB and assign it to variable
        Employees newEmployee = employeesRepository.save(employees);
        //3. extract and save address
        //3.1 extracting a list of address from employeeDTO
        List<Address> addresses = employeesDTO.getAddresses().stream()
                .map(addressDTO -> {
                          Address address =  convertToAddress(addressDTO);
        address.setEmployees(newEmployee);
        address.setIsActive('Y');
        address.setIsDeleted('N');
        return address;
                }).collect(Collectors.toList());
        addressRepository.saveAll(addresses);
        //4.extract and save contact information
        //4.1 extract list of contact information from employees DTO
        List<ContactInformation> contactInformations = employeesDTO.getContactInformation()
                .stream()
                .map(contactInformationDTO -> {
            ContactInformation contactInformation = convertToContactInformation(contactInformationDTO);
            contactInformation.setEmployees(newEmployee);
                    contactInformation.setIsActive('Y');
                    contactInformation.setIsDeleted('N');
            return  contactInformation;
        }).collect(Collectors.toList());
        for (ContactInformation contactInformation : contactInformations) {
            emailService.addEmployeeToEmail(contactInformation.getEmail());
        }
        contactInformationRepository.saveAll(contactInformations);

return " ";
    }
//        if (employeesDTO != null) {
//            Employees employees = convertToEmployees(employeesDTO);
//            employeesRepository.saveAndFlush(employees);
//            System.out.println(employees.getEmployeeId());
//            emailService.addEmployeeToEmail(employees.getEmail());
//            return "Successfully added employee record";
//        }
//        return "Please Enter a Valid Data in JSON Format";
//    }

    // CREATE EMPLOYEE RECORD
//    public ResponseEntity<?> addEmployee(EmployeeDetailsDTO employeeDetailsDTO) {
//      if(employeeDetailsDTO.getEmployees() !=null){
//          EmployeesDTO employeeRecord = employeeDetailsDTO.getEmployees();
//          Employees newEmployeeRecord = convetToEmpoyees(employeeRecord);
//          employeesRepository.save(newEmployeeRecord);
//          return ResponseEntity.ok("Successfully added record into employees table");
//      }
//      if(employeeDetailsDTO.getAddress() != null){
//          Address addressRecord = employeeDetailsDTO.getAddress();
//          addressRepository.save(addressRecord);
//          return ResponseEntity.ok("Successfully added record into address table");
//      }
//      if(employeeDetailsDTO.getContactInformation() !=null){
//          ContactInformation contactInformationRecord = employeeDetailsDTO.getContactInformation();
//          return ResponseEntity.ok("Successfully added record into contact information table");
//      }
//    }

//    @Transactional
//    // ADD EMPLOYEE
//    public ResponseEntity<?> addEmployee(EmployeesDTO employeesDTO) {
//        if(employeesDTO != null){
//            Employees employee = convertToEmployees(employeesDTO);
//            List<Email> emails = employeesDTO.getEmails().stream()
//                    .map(emailDTO -> convertToEmail(emailDTO))
//                    .collect(Collectors.toList());
//            emails.forEach(email -> email.setEmployees(employee));
//            employee.setEmails(emails);
//            employeesRepository.save(employee);
//            return ResponseEntity.ok("Saved Successfully");
//        }
//return ResponseEntity.badRequest().body("Failed");
//    }

        // DELETE EMPLOYEE BY ID
    public ResponseEntity<?> deleteEmployeeById(int id) {
        Optional<Employees> employ = employeesRepository.findById(id);
        if (employ.isPresent()) {
            employeesRepository.deleteById(id);
            return ResponseEntity.ok().body("sucesfully Deleted");
        }
        return ResponseEntity.badRequest().body("Please enter a valid ID");
    }

    public ResponseEntity<?> updateById(@PathVariable int id, @RequestBody EmployeesDTO employeeDTO) {
        Optional<Employees> existingEmployeee = employeesRepository.findById(id);
        if (existingEmployeee.isPresent()) {
            Employees existingEmployee = existingEmployeee.get();
            Employees newEmployeeRecord = convertToEmployees(employeeDTO);

            existingEmployee.setEmployeeId(id);
            existingEmployee.setFirstName(newEmployeeRecord.getFirstName());
            existingEmployee.setLastName(newEmployeeRecord.getLastName());
            existingEmployee.setDateOfBirth(newEmployeeRecord.getDateOfBirth());
            existingEmployee.setGender(newEmployeeRecord.getGender());
            existingEmployee.setIsActive(newEmployeeRecord.getIsActive());
            existingEmployee.setIsDeleted(newEmployeeRecord.getIsDeleted());
            employeesRepository.save(existingEmployee);
            return ResponseEntity.ok("Successfully Updated");
        }
        return ResponseEntity.ok("Update Failed");
    }

    public List<EmployeeByNameProjection> getEmployeeDetailsByMatch(String name) {
       List<EmployeeByNameProjection> employeesByName = employeesRepository.findEmployeeDetailsByFirstName(name);
        return employeesByName;
    }
}








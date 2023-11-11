package com.madan.crud_operations_demo.Service;

import com.madan.crud_operations_demo.Dto.AddressDTO;
import com.madan.crud_operations_demo.Dto.ContactInformationDTO;
import com.madan.crud_operations_demo.Dto.EmployeesDTO;
import com.madan.crud_operations_demo.Entity.Address;
import com.madan.crud_operations_demo.Entity.ContactInformation;
import com.madan.crud_operations_demo.Repository.AddressRepository;
import com.madan.crud_operations_demo.Repository.ContactInformationRepository;
import com.madan.crud_operations_demo.Repository.EmployeesRepository;
import com.madan.crud_operations_demo.Entity.Employees;
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

    @Autowired
    public EmployeesService(EmployeesRepository employeesRepository,
                            ContactInformationRepository contactInformationRepository,
                            AddressRepository addressRepository) {
        this.employeesRepository = employeesRepository;
        this.contactInformationRepository = contactInformationRepository;
        this.addressRepository = addressRepository;
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
        newEmployees.setActive('Y');
        newEmployees.setDeleted('N');
        return newEmployees;
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
    public ResponseEntity<?> addEmployee(EmployeesDTO employeesDTO) {
        if (employeesDTO != null) {
            Employees employes = convertToEmployees(employeesDTO);
            return ResponseEntity.ok(employeesRepository.save(employes));
        }
        return ResponseEntity.badRequest().body("Please Enter a Valid Data in JSON Format");
    }

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
            existingEmployee.setActive(newEmployeeRecord.getActive());
            existingEmployee.setDeleted(newEmployeeRecord.getDeleted());
            employeesRepository.save(existingEmployee);
            return ResponseEntity.ok("Successfully Updated");
        }
        return ResponseEntity.ok("Update Failed");
    }
}








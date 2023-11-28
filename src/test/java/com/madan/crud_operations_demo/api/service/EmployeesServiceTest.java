package com.madan.crud_operations_demo.api.service;

import com.madan.crud_operations_demo.dto.AddressDTO;
import com.madan.crud_operations_demo.dto.ContactInformationDTO;
import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.entity.Address;
import com.madan.crud_operations_demo.entity.ContactInformation;
import com.madan.crud_operations_demo.entity.Employees;
import com.madan.crud_operations_demo.implementation.EmployeesServiceImpl;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.repository.AddressRepository;
import com.madan.crud_operations_demo.repository.ContactInformationRepository;
import com.madan.crud_operations_demo.repository.EmployeesRepository;
import com.madan.crud_operations_demo.service.DTOToEntityService;
import com.madan.crud_operations_demo.service.EmailJobService;
import com.madan.crud_operations_demo.service.EntityToDTOService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EmployeesServiceTest {

    @Mock
    private EmployeesRepository employeesRepository;
    @Mock
    private ContactInformationRepository contactInformationRepository;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private EntityToDTOService entityToDTOService;
    @Mock
    private DTOToEntityService dtoToEntityService;
    @Mock
    private EmailJobService emailJobService;
    @InjectMocks
    private EmployeesServiceImpl employeesServiceImpl;

    @Test
    void employeeService_GetAllEmployees_ReturnListOfEmployees() {
        // Arrange
        Employees employees1 = Mockito.mock(Employees.class);
        Employees employees2 = Mockito.mock(Employees.class);
        List<Employees> mockEmployeesList = Arrays.asList(employees1, employees2);
        when(employeesRepository.findAll()).thenReturn(mockEmployeesList);
        when(entityToDTOService.convertToEmployeesDTO(any(Employees.class))).thenReturn(new EmployeesDTO());
        // Act
        List<EmployeesDTO> result = employeesServiceImpl.getAllEmployees();
        // Assert
        verify(employeesRepository).findAll();
        verify(entityToDTOService, times(2)).convertToEmployeesDTO(any(Employees.class));
        assertEquals(mockEmployeesList.size(), result.size());
        assertFalse(result.isEmpty());
    }

    @Test
    public void employeeService_GetEmployeeById_ReturnEmployeesById() {
        // Arrange
        Employees employees = Mockito.mock(Employees.class);
        EmployeesDTO employeesDTO = Mockito.mock(EmployeesDTO.class);
        when(employeesRepository.findById(anyInt())).thenReturn(Optional.of(employees));
        when(entityToDTOService.convertToEmployeesDTO(any(Employees.class))).thenReturn(employeesDTO);
        // Act
        EmployeesDTO result = employeesServiceImpl.getEmployeeById(employees.getEmployeeId());
        // Assert
        verify(employeesRepository).findById(employees.getEmployeeId());
        assertNotNull(result);
    }

    @Test
    public void employeeService_GetEmployeeDetailsByMatch_ReturnListOfEmployees() {
        //Arrange
        EmployeeByNameProjection employee1 = Mockito.mock(EmployeeByNameProjection.class);
        EmployeeByNameProjection employee2 = Mockito.mock(EmployeeByNameProjection.class);
        String name = "Madan";
        when(employee1.getFirstName()).thenReturn("Madan");
        when(employee1.getCity()).thenReturn("ijamsville");
        List<EmployeeByNameProjection> employeesList = Arrays.asList(employee1, employee2);
        when(employeesRepository.findEmployeeDetailsByFirstName(name)).thenReturn(employeesList);
        //Act
        List<EmployeeByNameProjection> result = employeesServiceImpl.getEmployeeDetailsByMatch(name);
        //Assert
        verify(employeesRepository).findEmployeeDetailsByFirstName(name);
        assertNotNull(result);
        assertEquals("Madan", result.get(0).getFirstName());
        assertEquals("ijamsville", result.get(0).getCity());
    }

    @Test
    public void employeesService_AddEmployee_ReturnEmployee() {
        //Arrange
        EmployeesDTO employeeDTO = Mockito.mock(EmployeesDTO.class);
        AddressDTO addressDTO = Mockito.mock(AddressDTO.class);
        ContactInformationDTO contactInformationDTO = Mockito.mock(ContactInformationDTO.class);
        when(dtoToEntityService.convertToEmployees(employeeDTO)).thenReturn(new Employees());
        when(dtoToEntityService.convertToAddress(addressDTO)).thenReturn(new Address());
        when(dtoToEntityService.convertToContactInformation(contactInformationDTO)).thenReturn(new ContactInformation());
        when(employeeDTO.getAddresses()).thenReturn(Collections.singletonList(addressDTO));
        when(employeeDTO.getContactInformation()).thenReturn(Collections.singletonList(contactInformationDTO));
        when(employeesRepository.save(any(Employees.class))).thenReturn(new Employees());
        //Act
        String result = employeesServiceImpl.addEmployee(employeeDTO);
        //Assert
        verify(dtoToEntityService).convertToEmployees(any(EmployeesDTO.class));
        verify(dtoToEntityService).convertToAddress(any(AddressDTO.class));
        verify(dtoToEntityService).convertToContactInformation(any(ContactInformationDTO.class));
        verify(employeesRepository).save(any(Employees.class));
        verify(addressRepository).saveAll(anyList());
        verify(contactInformationRepository).saveAll(anyList());
        assertEquals("Successfully Added", result);
        employeeDTO.getContactInformation().forEach(contactInformation -> verify(emailJobService).addEmployeeToEmail(contactInformation.getEmail()));
    }

    @Test
    public void employeeService_DeleteEmployeeById_ReturnEmployee() {
        //Arrange
        int id = 1;
        Employees employees = Mockito.mock(Employees.class);
        when(employeesRepository.findById(id)).thenReturn(Optional.of(employees));
        //Act
        Boolean result = employeesServiceImpl.deleteEmployeeById(id);
        // Assert
        verify(employeesRepository).findById(id);
        verify(employeesRepository).deleteById(id);
        assertTrue(result);
    }

    @Test
    public void employeeService_DeleteEmployeeById_ReturnFalse() {
        int id = 999;
        when(employeesRepository.findById(anyInt())).thenReturn(Optional.empty());

        Boolean result = employeesServiceImpl.deleteEmployeeById(id);

        verify(employeesRepository).findById(id);
        assertFalse(result);
    }


}

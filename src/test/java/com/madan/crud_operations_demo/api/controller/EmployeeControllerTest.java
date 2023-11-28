package com.madan.crud_operations_demo.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madan.crud_operations_demo.dto.EmployeesDTO;
import com.madan.crud_operations_demo.projection.EmployeeByNameProjection;
import com.madan.crud_operations_demo.service.EmployeesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {


    @MockBean
    private EmployeesService employeesService;

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(Object object) {
        try {
            return
                    new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getAllEmployees_ShouldReturnAllEmployees() throws Exception {
        EmployeesDTO employeesDTO1 = Mockito.mock(EmployeesDTO.class);
        EmployeesDTO employeesDTO2 = Mockito.mock(EmployeesDTO.class);
        List<EmployeesDTO> employeesDTOList = Arrays.asList(employeesDTO1, employeesDTO2);
        when(employeesService.getAllEmployees()).thenReturn(employeesDTOList);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getEmployeesById_ShouldReturnEmployee() throws Exception {
        int id = 1;
        EmployeesDTO employeesDTO = Mockito.mock(EmployeesDTO.class);
        when(employeesService.getEmployeeById(anyInt())).thenReturn(employeesDTO);

        mockMvc.perform(get("/employees/employee/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void getEmployeeDetailsByMatch_ShouldReturnMatchingEmployees() throws Exception {
        EmployeeByNameProjection employeeByNameProjection = new EmployeeByNameProjection() {
            @Override
            public Integer getEmployeeId() {
                return 1;
            }

            @Override
            public String getEmail() {
                return "maddala.madan@gmail.com";
            }

            @Override
            public Date getDateOfBirth() {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                    return formatter.parse("02-10-1994");
                } catch (ParseException e) {
                    return null;
                }
            }

            @Override
            public String getCity() {
                return "ijamsville";
            }

            @Override
            public String getFirstName() {
                return "Madan";
            }

            @Override
            public String getLastName() {
                return "Maddaala ";
            }
        };
        String name = "Madan";
        List<EmployeeByNameProjection> listEmployeeDetails = List.of(employeeByNameProjection);
        when(employeesService.getEmployeeDetailsByMatch(anyString())).thenReturn(listEmployeeDetails);

        mockMvc.perform(get("/employees/{name}", name))
                .andExpect(status().isOk());
    }

    @Test
    public void addEmployee_ShouldCreateEmployee() throws Exception {
        EmployeesDTO employeesDTO = Mockito.mock(EmployeesDTO.class);
        when(employeesService.addEmployee(any(EmployeesDTO.class))).thenReturn("Successfully Added");

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(employeesDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully Added"));
    }


    @Test
    public void deleteEmployeeById_ShouldDeleteEmployee() throws Exception {
        int id = 1;
        when(employeesService.deleteEmployeeById(anyInt())).thenReturn(Boolean.TRUE);

        mockMvc.perform(delete("/employees/delete/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}

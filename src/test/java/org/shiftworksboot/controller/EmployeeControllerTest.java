package org.shiftworksboot.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.dto.EmployeeFormDto;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class EmployeeControllerTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 유저 생성
    public Employee createEmp(String empId, String password) {
        EmployeeFormDto employeeFormDto = new EmployeeFormDto();

        employeeFormDto.setEmpId(empId);
        employeeFormDto.setName("유저1");
        employeeFormDto.setPassword(password);
        Employee employee = Employee.createEmployee(employeeFormDto, passwordEncoder);
        return employeeService.saveEmployee(employee);
    }

    @Test
    @DisplayName("로그인 테스트")
    public void loginTest() throws Exception {
        String empId = "user1";
        String password = "1234";

        mockMvc.perform(formLogin().userParameter("empId")
                .loginProcessingUrl("/users/login")
                .user(empId).password(password))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }
    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception {
        String empId = "user1";
        String password = "12345";

        mockMvc.perform(formLogin().userParameter("empId")
                .loginProcessingUrl("/users/login")
                .user(empId).password(password))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
    }
}
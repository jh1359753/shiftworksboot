package org.shiftworksboot.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.ShiftworksbootApplication;
import org.shiftworksboot.constant.Role;
import org.shiftworksboot.dto.EmployeeFormDto;
import org.shiftworksboot.entity.Department;
import org.shiftworksboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PasswordEncoder passwordEncoder;


    public Employee createEmployee(){
        EmployeeFormDto employeeFormDto = new EmployeeFormDto();

        employeeFormDto.setEmpId("user1");
        employeeFormDto.setName("김사원");
        employeeFormDto.setBirthday("1999.09.09");
        employeeFormDto.setAddress("가산");
        employeeFormDto.setPassword("1234");
        employeeFormDto.setEntry_date("2020.09.09");

        return Employee.createEmployee(employeeFormDto, passwordEncoder);

    }

    @Test
    @DisplayName("계정 생성 테스트")
    public void saveEmployeeTest(){
        Employee employee = createEmployee();
        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertEquals(employee.getEmpId(), savedEmployee.getEmpId());
        //assertEquals(employee.getDepartment().getDept_name(), savedEmployee.getDepartment().getDept_name());
        assertEquals(employee.getName(), savedEmployee.getName());
        assertEquals(employee.getBirthday(), savedEmployee.getBirthday());
        assertEquals(employee.getAddress(), savedEmployee.getAddress());
        assertEquals(employee.getPassword(), savedEmployee.getPassword());
        assertEquals(employee.getEntry_date(), savedEmployee.getEntry_date());

        assertEquals(employee.getAuthority(), savedEmployee.getAuthority());

    }

    @Test
    @DisplayName("중복회원가입테스트")
    public void saveDupliTest(){
        Employee employee1 = createEmployee();
        Employee employee2 = createEmployee();
        employeeService.saveEmployee(employee1);

        Throwable e = assertThrows(IllegalStateException.class,
                ()->{employeeService.saveEmployee(employee2);});

        assertEquals("이미 존재하는 사번입니다.", e.getMessage());
    }

}
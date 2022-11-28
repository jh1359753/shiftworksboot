package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("계정 저장 테스트")
    public void createEmployeeTest(){
        Employee employee = new Employee();
        employee.setEmpId("test1");
        employee.setPassword("1234");
        String password = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(password);

        Employee savedEmp = employeeRepository.save(employee);
        System.out.println(savedEmp.toString());

    }

}
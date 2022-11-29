package org.shiftworksboot.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.shiftworksboot.entity.Department;
import org.shiftworksboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "classpath:application-test.yml")
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

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

    @Test
    @DisplayName("계정 검색 테스트")
    public void searchEmp() {
        Employee employee = new Employee();
        employee.setEmpId("user1");
        employee.setPassword("1234");
        String password = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(password);
        employeeRepository.save(employee);

        System.out.println(employeeRepository.findByEmpId("test1"));
    }

    @Test
    @DisplayName("부서원 검색 테스트")
    public void searchDept() {

        Department dept = new Department();
        dept.setDeptId("DEPT1");
        departmentRepository.save(dept);

        for(int i=5; i<10; i++) {

            Employee employee = new Employee();
            employee.setEmpId("test" + i);
            employee.setPassword("1234");
            employee.setDepartment(dept);
            String password = passwordEncoder.encode(employee.getPassword());
            employee.setPassword(password);
            employeeRepository.save(employee);
        }

        employeeRepository.findByDepartmentDeptId(dept.getDeptId());

    }

}
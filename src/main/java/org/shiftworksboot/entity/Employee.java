package org.shiftworksboot.entity;

import lombok.*;
import org.shiftworksboot.constant.Role;
import org.shiftworksboot.dto.EmployeeFormDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter @Setter
@ToString
public class Employee {

    @Id
    @Column(unique = true, name = "emp_id")
    private String empId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;

    private String password;
    private String name;
    private String address;
    private String birthday;
    private String position;
    private String mobile;
    private String internal;
    private String email;
    private String entry_date;
    private String resignation_date;
    private String digital_sign;
    private String profile_photo;
    private int failures_num;
    private String memo;
    private String emp_id2;
    private String dept_id2;

    @Enumerated(EnumType.STRING)
    private Role authority;

//    public void addDepartment(Department department){
//        department.getEmployees().add(this);
//        this.department = department;
//    }

    public static Employee createEmployee(EmployeeFormDto employeeFormDto,
                                          PasswordEncoder passwordEncoder){
        Employee employee = new Employee();

        employee.setEmpId(employeeFormDto.getEmpId());
        String password = passwordEncoder.encode(employeeFormDto.getPassword());
        employee.setName(employeeFormDto.getName());
        employee.setAddress(employeeFormDto.getAddress());
        employee.setBirthday(employeeFormDto.getBirthday());
        employee.setPosition(employeeFormDto.getPosition());
        employee.setMobile(employeeFormDto.getMobile());
        employee.setInternal(employeeFormDto.getInternal());
        employee.setEmail(employeeFormDto.getEmail());
        employee.setEntry_date(employeeFormDto.getEntry_date());
        employee.setResignation_date(employeeFormDto.getResignation_date());

        employee.setPassword(password);
        employee.setAuthority(Role.USER);

        return employee;

    }

}

package org.shiftworksboot.service;

import lombok.RequiredArgsConstructor;
import org.shiftworksboot.entity.Department;
import org.shiftworksboot.entity.Employee;
import org.shiftworksboot.repository.EmployeeRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        validateDuplicateEmployee(employee);
        return employeeRepository.save(employee);

    }

    private void validateDuplicateEmployee(Employee employee){
        Employee findEmployee = employeeRepository.findByEmpId(employee.getEmpId());
        if(findEmployee != null){
            throw new IllegalStateException("이미 존재하는 사번입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String empId) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmpId(empId);

        if(employee == null){
            throw new UsernameNotFoundException(empId);
        }

        return User.builder()
                .username(employee.getEmpId())
                .password(employee.getPassword())
                .roles(employee.getAuthority().toString())
                .build();
    }
}

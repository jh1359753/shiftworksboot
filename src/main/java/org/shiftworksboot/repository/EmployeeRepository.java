package org.shiftworksboot.repository;

import org.shiftworksboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmpId(String empId);
}

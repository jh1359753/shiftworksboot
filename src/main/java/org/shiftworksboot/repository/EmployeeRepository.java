package org.shiftworksboot.repository;

import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.entity.Department;
import org.shiftworksboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmpId(String empId);

    List<Employee> findByDepartment(Department dept);
}

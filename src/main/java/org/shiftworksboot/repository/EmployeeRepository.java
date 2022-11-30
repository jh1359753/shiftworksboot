package org.shiftworksboot.repository;

import org.shiftworksboot.constant.TaskDept;
import org.shiftworksboot.entity.Department;
import org.shiftworksboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findByEmpId(String empId);

    List<Employee> findByDepartmentDeptId(String deptId);
}

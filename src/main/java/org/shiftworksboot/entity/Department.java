package org.shiftworksboot.entity;

import lombok.*;
import org.shiftworksboot.constant.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter @Setter
@ToString
public class Department {

    @Id
    @Column(name = "dept_id")
    private String deptId;

    private String dept_name;

    @Enumerated(EnumType.STRING)
    private Role authority;

//    @OneToMany(mappedBy = "department")
//    private List<Employee> employees = new ArrayList<>();

//    @Builder
//    public Department(String dept_name, Employee employee){
//        this.dept_name=dept_name;
//        employee.addDepartment(this);
//    }



}

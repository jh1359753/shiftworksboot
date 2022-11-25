package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employee")
@Getter @Setter
@ToString
public class Employee {

    @Id
    private String emp_id;

    @ManyToOne
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
    private Date entry_date;
    private Date resignation_date;
    private String digital_sign;
    private String profile_photo;
    private int failures_num;
    private String memo;
    private String emp_id2;
    private String dept_id2;


}

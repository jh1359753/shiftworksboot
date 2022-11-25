package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
@Getter @Setter
@ToString
public class Department {

    @Id
    private String dept_id;

    private String dept_name;
    private String authority;
}

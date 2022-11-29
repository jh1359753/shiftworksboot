package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "alarm")
@Getter @Setter @ToString
public class Alarm extends BaseTimeEntity{

    @Id
    @Column(name = "alarm_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer alarm_id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

}

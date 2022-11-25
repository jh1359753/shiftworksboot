package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "task")
@Getter @Setter @ToString
public class Task {

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer task_id;

    private String task_title;

    private String task_content;

    @Column(name = "t_private", length = 1)
    private Character t_private;

    @Column(name = "notification", length = 1)
    private Character notification;

    private String date_created;

    private String dept_id;


}

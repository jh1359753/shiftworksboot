package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.shiftworksboot.constant.TaskDept;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Getter @Setter @ToString
public class Task extends BaseEntity{

    @Id
    @Column(name = "task_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer task_id;

    private String task_title;

    @Lob
    private String task_content;

    @Column(name = "t_private")
    private Character t_private;

    @Column(name = "notification")
    private Character notification;

    @Enumerated(EnumType.STRING)
    private TaskDept dept_id;

}

package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="board")
@Getter @Setter
@ToString
public class Board {

    @Id
    @Column(name = "b_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer b_id;

    @Column(name = "b_name")
    private String name;

    @Column(name = "b_content")
    private String content;

    @Column(name = "b_private")
    private String pri;
}

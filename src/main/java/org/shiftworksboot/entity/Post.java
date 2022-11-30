package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="post")
@Getter @Setter
@ToString
public class Post {

    @Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer post_id;

    @Column(name = "post_name")
    private String pname;

    @Column(name = "post_content")
    private String content;

    @Column(name = "post_regno")
    private int regno;

    @Column(name = "post_regdate")
    private String regdate;

    @Column(name = "post_updatedate")
    private String updatedate;

//    @Column(name = "post_fix")
//    private char fix;

    @Column(name = "post_receivedept")
    private String receivedept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Department department;*/



}

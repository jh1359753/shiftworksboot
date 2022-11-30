package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="scrap")
@Getter
@Setter
@ToString
public class Scrap {

    @Id
    @Column(name = "scrap_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer scrap_id;

    private String scrap_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id")
    private Employee employee;

}

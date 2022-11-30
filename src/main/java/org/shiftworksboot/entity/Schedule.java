package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
@Getter @Setter @ToString
public class Schedule extends BaseEntity{

    @Id
    @Column(name = "sch_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sch_id;

    // Booking 관련 기능 merge 후 추가
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "book_id")
    //private Booking booking;

    private String sch_title;
    private String sch_content;
    private String start_date;
    private String end_date;
    private String sch_group;

    @Transient
    private String sch_date;

}

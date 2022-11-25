package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "rsc_booking")
@Getter @Setter
@ToString
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int book_id;

    private String book_date;
    private String book_begin;
    private int book_end;
    private String book_title;
    private String book_content;

    @ManyToOne
    @JoinColumn(name = "rsc_id")
    private MeetingRoom meetingRoom;

}

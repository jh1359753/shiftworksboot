package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "meeting_room")
@Getter @Setter
@ToString
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rsc_id")
    private int rsc_id;

    private String rsc_name;
    private String rsc_loc;
    private int rsc_amount;
    //private String rsc_hrs;
}

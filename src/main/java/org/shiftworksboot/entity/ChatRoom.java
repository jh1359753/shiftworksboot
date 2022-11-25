package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "chatroom")
@Getter @Setter
@ToString
public class ChatRoom {

    @Id
    private String room_id;

    private String room_name;

    private String lastchat;

    private String lastchat_time;
}

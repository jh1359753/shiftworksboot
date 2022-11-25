package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "chat")
@Getter @Setter
@ToString
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int chat_id;

    private String content;

    private String sendTime;

    private String sender;

}

package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "chatroom")
@Getter
@Setter
@ToString
public class ChatRoom {

    @Id
    @Column(name = "room_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomSeq;

    @Column(name = "room_id")
    private String roomId;

    @Column(name = "room_name")
    private String roomName;

    private String lastchat;

    private String lastchatTime;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatRoom", cascade = CascadeType.ALL)
//    private List<Chat> chat;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "emp_id")
    private Employee employee;


    public static ChatRoom createChatRoom(Employee employee) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.setRoomName(employee.getEmpId());
        chatRoom.setEmployee(employee);
        return chatRoom;
    }

    public void updataChatRoom(String lastchat, String lastchatTime) {

        this.lastchat = lastchat;
        this.lastchatTime = lastchatTime;

    }
}

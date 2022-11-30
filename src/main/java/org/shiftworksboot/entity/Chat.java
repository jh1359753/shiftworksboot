package org.shiftworksboot.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "chat")
@Getter @Setter
@ToString
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private int chatId;

    private String content;

    private String sendtime;

    private String sender;

    private String roomId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "room_id")
//    private ChatRoom chatRoom;

}

/*
chat - chatroom 관계 문제점
문제 1 : ChatRoom의 PK는 유일, 식별가능
    1_chatRoom의 uuid 값을 pk로 할 수 없음. -> 계정당 여러개의 채팅방을 가질 수 있음
        -> 복합키로 변경 : uuid + emp_id : 같은 채팅방에서 채팅 기록이 달라질 수 있음 / 채팅 내역의 중복 저장 문제
    2_chatRoom의 새로운 pk seq를 설정 -> 채팅 내역을 검색할 때 join 사용시 uuid로 해야하는데 못 함.
 */

package org.shiftworksboot.dto;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.ChatRoom;
import org.shiftworksboot.entity.Employee;

import java.util.UUID;
@Getter @Setter
public class ChatRoomDto {

    private String roomId;
    private String roomName;
    private String lastchat;
    private String lastchatTime;
    private Employee employee;

    private static ModelMapper modelMapper = new ModelMapper();

    public ChatRoom createChatRoom() {
        return modelMapper.map(this, ChatRoom.class);
    }

    public static ChatRoomDto of(ChatRoom chatRoom) {
        return modelMapper.map(chatRoom, ChatRoomDto.class);
    }

//    public static ChatRoomDto createChatRoom(Employee employee) {
//        ChatRoomDto chatRoomDto = new ChatRoomDto();
//        chatRoomDto.setRoom_id(UUID.randomUUID().toString());
//        chatRoomDto.setEmployee(employee);
//        return chatRoomDto;
//    }
}

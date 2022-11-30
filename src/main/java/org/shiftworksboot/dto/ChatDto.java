package org.shiftworksboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;
import org.shiftworksboot.entity.Chat;
import org.shiftworksboot.entity.ChatRoom;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ChatDto {

    private String content;

    private String sendtime;

    private String sender;

    private String roomId;

    private String roomName;

    private static ModelMapper modelMapper = new ModelMapper();

    public Chat createChat() {
        return modelMapper.map(this, Chat.class);
    }

    public static ChatDto of(ChatRoom chat) {
        return modelMapper.map(chat, ChatDto.class);
    }
}

package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shiftworksboot.entity.Chat;
import org.shiftworksboot.entity.ChatRoom;
import org.shiftworksboot.service.MessengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MessengerController {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessengerService messengerService;

    // 채팅방 목록 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messenger/chat")
    public String chat(Model model, Authentication auth) {

//        UserDetails userDetails = (UserDetails) auth.getPrincipal();
//
//        List<ChatRoom> chatRoomList = messengerService.getChatRoomList(userDetails.getUsername());
//
//        model.addAttribute("chatRoomList", chatRoomList);

//		log.info("@ChatRoomController, GET Chat / Username : " + userDetails.getUsername());
		List<ChatRoom> chatRoomList = messengerService.getChatRoomList("1");
		model.addAttribute("chatRoomList", chatRoomList);
//
//		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		log.info("==================================");
//		log.info("@ChatController, GET Chat / Username : " + user.getUsername());
//
//		model.addAttribute("userid", user.getUsername());

        return "messenger/MSG_main";
    }


    // stomp

    // client가 send할 수 있는 경로
    // servlet-context.xml에서 설정한 application-destination-prefix와 @messageMapping 경로가 병합
    // "pub/messenger/chat/enter"
//    @MessageMapping("/chatroom/enter")
//    public void enterChatRoom(Chat chat) {
//
//        //log.info("@StompChatController, enterChatRoom");
//        // 입장했을 때
//        chat.setContent(chat.getSender() + "님이 채팅방에 참여하였습니다.");
//        simpMessagingTemplate.convertAndSend("/sub/messenger/chat/room/" + chat.getRoom_id(), chat);
//    }


    @MessageMapping(value="/chatroom/{room_id}")
    @ResponseBody
    public void sendChat(@RequestBody Chat chat, @PathVariable String room_id) {

        //log.info("@StompChatController, sendChat");

        // 채팅 전송
        simpMessagingTemplate.convertAndSend("/sub/chatroom/" + room_id, chat);

    }


    @MessageMapping("/stomp")
    @SendTo("/topic/stomp")
    public ResponseEntity<String> stomp(String request) {


        //log.info("@StompChatController, stomp");

        return new ResponseEntity<String>(request, HttpStatus.OK);
    }
}

package org.shiftworksboot.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.shiftworksboot.dto.ChatDto;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class MessengerController {


    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessengerService messengerService;

    // 채팅방 생성
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messenger/chatroom")
    public void createChatRoom(@PathVariable String chatroomName, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        messengerService.insertChatRoom(userDetails.getUsername());

    }

    // 채팅방 목록 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messenger/chat")
    public String chat(Model model, Authentication auth) {


        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        List<ChatRoom> chatRoomDtoList = messengerService.getChatRoomList(userDetails.getUsername());
        log.info("@MessengerController, GET Chat / Username : " + userDetails.getUsername());

        model.addAttribute("chatRoomDtoList", chatRoomDtoList);


//		List<ChatRoom> chatRoomList = messengerService.getChatRoomList("1");
//		model.addAttribute("chatRoomList", chatRoomList);
//
//		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		log.info("==================================");
//		log.info("@ChatController, GET Chat / Username : " + user.getUsername());
//
//		model.addAttribute("userid", user.getUsername());

        return "messenger/MSG_main";
    }

    // 선택된 채팅방의 정보 요청
    @GetMapping("/messenger/chat/room/{room_id}")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<List<Chat>> getChat(@PathVariable("room_id") String roomId) {
        log.info("@ChatRoomController, GET getChat...............");
        return new ResponseEntity<>(messengerService.getChatList(roomId), HttpStatus.OK);
    }

    // 메시지 전송 요청
    @PostMapping(value = "/messenger/chat/send/{room_id}")
    @ResponseBody
    public ResponseEntity<String> sendChat(@PathVariable("room_id") String room_id, @RequestBody ChatDto chatDto) {
        log.info("@MessengerController, POST sendMessage...............");
        log.info("@MessengerController, content : " + chatDto.getContent());

        try {
            messengerService.insertChat(chatDto);
        } catch (Exception e) {
             new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }

        // insert 유무에 따라 헤더값을 다르게 전달
        return new ResponseEntity<>("success", HttpStatus.OK); // 200

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

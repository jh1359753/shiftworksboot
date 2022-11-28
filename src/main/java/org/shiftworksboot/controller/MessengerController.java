package org.shiftworksboot.controller;

import org.shiftworksboot.entity.ChatRoom;
import org.shiftworksboot.service.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@Controller
public class MessengerController {

    @Autowired
    private MessengerService messengerService;

    // 채팅방 목록 조회
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/messenger/chat")
    public String chat(Model model, WebSocketSession session, Authentication auth) {

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
}

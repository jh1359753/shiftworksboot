//package org.shiftworksboot.config;
//
//import org.shiftworksboot.handler.MessengerHandler;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.socket.WebSocketHandler;
//import org.springframework.web.socket.config.annotation.EnableWebSocket;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
//
//@Configuration
//@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(messengerHandler(), "/messengerHandler").addInterceptors(new HttpSessionHandshakeInterceptor())
//                .setAllowedOrigins("*").withSockJS();
//    }
//
//    @Bean
//    public WebSocketHandler messengerHandler() {
//        return new MessengerHandler();
//    }
//}

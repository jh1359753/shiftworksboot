/**
 * 
 */

var messengerService = (function () {

    var csrf_token = $("meta[name='_csrf']").attr("content");
    var csrf_header = $("meta[name='_csrf_header']").attr("content");

 	function getChatRoom(emp_id, callback, error){

        console.log("messengerService.js : getChatRoom : csrf_header : " + csrf_header);
        console.log("messengerService.js : getChatRoom : csrf_token : " + csrf_token);
 		
 		$.ajax({
 			type: 'GET',
 			url: '/messenger/chatroom',
 			beforeSend : function(xhr){
                xhr.setRequestHeader(csrf_header, csrf_token);
            },
            success: function (data, status, xhr) {
                if (callback) {
                    callback(data);
                }
            },
            error : function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
 		});
 	
 	}
    
    function getChat(room_id, callback, error) {

        console.log("messengerService.js : getChat : room_id : " + room_id);
        console.log("messengerService.js : getChat : csrf_header : " + csrf_header);
        console.log("messengerService.js : getChat : csrf_token : " + csrf_token);

        $.ajax({
            type: 'GET',
            url: '/messenger/chat/room/' + room_id,
            dataType: 'json',
            beforeSend : function(xhr){
                xhr.setRequestHeader(csrf_header, csrf_token);
            },
            success: function (data, status, xhr) {
                console.log("messengerService.js : getChat : success");
                if (callback) {
                    callback(data);
                }
            },
            error : function (xhr, status, err) {
                console.log("messengerService.js : getChat : error" + err);
                if (error) {
                    error(err);
                }
            }
        });
    } // end getChat function


    

   function sendChat(chat, callback, error) {

    	$.ajax({
    		type: 'POST',
    		url: '/messenger/chat/send/' + chat.room_id,
    		data : chat,
			contentType : 'application/json; charset=utf-8',
			dataType: 'text',
    		beforeSend : function(xhr){
                xhr.setRequestHeader(csrf_header, csrf_token);
            },
    		success: function (result, status, xhr) {
                if (callback) {
                    callback(result);
                }
            },
            error : function (xhr, status, err) {
                if (error) {
                    error(err);
                }
            }
    	})
    
        //socket.send(chat);    

	}//end sendChat
	
	


return {
    getChat: getChat,
    sendChat: sendChat,
    getChatRoom: getChatRoom
};

}) ();






/*
 var socket = new SockJS('http://localhost:8081/messenger/chat');
     
 socket.onmessage = onMessage;
 socket.onclose = onClose;
 socket.onopen = onOpen;
 

 // 서버와 연결되었을때
 function onOpen(evt) {
     console.log("onOpen");
 // 서버로부터 메시지를 받았을 때
 
 }
 function onMessage(chat) {		
     console.log("onMessage " + chat);
      $('.chat .header-chat .name').attr('id', "chat_" + chat.room_id);
	 
	 // 채팅 내용이 있을 때만 출력
	 if(chat.content != null){

	// 상대방의 채팅 내용
	 		if(chat.sender != login_id){
		 		var content = '<div class="message" id="msg_'+ chat.chat_id + '">' +
		 		'<div class="photo" style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);">' +
		 		'<div class="online"></div></div>' + 
		 		'<p class="text">'+ chat.content + '</p>	</div>' +
		 		'<p class="time">' + chat.sendtime.substr(0,16) + '</p>';
		 		$(".messages-chat").append(content);
		 		
	 		}else{	// 나의 채팅 내용
	 			var content = '	<div class="message text-only">' +
	 				'<div class="response">' +
	 				'<p class="text">'+ chat.content +'</p>' +
	 				'</div></div>' +
	 				'<p class="response-time time">' + chat.sendtime.substr(0,16) + '</p>';
	 			$(".messages-chat").append(content);
	 		}
	 		
	 	$(".messages-chat").animate({ scrollTop: $(".messages-chat")[0].scrollHeight });	    		 			
 		}

 }
 
 
 // 서버와 연결을 끊었을 때
 function onClose(evt) {
     console.log("onClose");
     
 //    $(".messages-chat").append("끊김");
 }
 
  */

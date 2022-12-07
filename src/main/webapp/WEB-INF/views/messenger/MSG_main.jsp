<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<title>Shiftworks_messenger</title>

<!-- icon을 사용하기 위함 -->
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css'>
<link rel='stylesheet' 
	href='https://fonts.googleapis.com/css?family=Montserrat'>
<link rel='stylesheet'
	href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha.6/css/bootstrap.min.css'>

<!-- JQuery 라이브러리 -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- autocomplete  -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>

<!-- timeago 라이브러리 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.js"></script>

<!-- socket 라이브러리 -->
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

<!-- 실시간 검색을 위한 라이브러리 -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<!-- custom style -->
<link rel="stylesheet" href="/resources/css/messenger.css">

<script type="text/javascript" src="/resources/js/messenger.js" />
<script type="text/javascript" src="/resources/js/sockjs.js" />
<!-- <script type="text/javascript" src="/resources/js/messenger/event.js" /> -->

<script type="text/javascript"></script>
</head>
<body class="container">
	<!-- partial:index.partial.html -->

	<div class="container">
		<div class="row">
			<nav class="menu">
				<ul class="items">
					<li class="item"><i class="fa fa-user" aria-hidden="true"></i>
					</li>
					<li class="item item-active"><i class="fa fa-commenting"
						aria-hidden="true"></i></li>
					<li class="item"><i class="fa fa-folder-o" aria-hidden="true"></i>
					</li>
				</ul>
			</nav>

			<!-- 왼쪽 : 채팅방 목록 -->
			<!-- 검색창 -->
			<section class="discussions">
				<div class="discussion search">
					<div class="searchbar">
						<i class="fa fa-search" aria-hidden="true"></i> <input type="text"
							placeholder="Search..."></input>
					</div>
				</div>

				<!-- ChatRoom DB에서 채팅방 리스트 출력 -->
				<c:forEach items="${chatRoomDtoList}" var="chatRoomDto">

					<div class="discussion" id="${chatRoomDto.roomId }" data-room-id="${chatRoomDto.roomId }">
						<div class="photo"
							style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);">
							<div class="online"></div>
						</div>
						<div class="desc-contact">
							<p class="name">${chatRoomDto.roomName }</p>
							<p class="message">${chatRoomDto.lastchat }</p>
						</div>
						<div class="timer" id="timer_${chatRoomDto.roomId }"></div>
						<script type="text/javascript">

							var roomId = "<c:out value='${chatRoomDto.roomId }'/>";
							var roomName = "<c:out value='${chatRoomDto.roomName }'/>";

							var time = '${chatRoomDto.lastchatTime }';
						
							if(time != ""){
								console.log("시간 : " + time);
								var lastchat = new Date(time);
								
								var timeago = moment(lastchat).fromNow();
								console.log(timeago);
							
								document.getElementById("timer_${chatRoomDto.roomId }").innerText = timeago;
							}else{
								$('.discussion .timer').hide();
							}
							
						</script>

					</div>
				</c:forEach>
			</section>


			<section class="chat">
				<div class="header-chat">
					<i class="icon fa fa-user-o" aria-hidden="true"></i>
					<p class="name" id="name"></p>
					<i class="icon clickable fa fa-ellipsis-h right" aria-hidden="true"></i>
				</div>


				<div class="messages-chat">
					
					<!-- <div class="message">
						<div class="photo"
							style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);">
							<div class="online"></div>
						</div>
						<p class="text">Hi, how are you ?</p>
					</div>
					<div class="message text-only">
						<p class="text">What are you doing tonight ? Want to go take a
							drink ?</p>
					</div>
					<p class="time">14h58</p>
					<div class="message text-only">
						<div class="response">
							<p class="text">Hey Megan ! It's been a while 😃</p>
						</div>
					</div>
					<div class="message text-only">
						<div class="response">
							<p class="text">When can we meet ?</p>
						</div>
					</div>
					<p class="response-time time">15h04</p>
					<div class="message">
						<div class="photo"
							style="background-image: url(https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1050&q=80);">
							<div class="online"></div>
						</div>
						<p class="text">9 pm at the bar if possible 😳</p>
					</div>
					<p class="time">15h09</p> -->
				</div>
				<div class="footer-chat">
					<i class="icon fa fa-paperclip clickable" style="font-size: 25pt;" aria-hidden="true"></i> 
					<input type="text" class="write-message" placeholder="Type your message here"></input> 
					<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
					<i class="icon send fa fa-paper-plane-o clickable" aria-hidden="true"></i>
				</div>
			</section>
		</div>
	</div>

	<sec:authentication property="principal.username" var="loginId" />

	<script type="text/javascript">

$(document).ready(function() {

	console.log("메신저 실행");
	
	var stompClient = null;
	
	// 로그인된 사번 
	var loginId = "<c:out value='${loginId}'/>";
    
    // 즉시 실행 함수 : 채팅방이 선택되지 않았을 때 채팅 내용이 보이지 않도록 함
    var init = function(){
    	$('.chat').hide();
    	
    	 $('.timer').each(function(index, item){
      		if($('.timer').text != ""){
      			console.log($(item).text());
      		}else{
      			console.log("e");
      		}
      	
      	});
    }();

    // 전송 버튼 눌렀을 때 메시지 전송
    $('.send').on("click", function(e) {
        console.log("btn_send");
		sendEvent();
    });
 
    
    // 메시지를 입력하고 enter 키를 입력했을 때 메시지 전송
    $('.write-message').on("keypress", function(e) {
            
        if(e.keyCode == '13'){
            console.log("btn_send");
            sendEvent();
        }
    });
    
    
    // 메뉴를 눌렀을 때
    $('.menu .items .item').on("click", function(e){
    
    	 $('.menu .items .item').each(function(index, element){
    	 	$(element).attr("class", "item");
    	 });
    	 
    	 $(this).attr("class", "item item-active");
    });
    

    // 채팅방 눌렀을 때
    $('.discussions .search').nextAll().on("click", function(e){
    	
    	// 각각의 채팅방 목록에 클릭 이벤트 추가
    	 $('.discussion').each(function(index, item){

    	 	// 검색창이 있는 div에 이벤트 방지를 위한 조건
    	 	var classValue = $(item).attr("class");
 
    	 	if(classValue == 'discussion search'){     	 	
    	 		$(item).attr("class", "discussion search");
    	 	}else{
    	 		$(item).attr("class", "discussion");
    	 	}
    	 });
 
    	 // 검색창이 있는 div에 이벤트 방지를 위한 조건문
    	 // 선택된 채팅방에 선택 표시
    	 if($(this).attr("class") != 'discussion search'){ 
    		$(this).attr("class", "discussion message-active");
       	 }

    	 // 채팅방의 크기 줄이고, 채팅 내용을 보여줌
    	 $('.discussions').css('width', '35%');
    	 $('.chat').hide().fadeIn(500).show();
    	 $('.timer').css('font-size', '9px');
    	 console.log("room_id : " + $(this).attr("id"));

		 // 선택된 채팅방 이름 출력
		$('.chat .header-chat .name').empty().append(roomName);

		 // 소켓 연결
    	 connect($(this).attr("id"));

		 // 채팅방 생성
		// messengerService.getChatRoom(loginId, function (data){
		//
		// 	console.log("getChatRoom : " + data);
		// })
    	 
    	 // 선택된 채팅방의 지난 채팅 내역 가져옴
    	 messengerService.getChat($(this).attr("id"), function(data){
 			console.log("getchat : ");
    		$('.messages-chat').empty(); 
    		
    	 	if(data != null){
    	 		console.log(data);
    		 	for(var i = 0; i < data.length; i++){
    		 		printChat(data[i]);
    		 	}

    	 	}
    	 });  
    });
    
    
 	// 클라이언트에서 stomp 연결하는 코드
	 // 서버에서 설정한 end-point로 stomp를 생성한다.
	 // 서버에서 브로커에 설정해준 "sub"라는 prefix에 room_id를 구독함.
	 // 메세지를 보내면 서버를 거쳐 구독하고 있는 클라이언트들에서 메세지를 전송
    function connect(roomId){

    	if(stompClient != null){
    		disconnect(stompClient);
    	}
    	
    	socket = new SockJS('/messenger/chat');	// servlet-context에서 설정한 sockJS 연결 주소
    	stompClient = Stomp.over(socket);
    	stompClient.connect({}, function(frame){
    		
    		console.log('connected : ' + frame);
    		
    		// /room/{roomId}를 구독
    		stompClient.subscribe('/sub/chatroom/' + roomId, function(chat){
				printChat(JSON.parse(chat.body));
    		});    		
    	});
    }
 	
 	function disconnect(){
 		if(stompClient !== null){
 			stompClient.disconnect();
 		}
 		console.log("Disconnected");
 	}
    
  /*   $('.search').autocomplete({
    	source:function(request, response){
    		
    		$.ajax({
    			type: 'get',
    			url: '/messenger/chat',
    			dataType: 'json'
    			
    		})
    		
    	}
    })	//end search
     */
    function sendEvent(){
    	// 채팅 내용 가져오기
        var content =  $('.write-message').val();
        console.log("전송 버튼 클릭 이벤트 : content : " + content);
       
        if(content != "" && content != null){
        
        // 현재 시간 구하기
        const d = new Date();
		const timestamp = new Date(+d + 3240 * 10000).toISOString().replace('T', ' ').replace(/\..*/, '');
        console.log("전송 버튼 클릭 이벤트 : sendtime : " + timestamp);	
        
        var chat = {
	            content: $('.write-message').val(),
	            sendtime: timestamp,
	            sender: loginId,
	            roomId: roomId
	          };
        console.log("전송 버튼 클릭 이벤트 : roomId : " + roomId);
        stompClient.send('/sub/chatroom/'+ roomId, {}, JSON.stringify(chat));

			messengerService.sendChat(JSON.stringify(chat), function(result){
				//console.log("메시지 받은 결과 : " + result);

				if(result == 'success'){
					console.log("메시지 전송 성공");
				}

			});

        
        // 채팅 입력창 비우고 포커스
        $('.write-message').val('').focus();

        }
        
        var vScrollDown = $('.messages-chat').prop('scrollHeight');
    	$('messages-chat').scrollTop(vScrollDown);
    }
  
    function printChat(chat){
   	 
   	 console.log("printChat : " + chat);
   	 
   	 $('.chat .header-chat .name').attr('id', "chat_" + chat.roomId);
   	 
   	 // 채팅 내용이 있을 때만 출력
   	 if(chat.content != null){

   			// 상대방의 채팅 내용
   		if(chat.sender != loginId){
   			var content = '<div class="message">' +
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
});


	window.BeforeLoadEvent = function(){
		disconnect();
	}

</script>
</body>
</html>

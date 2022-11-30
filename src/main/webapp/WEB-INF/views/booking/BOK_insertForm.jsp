<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>--%>
<%@include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />

<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<!-- timePicker -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

    <!— Popperjs —>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.2/dist/umd/popper.min.js"
          crossorigin="anonymous"></script>
    <!— Tempus Dominus JavaScript —>
    <script src="https://cdn.jsdelivr.net/gh/Eonasdan/tempus-dominus@master/dist/js/tempus-dominus.js"
          crossorigin="anonymous"></script>

    <!— Tempus Dominus Styles —>
    <link href="https://cdn.jsdelivr.net/gh/Eonasdan/tempus-dominus@master/dist/css/tempus-dominus.css"
          rel="stylesheet" crossorigin="anonymous">

<meta charset="UTF-8">
<title>Book your meetings here</title>
</head>
<body>
	<div class="container">
	<div class="col-12"><h2>회의실 예약</h2></div>
	<%-- <div>직원정보<br>
		<p>principal: <sec:authentication property="principal"/></p>
		<p>사용자아이디: <sec:authentication property="principal.username"/></p>
		<p>사용자권한: <sec:authentication property="principal.Authorities"/></p>
	</div> --%>


	<div class="insertForm" id="insertForm">
			<table>
			<div class="form-body">
				<%--<tr>
				<div class="form-group">
					<td><label>직원번호</label></td>
					<td><input class="form-control" name="emp_id" type="text" readonly="readonly"
					value="<sec:authentication property="principal.username"/>"></td>
					<!-- <input name="emp_id" type="text" readonly="readonly" value="사원번호 자동생성"></td> -->
				</div>
				</tr>
				<tr>
					<td>
					<sec:authentication property="principal" var="pinfo"/>
						<label>부서</label></td>
						<td><input class="form-control" name="dept_id" value="${pinfo.employee.dept_id }" readonly="readonly"/></td>
				</tr>--%>
				<tr>
					<td><label>자원 선택</label></td>
					<td>
					<div class="form-floating" style="width: 200px;">
							<select name="meetingRoom" class="form-select" id="meetingRoom"
								aria-label="Floating label select example">
								<option value="MTR101" selected="selected">회의실101</option>
								<option value="MTR202">회의실202</option>
								<option value="CFR305">컨퍼런스룸305</option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td><label>예약일자&ensp;</label></td>
					<td><input class="form-control" style="width: 200px;" type="text" name="book_date" id="Date" placeholder="날짜를 선택하세요"></td>
				</tr>
				<tr>
					<td><label>예약 시간&ensp;</label></td>
					<td>
					<div class="form-floating" style="width: 200px;">
							<select name="book_begin" class="form-select" id="book_begin"
								aria-label="Floating label select example">
								<option value="09" selected>09:00-11:00</option>
								<option value="11">11:00-13:00</option>
								<option value="13">13:00-15:00</option>
								<option value="15">15:00-17:00</option>
								<option value="17">17:00-19:00</option>
							</select> <label for="floatingSelect">예약시간 선택</label>
						</div>
					</td>
				</tr>
				<tr>
					<div class="form-group">
					<td><label>예약명</label></td>
					<td><input class="form-control" name="book_title" type="text" placeholder="제목을 입력하세요"><br></td>
					</div> 
				</tr>
				<tr>
					<div class="form-group">
					<td><label>예약내용</label></td>
					<td><textarea class="form-control" rows="6" cols="70" name="book_content" placeholder="내용을 입력하세요" ></textarea><br></td>
					</div>
				</tr>
				<tr>
					<td><input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"> </td>
				</tr>
				</table>
			
			<button id="submitBtn" type="button" class="btn btn-primary">예약</button>
			</div> <!-- end form-body class -->

	</div> <!-- end insertForm class -->

	<!-- <b><input id="datetimepicker" type="text" name="book_date"><br></b> -->
	</div> <!-- end container -->


	
	<!--javaScript 스크립트-->
	<script type="text/javascript" src="/resources/js/booking.js"></script>
	<script type="text/javascript">
	
	
	
	$(document).ready(function(){

		console.log(bookingService);

		//날짜 선택 UI(datepicker)
		$(function() {
		    $( "#Date" ).datepicker({
		    	dateFormat: "yy-mm-dd"
		    });
		});

		//예약폼 데이터
		var form = $(".insertForm");
		// var book_id = form.find("input[name='book_id']");
		var meetingRoom = form.find("select[name='meetingRoom']");
		var book_date = form.find("input[name='book_date']");
		var book_begin = form.find("select[name='book_begin']");
		var book_title = form.find("input[name='book_title']");
		var book_content = form.find("textarea[name='book_content']");
		var csrf_token = $("meta[name='_csrf']").attr("content");
		var csrf_header = $("meta[name='_csrf_header']").attr("content");

	// 	//제출 버튼 클릭 시 bookingData에 데이터 JSON형식으로 저장, 예약 프로세스 진행 후 결과값 출력
		$('#submitBtn').on("click", function(e){
			// $('.submitBtn').prop("type", "submit");

			var bookingData = $(".insertForm");

			var bookingData = {
					// book_id: book_id.val(), //자동생성이라 삭제 예정
					meetingRoom:$("select[name='meetingRoom']").val(),
					// emp_id: emp_id.val(), //로그인 세션으로 구현
					// dept_id: dept_id.val(), //로그인 세션으로 구현
					bookBegin: book_begin.val(),
					bookDate: book_date.val(),
					bookTitle: book_title.val(),
					bookContent: book_content.val(),
					csrf_token:csrf_token,
		            csrf_header:csrf_header
			};


			bookingService.insertBooking(bookingData, function(result){
						if(result == "success"){
							alert("예약되었습니다");
							$(location).attr('href', '/booking/main');
						}else if(result =="fail"){
							alert("이미 예약된 시간입니다.");
						}
					})//end insertBooking
	});

	});

	
	</script>
</body>
<%@include file="../includes/footer.jsp" %>
</html>
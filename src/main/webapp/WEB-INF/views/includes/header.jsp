<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<!-- JQuery 라이브러리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

<!-- Custom CSS -->
<link href="/resources/css/header.css" rel="stylesheet">

<script type="text/javascript" src="/resources/js/post.js"></script>

<title>Insert title here</title>
</head>
<body>

	<!-- naviBar headBar-->
	<!-- <div class="row">
		<nav class="nav-headbar"></nav>
	</div> -->
	<!-- end row -->


	<!-- naviBar Sidebar -->
	<div class="col-2">
		<nav class="nav-sidebar flex-column fixed-top" id="customSidebar">

			<!-- 로고 -->
			<div class="row" id="shiftworksLogo">
				<a href="/"
					class="navbar-brand text-white text-center d-block mx-auto py-3 mb-4 bottom-border">
					<img id="logo" alt="로고자리" src="/resources/css/shiftwork_logo.png"
					width="200" class="">
				</a>
			</div>

			<div class="alarm">
				<span class="badge text-bg-danger">+10</span>
				<!-- <button type="button" class="btn btn-primary position-relative" style="size: 10px;"> Alarm
				<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
					99+ 
				<span class="visually-hidden">unread messages</span>
				</span>
			</button>  -->
			</div>
			<!-- 알림 버튼 -->

			<!-- 프로필 -->
			<div class="row">
				<div class="bottom-border pd-3" id="profile"
					style="margin-bottom: -20px;">
					<div class="card-profile" style="">
						<!-- <a href="#" class="text-white">홍길동</a> -->
						<a href="#multiCollapseUser" class="" data-bs-toggle="collapse" role="button" aria-expanded="false"
							aria-controls="multiCollapseUser" style="text-decoration: none; color: white;" id="userName">
						<svg xmlns="http://www.w3.org/2000/svg" width="60" height="60" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
						  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
						  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
						</svg>
						<!-- <img
							id="userProfile" alt="유저사진" src="/resources/css/bonoUser.png"
							width="80" class="rounded-circle" style="margin-top: -10px;"> -->
							<sec:authentication property="principal.username"></sec:authentication> <!-- 사용자 이름 --></a>
					</div> <!-- end card -->

				</div> <!-- end profile -->

				<div class="col">
					<div class="collapse multi-collapse" id="multiCollapseUser">
						<div class="card card-body" style="height: 140px; margin-top: -15px;">
							<ul>
								<a href="/messenger/chat" onclick="window.open(this.href, '_blank', 'width=800, height=600'); return false;"><li>메신저</li></a> <br>
								<!-- <a href="/messenger"><li>메신저</li></a><br> -->
								<a href="/myaccount/info"><li>나의 계정 관리</li></a> <br>
								<a href="/myaccount/orgchart"><li>조직도 보기</li></a> <br>
								<a href="/customLogout"><li>로그아웃</li></a> <br>
							</ul>
						</div> <!-- end card -->
					</div> <!-- end collapse -->
				</div> <!-- end col -->
			</div> <!-- end row -->




			<!-- 관리자 버튼(관리자 계정 관리) -->
			<div class="row">
				<div class="col">
					<ul class="navbar-nav flex-column mt-4">
						<li class="nav-item"><a href="#multiCollapseMGR3"
							class="nav-link text-white p-3 mb-2 sidebar-link"
							data-bs-toggle="collapse" role="button" aria-expanded="false"
							aria-controls="multiCollapseMGR3"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-menu-button-fill" viewBox="0 0 16 16">
				  <path d="M1.5 0A1.5 1.5 0 0 0 0 1.5v2A1.5 1.5 0 0 0 1.5 5h8A1.5 1.5 0 0 0 11 3.5v-2A1.5 1.5 0 0 0 9.5 0h-8zm5.927 2.427A.25.25 0 0 1 7.604 2h.792a.25.25 0 0 1 .177.427l-.396.396a.25.25 0 0 1-.354 0l-.396-.396zM0 8a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V8zm1 3v2a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1v-2H1zm14-1V8a1 1 0 0 0-1-1H2a1 1 0 0 0-1 1v2h14zM2 8.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0 4a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z" />
				</svg> &ensp;사원 계정 관리
						</a></li>
					</ul>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->


			<div class="row">
				<div class="col">
					<div class="collapse multi-collapse" id="multiCollapseMGR3">
						<div class="card card-body"
							style="height: 130px; border-width: 0;">
							<ul>
								<a href="/manager/new"><li>계정 생성</li></a> <br>
								<a href="/manager/list"><li>계정 관리</li></a> <br>
								<!-- <a href="/account/form"><li>계정 양식 설정</li></a><br> -->
								<!-- <a href="/account/{empid}"><li>계정 삭제</li></a><br> -->
								<a href="/manager/retiree-list"><li>퇴사자 리스트 관리</li></a> <br>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<!-- 업무관리 버튼 -->
			<div class="row">
				<div class="col">
					<ul class="navbar-nav flex-column mt-4">
						<li class="nav-item"><a href="/task/pages/all/empty/empty/1"
							class="nav-link text-white p-3 mb-2 sidebar-link" role="button">
							<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-list-check" viewBox="0 0 16 16">
				<path fill-rule="evenodd" d="M5 11.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3.854 2.146a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 3.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 1 1 .708-.708L2 7.293l1.146-1.147a.5.5 0 0 1 .708 0zm0 4a.5.5 0 0 1 0 .708l-1.5 1.5a.5.5 0 0 1-.708 0l-.5-.5a.5.5 0 0 1 .708-.708l.146.147 1.146-1.147a.5.5 0 0 1 .708 0z" />
				</svg> &ensp;업무관리 </a></li>
					</ul>
				</div> <!-- end col -->
			</div> <!-- end row -->



			<!-- 일정관리 버튼 -->
			<div class="row">
				<div class="col">
					<ul class="navbar-nav flex-column mt-4">
						<li class="nav-item"><a href="/schedule/main"
							class="nav-link text-white p-3 mb-2 sidebar-link" role="button">
							<svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-calendar3" viewBox="0 0 16 16">
				  <path d="M14 0H2a2 2 0 0 0-2 2v12a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zM1 3.857C1 3.384 1.448 3 2 3h12c.552 0 1 .384 1 .857v10.286c0 .473-.448.857-1 .857H2c-.552 0-1-.384-1-.857V3.857z" />
				  <path d="M6.5 7a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm-9 3a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2zm3 0a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
				</svg> &ensp;일정관리
						</a></li>
					</ul>
				</div> <!-- end col -->
			</div> <!-- end row -->



			<!-- 전자결제 버튼 -->
			<div class="row">
				<div class="col">
					<ul class="navbar-nav flex-column mt-4">
						<li class="nav-item"><a href="#multiCollapseAPR"
							class="nav-link text-white p-3 mb-2 sidebar-link"
							data-bs-toggle="collapse" role="button" aria-expanded="false"
							aria-controls="multiCollapseAPR"> <svg
									xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-briefcase" viewBox="0 0 16 16">
				  <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z" />
				</svg> &ensp;전자결재
						</a></li>
					</ul>
				</div> <!-- end col -->
			</div> <!-- end row -->

			<div class="row">
				<div class="col">
					<div class="collapse multi-collapse" id="multiCollapseAPR">
						<div class="card card-body"
							style="height: 130px; border-width: 0;">
							<ul>
								<a href="/approval/new"><li>결재 문서 작성</li></a><br>
								<a href="/approval/list"><li>전체 결재문서함</li></a><br>
								<a href="/approval/receivedList"><li>결재전 문서</li></a><br>
							</ul>
						</div>
					</div>
				</div>
			</div>


			<!-- 게시판 버튼 -->
			<div class="row">
				<div class="col">
					<ul class="navbar-nav flex-column mt-4">
						<li class="nav-item"><a href="#multiCollapseBOA"
							class="nav-link text-white p-3 mb-2 sidebar-link"
							data-bs-toggle="collapse" role="button" aria-expanded="false"
							aria-controls="multiCollapseBOA"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
									fill="currentColor" class="bi bi-card-text" viewBox="0 0 16 16">
				  <path d="M14.5 3a.5.5 0 0 1 .5.5v9a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h13zm-13-1A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-9A1.5 1.5 0 0 0 14.5 2h-13z" />
				  <path d="M3 5.5a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9a.5.5 0 0 1-.5-.5zM3 8a.5.5 0 0 1 .5-.5h9a.5.5 0 0 1 0 1h-9A.5.5 0 0 1 3 8zm0 2.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5z" />
				</svg> &ensp;게시판
						</a></li>
					</ul>
				</div>
				<!-- end col -->
			</div>
			<!-- end row -->

			<div class="row">
				<div class="col">
					<div class="collapse multi-collapse" id="multiCollapseBOA">
						<div class="card card-body"
							style="height: 190px;">
							<ul class="board">
								<a href="/board/new"><li>게시글 등록</li></a><br>
								<a href="/board/newboard"><li>새 게시판 생성</li></a><br>
						</div>
					</div>
				</div> <!-- end col -->
			</div>

				<!-- 문서함 버튼 -->
			<div class="row">
					<div class="col">
						<ul class="navbar-nav flex-column mt-4">
							<li class="nav-item"><a href="#multiCollapseDOC" class="nav-link text-white p-3 mb-2 sidebar-link"
								data-bs-toggle="collapse" role="button" aria-expanded="false"
								aria-controls="multiCollapseDOC"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
								fill="currentColor" class="bi bi-archive" viewBox="0 0 16 16">
						  <path d="M0 2a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v2a1 1 0 0 1-1 1v7.5a2.5 2.5 0 0 1-2.5 2.5h-9A2.5 2.5 0 0 1 1 12.5V5a1 1 0 0 1-1-1V2zm2 3v7.5A1.5 1.5 0 0 0 3.5 14h9a1.5 1.5 0 0 0 1.5-1.5V5H2zm13-3H1v2h14V2zM5 7.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z" />
						</svg> &ensp;문서함
						</a></li>
						</ul>
					</div> <!-- end col -->
			</div> <!-- end row -->

				<div class="row">
					<div class="col">
						<div class="collapse multi-collapse" id="multiCollapseDOC">
							<div class="card card-body"
								style="height: 130px;">
								<ul>
									<a href="/document/myApproval/1"><li>결재문서함</li></a><br>
									<a href="/document/myDoc/1"><li>내가 쓴 게시물</li></a><br>
									<a href="/document/scrap/1"><li>스크랩 문서함</li></a><br>
									<a href="/document/deptDoc/1"><li>부서수신함</li></a><br>
								</ul>
							</div> <!-- end card-body -->
						</div> <!-- end collapse -->
					</div> <!-- end col -->
				</div> <!-- end row -->


				<!-- 회의실 예약 버튼 -->
				<div class="sidebar-booking">
					<ul class="navbar-nav flex-column mt-4">
						<li class="nav-item">
						<a class="nav-link text-white p-3 mb-2 sidebar-link" data-bs-toggle="collapse" href="#multiCollapseBOK" role="button"
							aria-expanded="false" aria-controls="multiCollapseBOK"> <svg xmlns="http://www.w3.org/2000/svg" width="25" height="25"
							fill="currentColor" class="bi bi-microsoft-teams" viewBox="0 0 16 16">
					  <path d="M9.186 4.797a2.42 2.42 0 1 0-2.86-2.448h1.178c.929 0 1.682.753 1.682 1.682v.766Zm-4.295 7.738h2.613c.929 0 1.682-.753 1.682-1.682V5.58h2.783a.7.7 0 0 1 .682.716v4.294a4.197 4.197 0 0 1-4.093 4.293c-1.618-.04-3-.99-3.667-2.35Zm10.737-9.372a1.674 1.674 0 1 1-3.349 0 1.674 1.674 0 0 1 3.349 0Zm-2.238 9.488c-.04 0-.08 0-.12-.002a5.19 5.19 0 0 0 .381-2.07V6.306a1.692 1.692 0 0 0-.15-.725h1.792c.39 0 .707.317.707.707v3.765a2.598 2.598 0 0 1-2.598 2.598h-.013Z" />
					  <path d="M.682 3.349h6.822c.377 0 .682.305.682.682v6.822a.682.682 0 0 1-.682.682H.682A.682.682 0 0 1 0 10.853V4.03c0-.377.305-.682.682-.682Zm5.206 2.596v-.72h-3.59v.72h1.357V9.66h.87V5.945h1.363Z" />
					</svg> &ensp;회의실 예약
						</a></li>
					</ul> <!-- end 회의실 예약버튼 -->

					<div class="row">
						<div class="col">
							<div class="collapse multi-collapse" id="multiCollapseBOK">
								<div class="card card-body"
									style="height: 100px; border-width: 0;">
									<ul>
										<a href="/booking/main" class="btmmenu"><li>월별 예약 현황</li></a> <br>
										<a href="/booking/new"><li>예약하기</li></a> <br>
										<a href="/booking/list"><li>전체 예약보기</li></a> <br>
									</ul>
								</div> <!-- end card-body -->
							</div> <!-- end collapse -->
						</div> <!-- end col -->
					</div> <!-- end row -->
				</div> <!-- end sidebar-booking -->
				
		</nav>
	</div>




	<!--javaScript 스크립트-->
	<script type="text/javascript" src="/resources/js/booking.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		//console.log("---------------------JS '***header***' TESTING---------------------");
		
		$(".nav-item").on("click", function(e){
			
			console.log("click success");
		});
		
		
		
		
		//동적으로 게시판 메뉴 추가해주기
	     postService.boardList(function(result){

	        
	        for(var i=0;i<result.length;i++){
	           
	           $(".board").append(
               "<a href='/board/list?b_id="+result[i].b_id+"'><li>"+result[i].name+"</li></a><br>"
	           )
	        }
	         
	     });
		
		
		
	});
	
	</script>



</body>
</html>

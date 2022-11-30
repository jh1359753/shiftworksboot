<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>--%>
<%@include file="../includes/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<link rel="stylesheet" href="/resources/css/post.css">

<meta charset="UTF-8">

<title></title>
</head>
<div class="container">
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">상세페이지</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">


			<!-- /.panel-heading -->
			<div class="panel-body">
				
				
				<!-- 목록 이전글, 다음글 보여주기-->
				<table class="table">
					<tbody>
						<tr>
							<th scope="row" class="pre_index">이전글</th>
						</tr>
						<tr>
							<th scope="row" class="post_index">다음글</th>	
						</tr>
					</tbody>
				</table>
			

				<button id='scrapBtn' class='btn btn-primary btn-xs pull-right'>스크랩하기</button>
				<!--게시글 상세 폼-->
				<div class="form-group">
					<label>게시판번호</label> <input class="form-control" name='b_id' value='<c:out value="${post.board.b_id }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>게시글번호</label> <input class="form-control" name='post_id' value='<c:out value="${post.post_id }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>작성자</label> <input class="form-control" name='name' value='<c:out value="${post.post_id }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>작성부서</label> <input class="form-control" name='dept_id' value='<c:out value="${post.post_id }"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>제목</label> <input class="form-control" name='post_name' value='<c:out value="${post.pname}"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea class="form-control" rows="20" cols="150" name='post_content' readonly="readonly">
						<c:out value="${post.content}" />
					</textarea>
				</div>
				<div class="form-group">
					<label>작성일</label> 
					<input class="form-control" name='post_regdate' value='<c:out value="${post.regdate}"/>' readonly="readonly">
				</div>
				<div class="form-group">
					<label>수정일</label> 
					<input class="form-control" name='post_updatedate' value='<c:out value="${post.updatedate}"/>' readonly="readonly">
				</div>
				<!--첨부파일-->
				<%--<div class="mb-3">
					<label for="formFileSm" class="form-label file">첨부파일</label>
						<ul class="boardFiles">
							<c:forEach items="${ post.fileList }" var="f">
							<li data-uuid="${ f.uuid }" data-file_name="${ f.file_name }"
									data-file_src="${ f.file_src }">
									<a href="#"><c:out value="${ f.file_name }"/></a>
							</li>
							</c:forEach>
						</ul>
				</div>--%>
				<%--<div class="form-group" hidden="hidden">
					<label>emp_id</label> 
					<input class="form-control" name='emp_id' value='<c:out value="${pinfo.username}"/>' readonly="readonly">
				</div>--%>
				<%--<sec:authentication property="principal" var="pinfo"/>
					<sec:authorize access="isAuthenticated()">
						<c:if test="${pinfo.username eq post.emp_id}">--%>
							<button id='modifyBtn' class='btn btn-primary btn-xs pull-right'>글수정하기</button>
						<%--</c:if>
					</sec:authorize>	--%>
				<button id="listBtn" class="btn btn-primary">목록보기</button>



				<%--<form id='operForm' action="/board/modify" method="get">
					<input type='hidden' id='post_id' name='post_id' value='<c:out value="${post.post_id}"/>'> 
					<input type='hidden' name='pageNum'  value='<c:out value="${cri.pageNum}"/>'> 
					<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'> 
					<input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
				</form>--%>

				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="post_id" name="post_id" value="<c:out value="${post.post_id}"/> ">
				</form>
				<!-- 게시글 상세 폼-->
				
				



				<!--댓글 달 때 보여주는 창 -->
				<div class='row'>
					<div class="col-lg-12">

						<!-- /.panel -->
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-comments fa-fw">댓글</i>
								<div class="form-group">
									<label></label> 
									<input class="form-control" name='r_content' value='<c:out value=""/>'>
								</div>
								<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>댓글등록</button>
							</div>


							<!-- /.panel-heading -->
							<div class="panel-body">
								<ul class="chat">

								</ul>
								<!-- ./ end ul -->
							</div>
							<!-- /.panel .chat-panel -->
							<div class="panel-footer"></div>
						</div>
					</div>
					<!-- ./ end row -->
				</div>
				<!-- /댓글 -->
				
				
				

			</div>
			<!--  end panel-body -->

		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- /.row -->
</div>








<script type="text/javascript" src="/resources/js/post.js"></script>
<script type="text/javascript" src="/resources/js/reply.js"></script>
<script>
	$(document).ready( function() {
		
		
						//form value 가져오기 
						var post_id = $(".panel-body").find("input[name='post_id']").val();
						var dept_id = $(".panel-body").find("input[name='dept_id']").val();
						var post_name = $(".panel-body").find("input[name='post_name']").val();
						var post_content = $(".panel-body").find("textarea[name='post_content']").val();
						var post_regdate = $(".panel-body").find("input[name='post_regdate']").val();

					
						
						//csrf_token 가져오기
						var csrf_token = $("meta[name='_csrf']").attr("content");
						var csrf_header = $("meta[name='_csrf_header']").attr("content");
						

						//글 수정 버튼 클릭 시 수정폼으로 가기 
						$("#modifyBtn").on("click", function() {

							$("#operForm").submit();
						});//end modifyform

						//댓글 조회 자동 실행되기
						var replyUl = $(".chat") //새로 생긴 reply를 추가할 ul 태그
						replyService.getReplyList({post : post_id}, function(list) {
											var str = "";
											
											for (var i = 0; i < list.length; i++) {
												str += "<li class='left clearfix' data-reply_id='"+list[i].reply_id+"'>";
												str += "<div>" + list[i].r_writer + "</div>";
												str += "<div>" + list[i].r_content + "</div>";
												str += "<div>" + list[i].r_regdate + "</div>"; 
											}

											replyUl.html(str);

										});

						//댓글등록 버튼 누를 시 reply/new 호출하기
						var addReplyBtn = $("#addReplyBtn");

						addReplyBtn.on("click", function() {
							var formInsertContent = $(".panel").find(
									"input[name='r_content']");

							var post = {
								post_id : post_id,
								r_content : formInsertContent.val(),
								csrf_token:csrf_token,
					            csrf_header:csrf_header
							}

							replyService.addReply(post, function(result) {

								alert("등록되었습니다.");
								formInsertContent.val("");
							});

						});//end addReply

						//스크랩하기 클릭 시 스크랩에 문서함에 저장하기 
						var scrapBtn = $('#scrapBtn');

						scrapBtn.on("click", function() {

							var post = {
								post_id : post_id,
								csrf_token:csrf_token,
					            csrf_header:csrf_header
							}

							postService.scrapPost(post, function(result) {
								alert("스크랩되었습니다!");
							});
						});

						//list버튼 클릭 시 목록이동
						$('#listBtn').on("click", function(e) {

							$("#operForm").attr("action", "/board/list");
							$(".form-group").empty();
							$("#operForm").submit();

						});
						
						
						//이전글
						postService.selectPrev({post_id:post_id},function(result){
							$(".pre_index").append(
									"<tb><a href='"+ result.post_id+ "'>"+result.post_name+"</a></tb>")
							
						});
						
						
						//다음글
						postService.selectNext({post_id:post_id},function(result){
							$(".post_index").append(
									"<tb><a href='"+ result.post_id+ "'>"+result.post_name+"</a></tb>")
							
						});
						
						
						
						 //이전글 다음글 클릭 시 해당 get.jsp 이동하기
						  $(".table").on("click","a", function(e){
							  	
								e.preventDefault();
								var post_id = $(this).attr("href");
								console.log(post_id);
								
								 var post={
										post_id:post_id,
										csrf_token:csrf_token,
							    		csrf_header:csrf_header
								}

								//history 테이블에 넣어서 읽음 표시하기
								postService.insertHistory(post);
								
							 //get.jsp이동
								 $("#operForm").find("input[name='post_id']").val(post_id);
							 	$("#operForm").attr("action","/board/get");
								$("#operForm").submit(); 
							
						  });
						 
						 
						// 첨부파일 클릭 시 다운로드/삭제할 수 있도록 하는 url
							$('.boardFiles li').each(function(i, obj){
								var file = {
										uuid: $(obj).data('uuid'),
										file_name: $(obj).data('file_name'),
										file_src: $(obj).data('file_src'),
									};
								var filePath = encodeURIComponent(file.uuid + "_" + file.file_name);
								
								$(obj).children('a').attr("href", "/board/download?fileName=" + filePath);
								
							}); // end li each
								
							
								
						
	});//end script
</script>





<%@include file="/WEB-INF/views/includes/footer.jsp"%>
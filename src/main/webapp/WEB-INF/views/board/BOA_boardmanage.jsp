<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>

<%--<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>--%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="/resources/css/post.css">

<meta charset="UTF-8">
<meta name="_csrf" content="${_csrf.token}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<title></title>
</head>
<body>
<div class="container">
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">
		   게시판 관리
		</h1>


		<!--메인 게시글-->
				<table id="boardTest" class="table table-striped table-bordered table-hover" border="1">
					<thead>
						<tr>
							<th>게시판 번호</th>
							<th>게시판 이름</th>
							<th>게시판 설명</th>
							<th>게시판 공개여부</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody class="getPost">
							
					</tbody>
				</table>
			
		</div>
	</div>
	<!--end row-->
</div>
<!-- end body-->

</body>
<script type="text/javascript">
$(document).ready(function () {
	
	var csrf_token = $("meta[name='_csrf']").attr("content");
	var csrf_header = $("meta[name='_csrf_header']").attr("content");
	
	//게시판 목록불러오기
	 postService.allBoardList(function(result){
		 
		 //console.log(result[0].b_id);
		 var str ="";
		 for(var i=0;i<result.length;i++){
			 
						str +="<tr>";
						str +="<td><a href='"+result[i].b_id+ "'>"+result[i].b_id +"</a></td>";
						str +="<td>"+result[i].name +"</td>";
						str +="<td>"+result[i].content +"</td>";
						str +="<td>"+result[i].pri +"</td>";
						str +="<td><button id='deleteBtn' class='btn btn-primary'>삭제</button></td>";
						str +="</tr>";
				 
		 }
		
		 $(".getPost").append(str);
	          
	 });//end allBoardList
	
	 
	 //게시판 삭제하기
	 $(".getPost").on("click","button",function(){
		 
		var b_id= $(this).parent().parent().find("a").attr("href");
		//console.log(b_id);
		
		var post={
				b_id:b_id,
				csrf_token:csrf_token,
				csrf_header:csrf_header
		}
		
		postService.deleteBoard(post,function(result){
			alert("삭제되었습니다");
		})
		 
	 })
	
	
});
</script>
</html>
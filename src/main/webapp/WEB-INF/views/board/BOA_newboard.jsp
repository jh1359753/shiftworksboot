<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>--%>

<%@ include file="../includes/header.jsp"%>
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
		<h1 class="page-header">새 게시판 생성</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading"></div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				

				
					<div class="form-group">
							<label>게시판명</label>
							<input class="form-control" name='b_name' value="" />
						</div>

					<div class="form-group">
						<label>게시판 설명</label>
						<textarea class="form-control" rows="20" cols="150" name='b_content'>
						</textarea>
					</div>
						<label>게시판 공개여부</label> 
						<select class="form-select" aria-label="Default select example" name="b_private">
								<option selected>------</option>
								<option value="N">공개</option>
								<option value="Y">비공개</option>
						</select>

				<button id="registerBtn" type="button" class="btn btn-primary" value="">게시판 생성</button>
				<button id="manageBtn" type="button" class="btn btn-primary" value="">게시판 관리</button>

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
<script type="text/javascript">
$(document).ready(function () {
		
	var csrf_token = $("meta[name='_csrf']").attr("content");
	var csrf_header = $("meta[name='_csrf_header']").attr("content");
	
	//글 등록 버튼 클릭 시 post db에 저장하기 
	  $("#registerBtn").on("click",function(e){
		  	
		  var name = $(".form-group").find("input[name='b_name']").val();
		  var content =$(".form-group").find("textarea[name='b_content']").val();
		  var pri = $(".form-select").val();
		  
		 
		  var post ={
				  name:name,
			      content:content,
			      pri:pri,
				  csrf_token:csrf_token,
				  csrf_header:csrf_header
		  }
		  
		   postService.insertNewBoard(post,function(result){
			  alert("새로운 게시판이 생성되었습니다.")
			  location.href = "/board/list";
		  }) 
	     
	      
	    });//end register
	    
	    
	    $("#manageBtn").on("click",function(){
	    	location.href = "/board/boardmanage";
	    })
	    
	
});
</script>
<%--
<%@include file="/WEB-INF/views/includes/footer.jsp"%>--%>

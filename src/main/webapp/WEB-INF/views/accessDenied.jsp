<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/includes/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비공개 게시글</title>
</head>
<body>

	<div class="container denied">
	<h3>접근 권한이 없습니다.</h3>
	<button type="button" class="btn btn-info">뒤로가기</button>
	</div>
	
<script type="text/javascript">
	$('button').on("click", function(){
		history.back();
	});
</script>
</body>
</html>
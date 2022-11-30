<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<%@taglib uri="http://www.springframework.org/security/tags"
   prefix="sec"%>--%>
<%@include file="/WEB-INF/views/includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Detail Reservation</title>
</head>
<body>

	<div class="container">
	<div class="col-12"><h2>예약 상세보기</h2></div>
 	
  	<div class="col-8">
	<table>
		
<%--			<tr>--%>
<%--				<td><label>예약번호&ensp;<br></label></td>--%>
<%--				<td class="form-control" style="width: 300px;"><c:out value="${event.bookId }"></c:out></td>--%>
<%--			</tr>--%>
			<tr>
				<td><label>예약 공간&ensp;<br></label></td>
				<td class="form-control" style="width: 300px;">
					<c:if test="${event.meetingRoom eq 'MTR101' }">
						<c:out value="회의실101호"></c:out>
					</c:if>
						<c:if test="${event.meetingRoom eq 'MTR202' }">
							<c:out value="회의실202호"></c:out>
						</c:if>
						<c:if test="${event.meetingRoom eq 'CFR305' }">
							<c:out value="컨퍼런스룸303호"></c:out>
						</c:if>
			</tr>
			<tr>
				<td><label>예약명&ensp;</label></td>
				<td class="form-control" style="width: 500px;"><c:out value="${event.bookTitle }"></c:out></td>
			</tr>
<%--			<tr>--%>
<%--				<td><label>예약자&ensp;</label></td>--%>
<%--				<td class="form-control" style="width: 300px;"><c:out value="${event.emp_id }"></c:out></td>--%>
<%--			</tr>--%>
			<tr>
				<td><label>예약 일정&ensp;</label></td>
				<td class="form-control" style="width: 300px;"><c:set var="bookDate" value="${event.bookDate }"></c:set>
				<c:out value="${fn:substring(book_date,0,13) }">&ensp;</c:out>
				<c:out value="${event.bookBegin }시~${event.bookBegin+2 }시"></c:out></td>
			</tr>
			<tr>
				<td><label>예약 내용&ensp;</label></td>
				<td class="form-control" style="width: 500px; height: 150px;"><c:out value="${event.bookContent }"></c:out></td>
			</tr>

	</table>
	<br><br>
	
	<table>
		<tr>
			<th><a href="/booking/list"><button id="listBtn" type="button" class="btn btn-primary">목록보기</button></a></th>
			<th><a href="#"><button id="removeBtn" type="button" class="btn btn-primary">예약 취소</button></a></th>
		</tr>
	</table>
	</div> <!-- end col-8 -->
	</div> <!-- end container -->
	
	
	
</body>
	

</body>
<%@include file="/WEB-INF/views/includes/footer.jsp" %>
</html>
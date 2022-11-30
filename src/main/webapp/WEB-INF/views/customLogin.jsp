<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/login.css">
<script
  src="https://code.jquery.com/jquery-3.4.1.js"
  integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
  crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>shiftworks login page</title>
</head>
<body>
<div class="wrapper">
   <%-- <h3><c:out value="${ error }"/></h3>
   <h3><c:out value="${ logout }"/></h3> --%>
   <div class="wrap">
      <form id="login_form" method="post">
         <div class="logo_wrap">
            <span>Shift Works</span>
         </div>
         <div class="login_wrap"> 
            <div class="id_wrap">
                  <div class="id_input_box">
                  <input class="id_input" name="empId" placeholder="사번"
                     onfocus="this.placeholder=''" onblur="this.placeholder='사번'">
               </div>
            </div>
            <div class="pw_wrap">
               <div class="pw_input_box">
                  <input class="pw_iput" type="password" name ="password"
                     placeholder="비밀번호" 
                     onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호'">
               </div>
            </div>
                     <input type="hidden" name="${_csrf.parameterName}"
                        value="${_csrf.token}" />
            </form>
            <c:if test = "${result == 0 }">
               <div class = "login_warn">사용자 ID 또는 비밀번호를 잘못 입력하셨습니다.</div>
            </c:if>
            
            <div class="login_button_wrap">
               <input type="submit" class="login_button" value="Login">
            </div>         
         </div>
         <input type="hidden" name="${_csrf.parameterName}"
                        value="${_csrf.token}" />
      
      
   </div>





	<script>
	/* 로그인 버튼 클릭 메서드 */
	$(".login_button").click(function(){
		
		//alert("로그인 버튼 작동");
		
		/* 로그인 메서드 서버 요청 */
		 $("#login_form").attr("action", "/users/login");
		$("#login_form").submit();
		
	});
	
	$("#login_form").keypress(function (e) {
		if(e.keyCode === 13){
			Login();
		}
	});
</script>
</body>
</html>


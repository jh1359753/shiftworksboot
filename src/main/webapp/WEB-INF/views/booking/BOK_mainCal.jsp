<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--<%@taglib uri="http://www.springframework.org/security/tags"--%>
<%--   prefix="sec"%>--%>
<%@include file="../includes/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">  
<!-- fullcalendar CDN -->  
<link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />  
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>  
<!-- fullcalendar 언어 CDN -->
<script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<!--javaScript 스크립트-->
<script type="text/javascript" src="/resources/js/booking.js"></script>
<script type="text/javascript">




document.addEventListener('DOMContentLoaded', function () {
    $(function () {
        var request = $.ajax({
            url: "/booking/calendar",
            method: "GET"
        });

        request.done(function (data) {

            var calendarEl = document.getElementById('calendar');

            var calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek',
                    center: 'title',
                    right: 'today prev,next'
                },
                eventTimeFormat: { // like '14:30'
                    hour: '2-digit',
                    minute: '2-digit',
                    hour12: false
                  }, //시간 표시 옵션
                eventBackgroundColor : "#1C3359",
                selectable: true,
                navLinks: true,
                editable: true,
                droppable: true, // things to be dropped onto the calendar
                events:data
            });

            calendar.render();
        });

        request.fail(function( jqXHR, textStatus ) {
            alert( "Request failed: " + textStatus );
        });
    });

});


</script>


<title>Checkout with Booking Calendar</title>
</head>
<body>
	
	<div class="container">
		<div id='calendar'></div>
	</div>


</body>
<%@include file="../includes/footer.jsp" %>
</html>
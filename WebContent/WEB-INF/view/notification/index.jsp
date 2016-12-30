<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}" />
<t:layout title="{{ 'manage_not' | translate }}">
	<form name="notificationForm" class="col-md-12" style="background-color: white; padding-bottom: 20px;" ng-controller="NotificationController" method="POST">
		<div class="col-md-4 notification">
			<input type="hidden" name="incomming" value="-1"/>
			<h3>{{ 'incomming_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsIncomming}" var="pattern" varStatus="itt">
					<a class="list-group-item" data-id='${pattern.id}'>
						<span class="badge" ng-click="test(${pattern.id})"><i class="fa fa-play fa-fw"></i></span>
						<label>${itt.index + 1}: ${pattern.pattern}</label>
				  	</a>
				</c:forEach>
			</div>
		</div>
		
		<div class="col-md-4 notification">
			<input type="hidden" name="vibration" value="-1"/>
			<h3>{{ 'vibration_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsVibration}" var="pattern" varStatus="itt">
					<a class="list-group-item" data-id='${pattern.id}'>
						<span class="badge" ng-click="test(${pattern.id})"><i class="fa fa-play fa-fw"></i></span>
						<label>${itt.index + 1}: ${pattern.pattern}</label>
				  	</a>
				</c:forEach>
			</div>
		</div>
		
		<div class="col-md-4 notification">
			<input type="hidden" name="vibration_cont" value="-1"/>
			<h3>{{ 'vibration_cont_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsVibrationCont}" var="pattern" varStatus="itt">
					<a class="list-group-item" data-id='${pattern.id}'>
						<span class="badge" ng-click="test(${pattern.id})"><i class="fa fa-play fa-fw"></i></span>
						<label>${itt.index + 1}: ${pattern.pattern}</label>
				  	</a>
				</c:forEach>
			</div>
		</div>
		
		<button class="btn btn-success btn-block" type="submit">
			<i class="fa fa-floppy-o fa-fw"></i> {{ 'save_button' | translate }}
		</button>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
	<script>
		$("button").click(function() {
			window.notificationForm.submit();
		});
		$(".list-group-item").click(function(e) {
			// The badge click will be handled in the NotificationController 
			if(!$(e.targetd).is("i.fa, span.badge")) {
				var self = $(this);
				var not = self.closest(".notification");
				not.find(".list-group-item").removeClass("active");
				self.addClass("active");
				not.find("input[type=hidden]").val(self.data("id"))
			}
		});
	</script>
</t:layout>
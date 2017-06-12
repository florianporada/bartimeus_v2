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
			<input type="hidden" name="incoming" value="${userIncoming.id}"/>
			<h3>{{ 'incoming_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsIncoming}" var="pattern" varStatus="itt">
					<c:set var="addClass" value="" />
					<c:if test="${userIncoming.id == pattern.id}">
						<c:set var="addClass" value="active"/>
					</c:if>
					<a class="list-group-item ${addClass}" data-id='${pattern.id}'>
						<span class="badge" ng-click="test(${pattern.id})"><i class="fa fa-play fa-fw"></i></span>
						<label>${itt.index + 1}: ${pattern.pattern}</label>
				  	</a>
				</c:forEach>
			</div>
		</div>
		
		<div class="col-md-4 notification">
			<input type="hidden" name="doorbell_known" value="${userDoorbellKnown.id}"/>
			<h3>{{ 'doorbell_known_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsDoorbellKnown}" var="pattern" varStatus="itt">
					<c:set var="addClass" value="" />
					<c:if test="${userDoorbellKnown.id == pattern.id}">
						<c:set var="addClass" value="active"/>
					</c:if>
					<a class="list-group-item ${addClass}" data-id='${pattern.id}'>
						<span class="badge" ng-click="test(${pattern.id})"><i class="fa fa-play fa-fw"></i></span>
						<label>${itt.index + 1}: ${pattern.pattern}</label>
				  	</a>
				</c:forEach>
			</div>
		</div>
		
		<div class="col-md-4 notification">
			<input type="hidden" name="doorbell_unknown" value="${userDoorbellUnknown.id}"/>
			<h3>{{ 'doorbell_unknown_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsDoorbellUnknown}" var="pattern" varStatus="itt">
					<c:set var="addClass" value="" />
					<c:if test="${userDoorbellUnknown.id == pattern.id}">
						<c:set var="addClass" value="active"/>
					</c:if>
					<a class="list-group-item ${addClass}" data-id='${pattern.id}'>
						<span class="badge" ng-click="test(${pattern.id})"><i class="fa fa-play fa-fw"></i></span>
						<label>${itt.index + 1}: ${pattern.pattern}</label>
				  	</a>
				</c:forEach>
			</div>
		</div>

		<div class="col-md-4 notification">
			<input type="hidden" name="motion" value="${userMotion.id}"/>
			<h3>{{ 'motion_notification' | translate }}</h3>
			<div class="list-group">
				<c:forEach items="${patternsMotion}" var="pattern" varStatus="itt">
					<c:set var="addClass" value="" />
					<c:if test="${userMotion.id == pattern.id}">
						<c:set var="addClass" value="active"/>
					</c:if>
					<a class="list-group-item ${addClass}" data-id='${pattern.id}'>
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
			if(!$(e.target).is("i.fa, span.badge")) {
				var self = $(this);
				var not = self.closest(".notification");
				not.find(".list-group-item").removeClass("active");
				self.addClass("active");
				not.find("input[type=hidden]").val(self.data("id"))
			}
		});
	</script>
</t:layout>
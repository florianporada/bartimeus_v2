<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}" />
<t:layout title="{{ 'add_sensor' | translate }}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">{{ 'manage_sensor' | translate }}</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
					<c:if test="${param.error}">
						{{ 'login_error' | translate }}
					</c:if>
					<form:form method="post" modelAttribute="sensor" cssClass="input-form">
						<form:hidden path="id"/>
						<form:hidden path="user.id"/>
						
						<div class="form-group">
							<label for="qr">QR</label>
							<div class="form-description">The sensor has a QR code on the back. Use the camera on your mobile to scan this number</div>
							<qrcode data="${sensor.hash}" size="200"></qrcode>
							<button type="button" class="btn btn-success">Scan</button>
						</div>
						
						<div class="form-group">
							<label for="name">{{ 'name' | translate }}</label>
							<div class="form-description">{{ 'name_description' | translate }}</div>
							<form:input path="name" type="text" id="name" placeholder="{{ 'name' | translate }}" cssClass="form-control"/>
							<form:errors path="name" />
						</div>
						<div class="form-group">
							<label for="hash">{{ 'hash' | translate }}</label>
							<div class="form-description">{{ 'hash_description' | translate }}</div>
							<form:input path="hash" type="text" id="hash" placeholder="{{ 'hash' | translate }}" cssClass="form-control"/>
							<form:errors path="hash" />
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-success btn-block">
								<i class="fa fa-plus fa-fw"></i> {{ 'edit_sensor' | translate }}
							</button>
						</div>
						
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</t:layout>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}" />
<t:layout title="Bartiméus">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">{{ 'dashboard' | translate }}</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-4 flex-center">
					<a class="icon-button" href="application/">
						<i class="fa fa-mobile"></i>
						<span class="label">{{ 'manage_app' | translate }}</span>
					</a>
				</div>
				<div class="col-md-4 flex-center">
					<a class="icon-button" href="sensor/">
						<i class="fa fa-bell"></i>
						<span class="label">{{ 'manage_sensor' | translate }}</span>
					</a>
				</div>
				<div class="col-md-4 flex-center">
					<a class="icon-button" href="notification/">
						<i class="fa fa-rss"></i>
						<span class="label">{{ 'manage_not' | translate }}</span>
					</a>
				</div>
			</div>
		</div>
	</div>
</t:layout>
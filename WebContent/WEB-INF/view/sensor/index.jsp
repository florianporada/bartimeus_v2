<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}" />
<t:layout title="{{ 'manage_sensor' | translate }}">
	<div class="panel panel-default">
		<div class="panel-heading">
			<h3 class="panel-title">{{ 'manage_sensor' | translate }}</h3>
		</div>
		<div class="panel-body">
			<div class="row">
				<div class="col-md-12">
				</div>
			</div>
		</div>
	</div>
</t:layout>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout title="{{ 'about_title' | translate }}">
	<div class="col-md-12" style="background-color: white;">
		<div class="col-md-4  article">
			<div class="title">
				<img src='<c:url value="/assets/img/bartimeus_logo.jpg"/>' />
				<h1>{{ 'about_description_title' | translate }}</h1>
			</div>
			<div class="description">
				{{ 'about_description' | translate }}
			</div>
		</div>
		
		<div class="col-md-4  article">
			<div class="title">
				<img src='<c:url value="/assets/img/hva_logo.png"/>' />
				<h1>{{ 'project_description_title' | translate }}</h1>
			</div>
			<div class="description">
				{{ 'project_description' | translate }}
			</div>
		</div>
		
		<div class="col-md-4  article">
			<div class="title">
				<img src='<c:url value="/assets/img/willie_logo.png"/>' />
				<h1>{{ 'app_description_title' | translate }}</h1>
			</div>
			<div class="description">
				{{ 'app_description' | translate }}
			</div>
		</div>
	</div>
</t:layout>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout title="{{ 'logout_title' | translate }}">
	<div class="col-md-10 col-xs-12 col-centered" style="background-color: white;">
		<p class="logout">{{ 'logged_out' | translate }} <a href="../">{{ 'click_login' | translate }}</a></p>
	</div>
</t:layout>
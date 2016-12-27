<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<t:layout title="Willie: Logout">
	<div class="col-md-10 col-xs-12 col-centered" style="background-color: rgb(41, 92, 135); color: #FAFAFA">
		<p class="logout">You're logged out. <a href="../login">Click here to login</a></p>
	</div>
</t:layout>
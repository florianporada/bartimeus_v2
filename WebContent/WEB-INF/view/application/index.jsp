<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<t:layout title="{{ 'manage_app' | translate }}">
	<div class="col-md-12" style="background-color: white; padding: 10px;">
		<div class="col-md-6 col-sm-12 flex-center">
			<qrcode data="string" size="300"></qrcode>
		</div>
	</div>
</t:layout>
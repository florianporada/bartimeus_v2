<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
	<head>
		<title>Generate a new Sensor</title>
        <script src="<c:url value="/assets/lib/angular/angular.min.js" />"></script>
        <script src="<c:url value="/assets/lib/angular/angular-translate.min.js" />"></script>
        <script src="<c:url value="/assets/lib/qrcode/qrcode.js" />"></script>
        <script src="<c:url value="/assets/lib/qrcode/angular.qrcode.js" />"></script>
        <script src="<c:url value="/assets/js/Application.js" />"></script>
	</head>
	<body ng-app="app">
		<h1>New sensor</h1>
		<p><strong>Sensor ID: </strong> ${sensor}</p>
		<p><strong>Hash: </strong> ${hash}</p>
		<qrcode data="${hash}" size="200"></qrcode>
	</body>
</html>
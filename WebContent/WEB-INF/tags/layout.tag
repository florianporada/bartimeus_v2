<%@tag description="Default layout tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${pageContext.request.contextPath}" />

<jsp:directive.attribute name="title" type="java.lang.String" required="false" rtexprvalue="true" description="Title of the page" />
<jsp:directive.attribute name="breadcrumbs" required="false" rtexprvalue="true" />

<!DOCTYPE html>
<html lang="en" ng-app="app">
	<head>
		<title>${title}</title>
		
		<!-- jQuery -->
		<script src="<c:url value="/assets/lib/jquery/jquery.min.js" />"></script>
        
        <!-- Angular JS -->
        <script src="<c:url value="/assets/lib/angular/angular.min.js" />"></script>

        <!-- Translation -->
        <script src="<c:url value="/assets/lib/angular/angular-translate.min.js" />"></script>
        
        <!-- QR Code -->
        <script src="<c:url value="/assets/lib/qrcode/qrcode.js" />"></script>
        <script src="<c:url value="/assets/lib/qrcode/angular.qrcode.js" />"></script>
        
        <script src="<c:url value="/assets/js/Application.js" />"></script>
        <script src="<c:url value="/assets/js/config/DutchConfig.js" />"></script>
        <script src="<c:url value="/assets/js/config/EnglishConfig.js" />"></script>
        <script src="<c:url value="/assets/js/controller/BaseController.js" />"></script>
        <script src="<c:url value="/assets/js/controller/NotificationController.js" />"></script>
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/bootstrap/css/bootstrap.min.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/lib/fontawesome/css/font-awesome.min.css" />">
		
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/style.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/layout.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/header.css" />">
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/css/footer.css" />">
	</head>
	<body ng-controller="BaseController">
		<div class="overlay" ng-hide="true">
            <div class="loader">
                <i class="fa fa-cog fa-spin fa-3x fa-fw"></i>
                <span class="sr-only">Loading...</span>
            </div>
        </div>
		
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
				     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
				     </button>
				     <c:choose>
				     	<c:when test="${title != 'Bartiméus'}">
				     		<a class="navbar-brand" href="<c:url value="/" />">Bartiméus: ${title}</a>
				     	</c:when>
				     	<c:otherwise>
							<a class="navbar-brand" href="<c:url value="/" />">Bartiméus</a>		     	
				     	</c:otherwise>
				     </c:choose>
		
			    </div>
			    
			    <div class="nav navbar-nav">
			    	<ol class="breadcrumb">
			    		<c:forEach items="${breadcrumbs}" var="crumb" varStatus="itt">
			    			<c:choose>
			    				<c:when test="${itt.last}">
			    					<li class="breadcrumb-item active">${crumb.name}</li>
			    				</c:when>
			    				<c:otherwise>
			    					<li class="breadcrumb-item"><a href="${crumb.url}">${crumb.name}</a></li>
			    				</c:otherwise>
			    			</c:choose>
			    		</c:forEach>
					</ol>
			    </div>
			      
			    <ul class="nav navbar-nav navbar-right">
			    	<li>
						<ul class="i18n">
							<li ng-if="LANG === 'en_US'">
								<a ng-click="lang('nl_NL')">
									<img src='<c:url value="/assets/img/nl.png" />'>
								</a>
							</li>
							<li ng-if="LANG === 'nl_NL'">
								<a ng-click="lang('en_US')">
									<img src='<c:url value="/assets/img/uk.png" />'>
								</a>
							</li>
						</ul>
			    	</li>
			        <li>
			        	<c:if test="${isLoggedIn}">
			        		<a href="${base}/user/logout">Hey ${user}, <strong>{{'click_logout' | translate}}</strong></a>
			        	</c:if>
			        </li>
			    </ul>	
			</div>
		</nav>
		
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<jsp:doBody/>
				</div>
			</div>
		</div>

		<footer class="footer">
			<div class="fluid-container">
				<ul class="links">
					<li><a href="https://theassistant.io">TheAssistant.io</a></li>
					<li><a href="<c:url value="/info/about" />">{{ 'about_title' | translate }}</a></li>
					<li><a href="<c:url value="/info/privacy" />">{{ 'privacy_title' | translate }}</a></li>
					<li><a href="<c:url value="/info/terms" />">{{ 'terms_title' | translate }}</a></li>
				</ul>
				<p class="text">&copy; 2016, Team Dream Team</p>
			</div>
		</footer>
	</body>
</html>
<%@tag description="Default layout tag" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="base" value="${pageContext.request.contextPath}" />

<jsp:directive.attribute name="title" type="java.lang.String" required="true" rtexprvalue="true" description="Title of the page" />
<jsp:directive.attribute name="breadcrumbs" required="false" rtexprvalue="true" />

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${title}</title>
		
		<link rel="stylesheet" type="text/css" href="${base}/assets/lib/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${base}/assets/lib/fontawesome/css/font-awesome.min.css">
		
		<link rel="stylesheet" type="text/css" href="${base}/assets/css/style.css">
		<link rel="stylesheet" type="text/css" href="${base}/assets/css/layout.css">
		<link rel="stylesheet" type="text/css" href="${base}/assets/css/header.css">
	</head>
	<body>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
				     <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
				     </button>
			      	<a class="navbar-brand" href="${base}">${title}</a>
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
			            <a href="#">
			                <strong>Sign in/up | Logout</strong>
			            </a>
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
	</body>
</html>
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
					<ul class="striped-list">
						<c:forEach items="${sensors}" var="sensor">
							<li>
								${sensor.name}
								<div class="toolbox">
									<a href="edit/${sensor.id}">
										<i class="fa fa-pencil fa-fw"></i>
									</a>
									<a href="delete/${sensor.id}">
										<i class="fa fa-trash fa-fw"></i>
									</a>
								</div>
							</li>
						</c:forEach>
					</ul>
				
					<a href="add/" class="btn btn-success btn-block">
						<i class="fa fa-plus fa-fw"></i> {{ 'add_sensor' | translate }}
					</a>
				</div>
			</div>
		</div>
	</div>
</t:layout>
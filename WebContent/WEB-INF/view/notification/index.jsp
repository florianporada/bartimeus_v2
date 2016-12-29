<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<c:set var="url" value="${requestScope['javax.servlet.forward.request_uri']}" />
<t:layout title="Bartiméus">
	<div class="col-md-12" style="background-color: white; padding-bottom: 20px;">
		<div class="col-md-4 notification">
			<h3>{{ 'incomming_notification' | translate }}</h3>
			<div class="list-group">
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="1"> 1: H - H - H</label>
			  	</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 2: Z - Z - Z</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 3: Z - H - Z</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 4: Z - Z - H</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 5: H - H - Z</label>
				</a>
			</div>
		</div>
		
		<div class="col-md-4 notification">
			<h3>{{ 'vibration_notification' | translate }}</h3>
			<div class="list-group">
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="1"> 1: H - H - H</label>
			  	</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 2: Z - Z - Z</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 3: Z - H - Z</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 4: Z - Z - H</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 5: H - H - Z</label>
				</a>
			</div>
		</div>
		
		<div class="col-md-4 notification">
			<h3>{{ 'vibration_cont_notification' | translate }}</h3>
			<div class="list-group">
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="1"> 1: H - H - H</label>
			  	</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 2: Z - Z - Z</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 3: Z - H - Z</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 4: Z - Z - H</label>
				</a>
				<a class="list-group-item">
					<span class="badge"><i class="fa fa-play fa-fw"></i></span>
					<label><input type="radio" name="incomming" value="2"> 5: H - H - Z</label>
				</a>
			</div>
		</div>
		
		<button class="btn btn-success btn-block">
			<i class="fa fa-floppy-o fa-fw"></i> {{ 'save_button' | translate }}
		</button>
	</div>
	<script>
		// Fix for the radio buttons not working in a a.list-group-item
		$(".list-group-item").click(function() {
			$(this).find("input").prop("checked", true)
		});
	</script>
</t:layout>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ie=edge">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

	<link rel="stylesheet" type="text/css" href="../../fontawesome-free-5.4.2-web/css/all.css">
	<link rel="stylesheet" type="text/css" href="../../css/efe.css">
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="../../js/efe.js"></script>



	<title>Mon CRM</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/menu.jspf" %>		

	<div class="container">		<!-- pour une maquette responsive avec une largeur fixe. -->
								<!-- http://getbootstrap.com/css/#grid-options -->
		<h1 class=" text-center bg-primary">Affaires <span class="badge alert-info">${listeA.size() }</span></h1>
		
 			<div class="col-xs-2 paddingLeftZero">
					<a href="ajout" class="btn btn-default bleu" ><i class="fas fa-plus-square"></i></a>
			</div>
			<div class="col-xs-10 paddingRightZero" >
				<form method="get" action="recherche" >
			    		<input type="hidden" id="tri" name="tri">
			 		
			 		<div class="input-group margeBas">
			  			<span class="input-group-addon bleu">
			  				<span class="far fa-address-card"></span>
			  			</span>
			  			<input type="search" class="form-control" placeholder="Filtre sur affaire..." id="txt" name="txt" value="${txt }" autofocus>
			  			<span class="input-group-btn">
			        			<button type = "submit" class="btn btn-default bleu" type="button"><i class="fas fa-search"></i> </button>
			      		</span>
					</div>
				</form>
			</div>
		
		<p class="text-right"><a href="#affOptions" data-toggle="collapse">Options</a></p>

		<div id="affOptions" class="collapse">
			<form:form action="validOptions" method ="post" commandName="affOptions">
				
				<label for="annule1"  class="labelOption "><form:checkbox path="annule"/> Annulée</label>
				
				<label for="effectue1" class="labelOption"><form:checkbox path="effectue"/> Effectuée</label>
				
				<label for="enAttente1" class="labelOption"><form:checkbox path="enAttente"/> En Attente</label>
				
				<label for="confirme1" class="labelOption"><form:checkbox path="confirme"/> Confirmée</label>
				
				<label for="enCours1" class="labelOption"><form:checkbox path="enCours"/> En Cours</label>
				<button class="btn btn-primary btn-xs">Valider</button>
			</form:form>
		</div>

		<jsp:useBean id="now" class="java.util.Date"/>
		
		
			<div class="hidden-xs">
				
				<table class="table table-hover table-striped ">
					<tr>
						<th  class="w100px"> 
							<a id="dA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a>
							<h5 class="inline text-info">Début</h5> 
							<a id="dD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
						<th class="">&nbsp;</th>
						<th class="">
							<a id="aA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a> 
							<h5 class="inline text-info">&nbsp;Affaire&nbsp;</h5> 
							<a id="aD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
						<th class="">
							<a id="vA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a> 
							<h5 class="inline text-info">&nbsp;Lieu&nbsp;</h5> 
							<a id="vD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
						<th class="">
							<a id="cA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a> 
							<h5 class="inline text-info">&nbsp;Contact&nbsp;</h5> 
							<a id="cD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
	     			</tr>
					<c:forEach items="${listeA}" var="a" varStatus="bStatus">

						<c:if test="${(a.annule && affOptions.annule) ||
									  (!a.annule && a.fin lt now && affOptions.effectue) ||
									  (!a.annule && a.fin ge now && a.debut le now && affOptions.enCours) ||
									  (!a.annule && a.confirme && affOptions.confirme && !(a.fin lt now)) ||
									  (!a.annule && !a.confirme && affOptions.enAttente) 
									}">

									<tr>
										<fmt:formatDate value="${a.debut }" var="fDate" pattern="dd/MM/yyyy" />
										<td>${fDate }</td>
			
										<c:choose>
											<c:when test="${a.annule}"><td><i class="fas fa-square alert-danger"></i></td></c:when> 
											<c:when test="${a.fin lt now}"><td><i class="fas fa-square "></i></td></c:when> 
											<c:when test="${a.fin ge now && a.debut le now}"><td><i class="fas fa-square alert-info"></i></td></c:when>
											<c:when test="${a.confirme }"><td><i class="fas fa-square alert-success"></i></td></c:when> 
											<c:otherwise><td><i class="fas fa-square orange"></i></td></c:otherwise> 
										</c:choose>
										
										
										<td>
											<a href="voir?index=${a.id}"><strong>${a.nom}</strong></a><br>
											${a.nbJours } jour<c:if test="${a.nbJours > 1}">s</c:if>
										</td>
										<td>${a.lieu }</td>
										<td><a href="../contacts/voir?index=${a.contact.id}">${a.contact.prenom} ${a.contact.nom}</a><br>
											<a href="../societes/voir?index=${a.contact.societe.id}">(${a.contact.societe.nom})</a>
										</td>
									</tr>
						</c:if>
					</c:forEach>
				</table>			
			</div>
			
			<div class="visible-xs">
				
				<table class="table table-hover table-striped ">
					<tr>
						<th class="w100px"> 
							<a id="dA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a>
							<h4 class="inline text-info">&nbsp;Début&nbsp;</h4> 
							<a id="dD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
						<th class="">
							<a id="aA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a> 
							<h4 class="inline text-info">&nbsp;Affaire&nbsp;</h4> 
							<a id="aD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
	     			</tr>
					<c:forEach items="${listeA}" var="a" varStatus="bStatus">
						<c:if test="${(a.annule && affOptions.annule) ||
									  (!a.annule && a.fin lt now && affOptions.effectue) ||
									  (!a.annule && a.fin ge now && a.debut le now && affOptions.enCours) ||
									  (!a.annule && a.confirme && affOptions.confirme && !(a.fin lt now)) ||
									  (!a.annule && !a.confirme && affOptions.enAttente) 
									}">
	
							<tr>
								<td>${a.debut } </td>
								<td>
								<c:choose>
									<c:when test="${a.annule}"><i class="fas fa-square alert-danger"></i></c:when> 
									<c:when test="${a.fin lt now}"><i class="fas fa-square "></i></c:when> 
									<c:when test="${a.fin ge now && a.debut le now}"><i class="fas fa-square alert-info"></i><</c:when>
									<c:when test="${a.confirme }"><i class="fas fa-square alert-success"></i></c:when> 
									<c:otherwise><i class="fas fa-square orange"></i></c:otherwise> 
								</c:choose>
									<a href="voir?index=${a.id}"><strong>${a.nom}</strong></a><br>
									${a.nbJours } jours<br>
									${a.lieu }<br>
									<a href="../contacts/voir?index=${a.contact.id}">${a.contact.prenom} ${a.contact.nom}</a><br>
									<a href="../societes/voir?index=${a.contact.societe.id}">${a.contact.societe.nom} ${a.contact.societe.adresse.ville}</a>
								</td>
								
							</tr>
	
						
						</c:if>
						
					</c:forEach>
				</table>			
			</div>
	
			

	
	</div>

	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
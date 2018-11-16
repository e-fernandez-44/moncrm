<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	

	<div class="container">		

	<c:set var="titre">${soc.id == 0 ? "Nouveau" : "Edition"}</c:set>


		<h1 class=" text-center bg-primary">${titre }</h1>


		<div class="col-sm-8 col-sm-offset-2">			
			
			<form:form action="ajout" method="POST" cssClass="form-horizontal" modelAttribute="soc">
			
				<form:hidden path="id"/>
				<form:hidden path="adresse.id"/>
				<c:set var="nomErrors"><form:errors path="nom"/></c:set>
				<c:choose>
					<c:when test="${not empty nomErrors}">				
						<div class="form-group has-error"">	
					</c:when>		
						<c:otherwise>
							<div class="form-group">
						</c:otherwise>
				</c:choose>
					<form:label path="nom" cssClass="control-label text-primary">Nom : </form:label> 
					<span class="text-danger"><form:errors path="nom" /></span>
					<form:input path="nom"  placeholder="Nom ..."  cssClass="form-control" /> 
				</div>

				<div class="form-group">			
					<form:label path="adresse.adresse" cssClass="control-label text-primary">Adresse :</form:label>
					<form:input path="adresse.adresse"  placeholder="Adresse ..."  cssClass="form-control" /> 
				</div>

				<div class="form-group">			
					<form:label path="adresse.adresse2" cssClass="control-label text-primary">Adresse complémentaire :</form:label>
					<form:input path="adresse.adresse2"  placeholder="Adresse complémentaire ..."  cssClass="form-control" /> 				
				</div>
				<div class="form-group">			
					<form:label path="adresse.codePostal" cssClass="control-label text-primary">Code Postal :</form:label>
					<form:input path="adresse.codePostal"  placeholder="Code Postal ..."  cssClass="form-control" /> 
				</div>
				
				<c:set var="villeErrors"><form:errors path="adresse.ville"/></c:set>
				<c:choose>
					<c:when test="${not empty villeErrors}">				
						<div class="form-group has-error"">	
					</c:when>		
						<c:otherwise>
							<div class="form-group">
						</c:otherwise>
				</c:choose>		
					<form:label path="adresse.ville" cssClass="control-label text-primary">Ville :</form:label>
					<span class="text-danger"><form:errors path="adresse.ville" /></span>
					<form:input path="adresse.ville"  placeholder="Ville ..."  cssClass="form-control" /> 
					
				</div>
				<div class="form-group" >
					<div class="pull-left">
						<button type="submit" class="btn btn-primary">Envoyer</button>			
					</div>
					<div class="pull-right">
						<a href="liste" class="btn btn-warning">Annuler</a>			
					</div>
				</div>	
			</form:form>
			
			
			
			
		</div>

		

	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
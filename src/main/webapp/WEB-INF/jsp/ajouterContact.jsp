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

	<c:set var="titre">${contact.id == 0 ? "Nouveau" : "Edition"}</c:set>


		<h1 class=" text-center bg-primary">${titre }</h1>


		<div class="col-sm-8 col-sm-offset-2">			
			
			<form:form action="ajout" method="POST" cssClass="form-horizontal" modelAttribute="contact">
			
				<form:hidden path="id"/>
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
					<form:label path="prenom" cssClass="control-label text-primary">Prénom :</form:label>
					<form:input path="prenom"  placeholder="Prénom ..."  cssClass="form-control" /> 
				</div>

				<div class="form-group">			
					<form:label path="fonction" cssClass="control-label text-primary">Fonction :</form:label>
					<form:input path="fonction"  placeholder="Fonction ..."  cssClass="form-control" /> 				
				</div>
				<div class="form-group">			
					<form:label path="telFixe" cssClass="control-label text-primary">Téléphone Fixe :</form:label>
					<form:input type="tel" path="telFixe"  placeholder="Téléphone fixe ..."  cssClass="form-control" /> 
				</div>
				<div class="form-group">			
					<form:label path="telMobile" cssClass="control-label text-primary">Téléphone Mobile :</form:label>
					<form:input type="tel" path="telMobile"  placeholder="Téléphone mobile ..."  cssClass="form-control" /> 
				</div>
				<div class="form-group">			
					<form:label path="email" cssClass="control-label text-primary">Email :</form:label>
					<form:input type="email" path="email"  placeholder="Email ..."  cssClass="form-control" /> 
				</div>
				
				<div class="form-group">			
					<form:label path="societe.id" cssClass="control-label text-primary">Société :</form:label>
					<form:select path="societe.id" cssClass="form-control">
<%-- 							<form:options items="${listeSocietes}" itemValue="id" itemLabel="nom"/> --%>
					    <c:forEach var="soc" items="${listeSocietes}">
					        <form:option value="${soc.id}"><c:out value="${soc.nom} - ${soc.adresse.ville}"/></form:option>
					    </c:forEach>
   					</form:select>					
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<c:set var="titre">${f.id == 0 ? "Nouveau" : "Edition"}</c:set>


		<h1 class=" text-center bg-primary">${titre }</h1>


		<div class="col-sm-8 col-sm-offset-2">			
			
			<form:form action="ajout" method="POST" cssClass="form-horizontal" modelAttribute="f">
			
				<form:hidden path="id"/>
		
		
				<div class="form-group">			
					<form:label path="affaire.id" cssClass="control-label text-primary">Affaire :</form:label>
					<form:select path="affaire.id" cssClass="form-control">
					    <form:option value="0"><c:out value="Divers"/></form:option>
					    <c:forEach var="a" items="${listeA}">
					    		<fmt:formatDate value="${a.debut }" var="fDate" pattern="dd/MM/yyyy" />
					        <form:option value="${a.id}"><c:out value="${a.nom} du ${fDate } (${a.nbJours }j) - ${a.contact.societe.nom}"/></form:option>
					    </c:forEach>
   					</form:select>					
				</div>
		
		
		
				<c:set var="intituleErrors"><form:errors path="intitule"/></c:set>
				<c:choose>
					<c:when test="${not empty intituleErrors}">				
						<div class="form-group has-error"">	
					</c:when>		
						<c:otherwise>
							<div class="form-group">
						</c:otherwise>
				</c:choose>
				
				
					<form:label path="intitule" cssClass="control-label text-primary">Intitulé : </form:label> 
					<span class="text-danger"><form:errors path="intitule" /></span>
					<form:input path="intitule"  placeholder="intitulé ..."  cssClass="form-control" /> 
				</div>

				<div class="paddingLeftZero ">
					<c:set var="dateErrors"><form:errors path="date"/></c:set>
					<c:choose>
						<c:when test="${not empty dateErrors}">				
							<div class="form-group  has-error"">	
						</c:when>		
							<c:otherwise>
								<div class="form-group ">
							</c:otherwise>
					</c:choose>
				
						<form:label path="date" cssClass="control-label text-primary">Date :</form:label>
						<span class="text-danger"><form:errors path="date" /></span>
						<form:input   path="date" type="date" placeholder="Date  ..."  cssClass="form-control minWidth95" /> 
					</div>
				</div>

				<c:set var="lieuErrors"><form:errors path="lieu"/></c:set>
				<c:choose>
					<c:when test="${not empty lieuErrors}">				
						<div class="form-group has-error"">	
					</c:when>		
						<c:otherwise>
							<div class="form-group">
						</c:otherwise>
				</c:choose>
				
				
					<form:label path="lieu" cssClass="control-label text-primary">Lieu : </form:label> 
					<span class="text-danger"><form:errors path="lieu" /></span>
					<form:input path="lieu"  placeholder="Lieu ..."  cssClass="form-control" /> 
				</div>

					
				<div class="form-group">			
					<form:label path="montantTTC" cssClass="control-label text-primary">Montant TTC :</form:label>
					<form:input path="montantTTC"  placeholder="Montant TTC ..."  cssClass="form-control" /> 
				</div>
					
				<div class="form-group">			
					<form:label path="tva" cssClass="control-label text-primary">TVA :</form:label>
					<form:input path="tva"  placeholder="TVA ..."  cssClass="form-control" /> 
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
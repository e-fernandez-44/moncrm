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
<%-- 							<form:options items="${listeSocietes}" itemValue="id" itemLabel="nom"/> --%>
					    <c:forEach var="a" items="${listeA}">
					    		<fmt:formatDate value="${a.debut }" var="fDate" pattern="dd/MM/yyyy" />
					        <form:option value="${a.id}"><c:out value="${a.nom} du ${fDate } (${a.nbJours }j) à ${a.lieu } (${a.contact.societe.nom})"/></form:option>
					    </c:forEach>
   					</form:select>					
				</div>
				
				
				<c:set var="numeroErrors"><form:errors path="numero"/></c:set>
				<c:choose>
					<c:when test="${not empty numeroErrors}">				
						<div class="form-group has-error"">	
					</c:when>		
						<c:otherwise>
							<div class="form-group">
						</c:otherwise>
				</c:choose>
				
				
					<form:label path="numero" cssClass="control-label text-primary">Numéro : </form:label> 
					<span class="text-danger"><form:errors path="numero" /></span>
					<form:input path="numero"  placeholder="Numéro ..."  cssClass="form-control" /> 
				</div>

				
				<div class="paddingLeftZero ">
					<c:set var="factErrors"><form:errors path="dateFacturation"/></c:set>
					<c:choose>
						<c:when test="${not empty factErrors}">				
							<div class="form-group  has-error"">	
						</c:when>		
							<c:otherwise>
								<div class="form-group ">
							</c:otherwise>
					</c:choose>
				
						<form:label path="dateFacturation" cssClass="control-label text-primary">Date de Facturation :</form:label>
						<span class="text-danger"><form:errors path="dateFacturation" /></span>
						<form:input   path="dateFacturation" type="date" placeholder="Date de facturation ..."  cssClass="form-control minWidth95" /> 
					</div>
				</div>
				
				<div class="paddingLeftZero ">
					<c:set var="prevErrors"><form:errors path="dateEncaissementPrevu"/></c:set>
					<c:choose>
						<c:when test="${not empty prevErrors}">				
							<div class="form-group  has-error"">	
						</c:when>		
							<c:otherwise>
								<div class="form-group ">
							</c:otherwise>
					</c:choose>
				
						<form:label path="dateEncaissementPrevu" cssClass="control-label text-primary">Date Encaissement Prévu :</form:label>
						<span class="text-danger"><form:errors path="dateEncaissementPrevu" /></span>
						<form:input   path="dateEncaissementPrevu" type="date" placeholder="Date d'encaissement prévu ..."  cssClass="form-control minWidth95" /> 
					</div>
				</div>
				
				<div class="paddingLeftZero ">
					<c:set var="encErrors"><form:errors path="dateEncaissement"/></c:set>
					<c:choose>
						<c:when test="${not empty encErrors}">				
							<div class="form-group  has-error"">	
						</c:when>		
							<c:otherwise>
								<div class="form-group ">
							</c:otherwise>
					</c:choose>
				
						<form:label path="dateEncaissement" cssClass="control-label text-primary">Date Encaissement :</form:label>
						<span class="text-danger"><form:errors path="dateEncaissement" /></span>
						<form:input   path="dateEncaissement" type="date" placeholder="Date d'encaissement ..."  cssClass="form-control minWidth95" /> 
					</div>
				</div>
								
					
				<div class="form-group">			
					<form:label path="montant" cssClass="control-label text-primary">Montant :</form:label>
					<form:input path="montant"  placeholder="Montant ..."  cssClass="form-control" /> 
				</div>
					
				<div class="form-group">			
					<form:label path="tva" cssClass="control-label text-primary">TVA (%) :</form:label>
					<form:input path="tva"  placeholder="TVA ..."  cssClass="form-control" /> 
				</div>
				
				
				<div class="form-group">
					  <label for="facture1"  class="control-label text-primary "><form:checkbox path="facture" /> &nbsp;&nbsp;&nbsp;Facturé</label>
				 </div>
				
				<div class="form-group">
					  <label for="encaisse1"  class="control-label text-primary "><form:checkbox path="encaisse" /> &nbsp;&nbsp;&nbsp;Encaissé</label>
				 </div>
				
				<div class="form-group">			
					<form:label path="moisTravailles" cssClass="control-label text-primary">Mois Travaillés :</form:label>
					<form:textarea path="moisTravailles"  placeholder="Mois Travaillés (format : aaaamm=nb) ..."  cssClass="form-control"/> 
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
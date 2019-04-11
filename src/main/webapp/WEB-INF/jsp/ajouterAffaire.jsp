<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
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

	<c:set var="titre">${aff.id == 0 ? "Nouveau" : "Edition"}</c:set>


		<h1 class=" text-center bg-primary">${titre }</h1>


		<div class="col-sm-8 col-sm-offset-2">			
			
			<form:form action="ajout" method="POST" cssClass="form-horizontal" modelAttribute="aff">
			
				<form:hidden path="id"/>
				
				<div class="form-group">			
					<form:label path="contact.id" cssClass="control-label text-primary">Contact :</form:label>
					<form:select path="contact.id" cssClass="form-control">
<%-- 							<form:options items="${listeSocietes}" itemValue="id" itemLabel="nom"/> --%>
					    <c:forEach var="c" items="${listeContacts}">
					        <form:option value="${c.id}"><c:out value="${c.nom} ${c.prenom } - ${c.societe.nom} ${c.societe.adresse.ville}"/></form:option>
					    </c:forEach>
   					</form:select>					
				</div>
				
				
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

				<div class="col-xs-6 paddingLeftZero ">
					<c:set var="debutErrors"><form:errors path="debut"/></c:set>
					<c:choose>
						<c:when test="${not empty debutErrors}">				
							<div class="form-group dateAffaireGauche has-error"">	
						</c:when>		
							<c:otherwise>
								<div class="form-group dateAffaireGauche">
							</c:otherwise>
					</c:choose>
				
						<form:label path="debut" cssClass="control-label text-primary">Date de début :</form:label>
						<span class="text-danger"><form:errors path="debut" /></span>
						<form:input   path="debut" type="date" placeholder="Date de début ..."  cssClass="form-control minWidth95" /> 
					</div>
				</div>
				
				
				<div class="col-xs-6 paddingRightZero ">
					<c:set var="finErrors"><form:errors path="fin"/></c:set>
					<c:choose>
						<c:when test="${not empty finErrors}">				
							<div class="form-group dateAffaireDroite has-error"">	
						</c:when>		
							<c:otherwise>
								<div class="form-group dateAffaireDroite">
							</c:otherwise>
					</c:choose>

						<form:label path="fin" cssClass="control-label text-primary">Date de fin :</form:label>
						<span class="text-danger"><form:errors path="fin" /></span>
						<form:input   path="fin" type="date" placeholder="Date de fin ..."  cssClass="form-control minWidth95" /> 
					</div>
				</div>
 
				<div class="form-group">			
					<form:label path="nbJours" cssClass="control-label text-primary">Nombre de jours :</form:label>
					<form:input path="nbJours"  type="number" placeholder="Nombre de jours ..."  cssClass="form-control" /> 				
				</div>
				<div class="form-group">			
					<form:label path="lieu" cssClass="control-label text-primary">Lieu :</form:label>
					<form:input path="lieu"  placeholder="Lieu ..."  cssClass="form-control" /> 				
				</div>
				<div class="form-group">			
					<form:label path="clientFinal" cssClass="control-label text-primary">Client Final :</form:label>
					<form:input path="clientFinal"  placeholder="Client Final ..."  cssClass="form-control" /> 
				</div>
				
				<div class="form-group">			
					<form:label path="tarifJour" cssClass="control-label text-primary">Tarif Jour :</form:label>
					<form:input path="tarifJour"  placeholder="Tarif Jour ..."  cssClass="form-control" /> 
				</div>


				<fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${fraisReel}" var="fraisR" />

				<div class="form-group">			
					<label for="fraisReel" class="control-label text-primary">Frais Réels :</label>
					<input id ="fraisReel"  placeholder="Frais Réels ..."  class="form-control" readonly value="${fraisR }"/> 
				</div>
				
<%-- 				<div class="form-group">
					  <input type="checkbox" id="horsFrais" name="horsFrais" data-toggle="collapse" data-target="#affHorsFrais" aria-expanded="false" aria-controls="affHorsFrais"/>&nbsp;&nbsp;&nbsp;
				      <form:label path="horsFrais" cssClass="control-label text-primary" data-toggle="collapse" data-target="#affHorsFrais" aria-expanded="false" aria-controls="affHorsFrais">Tarifs Hors Frais</form:label>
				 </div>
				 
 --%>			
 
 				
				<div class="form-group">
					  <label for="horsFrais1"  class="control-label text-primary">
					  	<form:checkbox path="horsFrais" /> 
					  	&nbsp;&nbsp;&nbsp;Tarifs Hors Frais
					  </label>
				 </div>
 	
 				<div id="affHorsFrais" class=" panel panel-primary form-group">
					<div class="panel-body ">

						<div class="">			
							<form:label path="fraisRemb" cssClass="control-label text-primary">Frais Remboursés :</form:label>
							<form:input path="fraisRemb"  placeholder="Frais Remboursés ..."  cssClass="form-control" /> 
						</div>
					</div>
				</div>
				
				<div class="form-group">
					  <label for="annule1"  class="control-label text-primary "><form:checkbox path="annule" /> &nbsp;&nbsp;&nbsp;Annulé</label>
				 </div>
				
				<div class="form-group">
					  <label for="confirme1"  class="control-label text-primary "><form:checkbox path="confirme" /> &nbsp;&nbsp;&nbsp;Confirmé</label>
				 </div>
				
				<div class="form-group">
					  <label for="plusDeFrais1"  class="control-label text-primary "><form:checkbox path="plusDeFrais" /> &nbsp;&nbsp;&nbsp;Cloturé (Terminé pour frais)</label>
				 </div>

				<div class="form-group">			
					<form:label path="commentaire" cssClass="control-label text-primary">Commentaires :</form:label>
					<form:textarea path="commentaire"  placeholder="Commentaires ..."  cssClass="form-control"/> 
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
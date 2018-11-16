<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<div class="container">		<!-- pour une maquette responsive avec une largeur fixe. -->
								<!-- http://getbootstrap.com/css/#grid-options -->
		<h1 class=" text-center bg-primary">Factures <span class="badge alert-info">${listeF.size() }</span></h1>
		
 		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
					
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
				  			<input type="search" class="form-control" placeholder="Filtre sur numéro, affaire, contact ou société ..." id="txt" name="txt" value="${txt }" autofocus>
				  			<span class="input-group-btn">
				        			<button type = "submit" class="btn btn-default bleu" type="button"><i class="fas fa-search"></i> </button>
				      		</span>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				
				<table class="table table-hover table-striped ">
				        <colgroup>
            					<col class="col-xs-4">
            					<col class="col-xs-8">
        					</colgroup>
					<tr>
						<th> 
							
							<a id="nA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a>
							
							<h5 class="inline text-info">&nbsp;Num&nbsp;</h5> 
							<a id="nD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
						<th class="">
							<a id="aA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a> 
							<h5 class="inline text-info">&nbsp;Facture&nbsp;</h5> 
							<a id="aD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
					</tr>
					<c:forEach items="${listeF}" var="f" varStatus="bStatus">
					
						<tr>
							<td>
								${f.numero} <br>
								<c:if test="${f.encaisse }"> <i class="fas fa-check-circle vert" ></i></c:if>
								<c:if test="${!f.encaisse }"><i class="fas fa-ban rouge"></i></c:if>
							</td>
							<td><a href="voir?index=${f.id}">${f.affaire.nom}</a><br>
								Début le <fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${f.affaire.debut}" /> (${f.affaire.nbJours}j)<br>
								A ${f.affaire.lieu} <br>
								<a href="../contacts/voir?index=${f.affaire.contact.id }">${f.affaire.contact.prenom } ${f.affaire.contact.nom}</a><br>
								<a href="../societes/voir?index=${f.affaire.contact.societe.id }">${f.affaire.contact.societe.nom} ${f.affaire.contact.societe.adresse.ville} </a>
							</td>
							
						</tr>
					</c:forEach>
				</table>			
			</div>
		</div>

	
	</div>

	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
<%@page import="efe.crm.bean.Action"%>
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

	<script type="text/javascript">
		$(function(){

			$('#annuler').on('click', function(){
				//
			})
			
			$('#valider').on('click', function(){
				$("#supp").submit();
			})
			
		});

	</script>
</head>
	

<body>
	<%@ include file="/WEB-INF/jsp/menu.jspf" %>		
	

	<div class="container">		

		<h1 class=" text-center bg-primary">Action</h1>

		<c:if test="${message != null}">
			<div class="alert alert-danger  alert-dismissible">
				<button type="button" class="close" data-dismiss="alert" >&times;</button>
				<strong>Erreur : </strong> ${message}.  
			</div>
		</c:if>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">			
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="fas fa-info-circle"></i> &nbsp;&nbsp;Informations</h3>
					</div>
				
					<div class="panel-body">
						<c:set var="date" value='<%=((Action)request.getAttribute("action")).getDateLimite()%>'/>
						<p><i class="fas fa-star ${a.importance == 'AFort' ? 'rouge' :  a.importance == 'BMoyen' ? 'orange' : 'vert'}"></i> &nbsp;&nbsp; : &nbsp;&nbsp;${action.tache }</p>
						<p><i class="far fa-calendar-alt"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${date}" /> </p>

								<c:choose>
									<c:when test="${action.societe != null }">
										<i class="fas fa-industry"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../societes/voir?index=${action.societe.id }">${action.societe.nom } - ${action.societe.adresse.ville }</a>
									</c:when>
									<c:when test="${action.contact != null }">
										<i class="fas fa-user"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../contacts/voir?index=${action.contact.id }">${action.contact.prenom } ${action.contact.nom }</a> (<a href="../societes/voir?index=${action.contact.societe.id }">${action.contact.societe.nom }</a>)
									</c:when>
									<c:when test="${action.affaire != null }">
										<i class="fas fa-search-dollar"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../affaires/voir?index=${action.affaire.id }">${action.affaire.nom }</a>  du <fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${action.affaire.debut}" /> 
											(${action.affaire.nbJours }j) à ${action.affaire.lieu }
									</c:when>
									<c:when test="${action.facture != null }">
										<i class="fas fa-money-check-alt"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../facture/voir?index=${action.facture.id }">${action.facture.numero }</a> <br>
										<a href="../affaires/voir?index=${action.facture.affaire.id }">${action.facture.affaire.nom }</a>  du <fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${action.facture.affaire.debut}" /> 
											(${action.facture.affaire.nbJours }j) à ${action.facture.affaire.lieu }
									</c:when>
								
								</c:choose>


					</div>
					<div class="panel-footer">
						<div class="pull-left" ><a href="modif?index=${action.id }" class="btn btn-warning">Modifier</a> </div>
						<div class="pull-right"><button class="btn btn-danger" data-toggle="modal" data-target="#data" data-backdrop="static">Supprimer</button> </div>		
						<div class="clearfix"></div>			
					</div>
				</div>
			</div>
		</div>

<!--   -->
		<form id="supp" method="post" action="suppr">
			<input type="hidden" name="index" value="${action.id }" />
		</form>
	
			<div id="data" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Validation de la suppression</h4>
						</div>
						<div class="modal-body">						
							Voulez-vous supprimer l'action ${action.tache} ?
						</div>	
						<div class="modal-footer">
							<button id="annuler" class="btn btn-success" data-dismiss="modal">Annuler</button>
							
							<button id="valider" class="btn btn-danger" data-dismiss="modal">Valider</button>
						</div>					
					</div>
				</div>
			</div>


	
	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
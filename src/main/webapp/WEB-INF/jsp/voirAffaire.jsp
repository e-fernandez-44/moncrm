<%@page import="efe.crm.bean.Affaire"%>
<%@page import="java.util.Date"%>
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

		<h1 class=" text-center bg-primary">${aff.nom }</h1>

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
						<h3 class="panel-title"><i class="fas fa-search-dollar"></i> &nbsp;&nbsp;Informations</h3>
					</div>
				
					<div class="panel-body">
						<c:set var="deb" value='<%=((Affaire)request.getAttribute("aff")).getDebut()%>'/>
						<c:set var="fin" value='<%=((Affaire)request.getAttribute("aff")).getFin()%>'/>
						<p><i class="far fa-calendar-alt"></i> &nbsp;&nbsp; : &nbsp;&nbsp;Du
						<fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${deb}" />
						 au 
						<fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${fin}" />
						 (${aff.nbJours } jour${aff.nbJours > 1 ? "s":"" })
						 </p>
						
						<p><i class="fas fa-city"></i> &nbsp;&nbsp; : &nbsp;&nbsp;${aff.lieu}	</p>
						
						<p class="text-right marge0">Tarif jour : <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${aff.tarifJour }" /> €</p>
						<p class="text-right marge0">Tarif hors frais : ${aff.horsFrais?'Oui':'Non' }</p>
						<p class="text-right marge0">Frais réels : <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${fraisReel }" /> €</p>
						<p class="text-right marge0">Frais remboursés : <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${aff.fraisRemb }" /> €</p>
						
						<p class=" marge0"><span class="decalage">Confirmé : </span> 
							<c:if test="${aff.confirme }"> <i class="fas fa-check-circle vert" ></i></c:if>
							<c:if test="${!aff.confirme }"><i class="far fa-circle"></i></c:if>
						</p>
						<p class=" marge0"><span class="decalage">Annulé :  </span> 
							<c:if test="${aff.annule }"> <i class="fas fa-check-circle rouge" ></i></c:if>
							<c:if test="${!aff.annule }"><i class="far fa-circle"></i></c:if>
						</p>
						<p><span class="decalage">Cloturé :  </span> 
							<c:if test="${aff.plusDeFrais }"> <i class="fas fa-check-circle rouge" ></i></c:if>
							<c:if test="${!aff.plusDeFrais }"><i class="far fa-circle"></i></c:if>
						</p>
						<p>
							<i class="fas fa-user"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../contacts/voir?index=${aff.contact.id }">${aff.contact.prenom} ${aff.contact.nom}</a>
						</p>
						<p>
							<i class="fas fa-industry"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../societes/voir?index=${aff.contact.societe.id }">${aff.contact.societe.nom} ${aff.contact.societe.adresse.ville}</a>
						</p>
						<p>
							<i class="fas fa-comment-dots"></i> &nbsp;&nbsp; : &nbsp;&nbsp;${aff.commentaire }
						</p>
 					</div>
					<div class="panel-footer">
						<div class="pull-left" ><a href="modif?index=${aff.id }" class="btn btn-warning">Modifier</a> </div>
						<div class="pull-right"><button class="btn btn-danger" data-toggle="modal" data-target="#data" data-backdrop="static">Supprimer</button> </div>		
						<div class="clearfix"></div>			
					</div>
				</div>
			</div>
		</div>

<!--   -->
		<form id="supp" method="post" action="suppr">
			<input type="hidden" name="index" value="${aff.id }" />
		</form>
	
			<div id="data" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Validation de la suppression</h4>
						</div>
						<div class="modal-body">						
							Voulez-vous supprimer l'affaire ${aff.nom } ?
						</div>	
						<div class="modal-footer">
							<button id="annuler" class="btn btn-success" data-dismiss="modal">Annuler</button>
							
							<button id="valider" class="btn btn-danger" data-dismiss="modal">Valider</button>
						</div>					
					</div>
				</div>
			</div>


<!--  -->

	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
<%@page import="efe.crm.bean.Facture"%>
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

		<h1 class=" text-center bg-primary">facture ${f.numero }</h1>

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
						<h3 class="panel-title"><i class="fas fa-money-check-alt"></i> &nbsp;&nbsp;Informations</h3>
					</div>
				
					<div class="panel-body">

						<p class="marge0"><i class="fas fa-search-dollar"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../affaires/voir?index=${f.affaire.id }">${f.affaire.nom }</a></p>
						
						<c:set var="deb" value='<%=((Facture)request.getAttribute("f")).getAffaire().getDebut()%>'/>
						<c:set var="fin" value='<%=((Facture)request.getAttribute("f")).getAffaire().getFin()%>'/>
						<c:set var="fact" value='<%=((Facture)request.getAttribute("f")).getDateFacturation()%>'/>
						<c:set var="prev" value='<%=((Facture)request.getAttribute("f")).getDateEncaissementPrevu()%>'/>
						<c:set var="enc" value='<%=((Facture)request.getAttribute("f")).getDateEncaissement()%>'/>
						
						<p class="marge0"><i class="far fa-calendar-alt"></i> &nbsp;&nbsp; : &nbsp;&nbsp;Du
						<fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${deb}" />
						 au 
						<fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${fin}" />
						 (${f.affaire.nbJours } jour${f.affaire.nbJours > 1 ? "s":"" })
						 </p>
						<p class="marge0"><i class="fas fa-industry"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../societes/voir?index=${f.affaire.contact.societe.id }">${f.affaire.contact.societe.nom }</a></p>
						<p class="marge0"><i class="fas fa-user"></i> &nbsp;&nbsp; : &nbsp;&nbsp;<a href="../contact/voir?index=${f.affaire.contact.id }">${f.affaire.contact.prenom } ${f.affaire.contact.nom }</a></p>
						<p class="marge0"><i class="fas fa-city"></i> &nbsp;&nbsp; : &nbsp;&nbsp;${f.affaire.lieu }</p>
						
						<p class="text-right marge0"><i class="far fa-calendar-alt"></i>&nbsp;&nbsp;Facturation : <fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${fact}" /></p>
						<p class="text-right marge0"><i class="far fa-calendar-alt"></i>&nbsp;&nbsp;Encaissement prévu : <fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${prev}" /></p>
						<p class="text-right marge0"><i class="far fa-calendar-alt"></i>&nbsp;&nbsp;Encaissement : <fmt:formatDate type = "date" pattern="dd/MM/yyyy" value = "${enc}" /></p>
						
						<p class="marge0"><i class="fas fa-file-invoice-dollar"></i></i> &nbsp;&nbsp; : &nbsp;&nbsp;${f.montant } € HT</p>
						<p class="marge0"><i class="fas fa-file-invoice-dollar"></i></i> &nbsp;&nbsp; : &nbsp;&nbsp;TVA : ${f.tva } %</p>
						<p><i class="fas fa-file-invoice-dollar"></i></i> &nbsp;&nbsp; : &nbsp;&nbsp;Mois Travaillés : ${f.moisTravailles }</p>
						
						<p class=" marge0"><span class="decalage">Facturé : </span> 
							<c:if test="${f.facture }"> <i class="fas fa-check-circle vert" ></i></c:if>
							<c:if test="${!f.facture }"><i class="far fa-circle"></i></c:if>
						</p>
						<p><span class="decalage">Encaissé :  </span> 
							<c:if test="${f.encaisse }"> <i class="fas fa-check-circle vert" ></i></c:if>
							<c:if test="${!f.encaisse }"><i class="far fa-circle"></i></c:if>
						</p>
						
						
						<div class="col-sm-10 col-sm-offset-1 padding0">
							<table class="table table-hover table-striped ">
								<tr>
									<td  class="text-right">Montant facturé HT :</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montant}" /> €</td>
								</tr>
								<tr>
									<td  class="text-right">Montant facturé TTC :</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montantFacture}" /> €</td>
								</tr>
								<tr>
									<td  class="text-right">Montant Frais facturés :</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.affaire.fraisRemb}" /> €</td>
								</tr>
								<tr>
									<td  class="text-right">Total Frais :</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${fraisReel}" /> €</td>
								</tr>
								<tr>
									<td  class="text-right">Total Frais/j :</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${fraisReel / f.affaire.nbJours}" /> €</td>
								</tr>
								<tr>
									<td  class="text-right">Total HT Hors Frais :</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montant - fraisReel + f.affaire.fraisRemb}" /> €</td>
								</tr>
								<tr>
									<td  class="text-right">Total HT Hors Frais Jour:</td>
									<td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${(f.montant - fraisReel + f.affaire.fraisRemb) / f.affaire.nbJours}" /> €</td>
								</tr>
							</table>
						</div>
 					</div>
					<div class="panel-footer">
						<div class="pull-left" ><a href="modif?index=${f.id }" class="btn btn-warning">Modifier</a> </div>
						<div class="pull-right"><button class="btn btn-danger" data-toggle="modal" data-target="#data" data-backdrop="static">Supprimer</button> </div>		
						<div class="clearfix"></div>			
					</div>
				</div>
			</div>
		</div>

<!--   -->
		<form id="supp" method="post" action="suppr">
			<input type="hidden" name="index" value="${f.id }" />
		</form>
	
			<div id="data" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Validation de la suppression</h4>
						</div>
						<div class="modal-body">						
							Voulez-vous supprimer la facture ${f.numero } ?
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
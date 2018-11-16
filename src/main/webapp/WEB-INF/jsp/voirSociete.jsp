<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
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

		<h1 class=" text-center bg-primary">${soc.nom }</h1>

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
						<h3 class="panel-title"><i class="fas fa-envelope"></i> &nbsp;&nbsp;Adresse</h3>
					</div>
				
					<div class="panel-body">
						<p>${soc.getAdresse().getAdresse() }</p>
						<c:if test="${soc.getAdresse().getAdresse2().isEmpty() == false}">
							<p>${soc.getAdresse().getAdresse2()}</p>
						</c:if>
						<p>${soc.getAdresse().getCodePostal() } ${soc.getAdresse().getVille() }</p>
					</div>
					<div class="panel-footer">
						<div class="pull-left" ><a href="modif?index=${soc.id }" class="btn btn-warning">Modifier</a> </div>
						<div class="pull-right"><button class="btn btn-danger" data-toggle="modal" data-target="#data" data-backdrop="static">Supprimer</button> </div>		
						<div class="clearfix"></div>			
					</div>
				</div>
			</div>
		</div>

<!--   -->
		<form id="supp" method="post" action="suppr">
			<input type="hidden" name="index" value="${soc.id }" />
		</form>
	
			<div id="data" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Validation de la suppression</h4>
						</div>
						<div class="modal-body">						
							Voulez-vous supprimer la société ${soc.nom} ?
						</div>	
						<div class="modal-footer">
							<button id="annuler" class="btn btn-success" data-dismiss="modal">Annuler</button>
							
							<button id="valider" class="btn btn-danger" data-dismiss="modal">Valider</button>
						</div>					
					</div>
				</div>
			</div>


<!--  -->

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">			
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title"><i class="fas fa-user"></i> &nbsp;&nbsp;Contacts</h3>
					</div>
					<div class="panel-body">
						<c:forEach items="${listeC}" var="c" varStatus="bStatus">
							<p><a href="../contacts/voir?index=${c.id }">${c.prenom} ${c.nom }</a> (${c.fonction })</p>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>

	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
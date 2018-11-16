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
</head>
<body>
	<%@ include file="/WEB-INF/jsp/menu.jspf" %>		

	<div class="container">		<!-- pour une maquette responsive avec une largeur fixe. -->
								<!-- http://getbootstrap.com/css/#grid-options -->
		<h1 class=" text-center bg-primary">Sociétés <span class="badge alert-info">${listeS.size() }</span></h1>
		
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
				  			<input type="search" class="form-control" placeholder="Filtre sur société et ville..." id="txt" name="txt" value="${txt }" autofocus>
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
					<tr>
						<th class=""> 
							
							<a id="nA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a>
							
							<h4 class="inline text-info">&nbsp;Société&nbsp;</h4> 
							<a id="nD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
						<th class="">
							<a id="vA" href="#"><span class="glyphicon glyphicon-menu-down"></span></a> 
							<h4 class="inline text-info">&nbsp;Ville&nbsp;</h4> 
							<a id="vD" href="#"><span class="glyphicon glyphicon-menu-up"></span></a>
						</th>
					</tr>
					<c:forEach items="${listeS}" var="s" varStatus="bStatus">
						<tr>
							<td><a href="voir?index=${s.id}">${s.nom}</a></td>
							<td>${s.adresse.ville}</td>
						</tr>
					</c:forEach>
				</table>			
			</div>
		</div>

	
	</div>

	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
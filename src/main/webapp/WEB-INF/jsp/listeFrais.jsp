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
	<script src="../../js/ajaxJquery.js"></script>





	<c:if test="${index != null}">
		 <script>
		 
		 $(function(){
			 var top = document.getElementById("f${index}").offsetTop;
			 window.scrollTo(0, top);  
		});
	   	</script>
	
	</c:if>

	<title>Mon CRM (${index })</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/menu.jspf" %>		

	<div class="container">		<!-- pour une maquette responsive avec une largeur fixe. -->
								<!-- http://getbootstrap.com/css/#grid-options -->
		<h1 class=" text-center bg-primary">Frais </h1>
		
 		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
					
				<div class="col-xs-2 paddingLeftZero">
					<a href="ajout" class="btn btn-default bleu" ><i class="fas fa-plus-square"></i></a>
				</div>
				<div class="col-xs-10 paddingRightZero" >
					<form method="get" action="recherche" >
				 		<div class="input-group margeBas">
				  			<span class="input-group-addon bleu">
				  				<span class="far fa-address-card"></span>
				  			</span>
				  			<input type="search" class="form-control" placeholder="Filtre sur affaire, société et ville..." id="txt" name="txt" value="${txt }" autofocus>
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
				
				
				<ul class="list-group">
					<li class="list-group-item" id="f0">
					
							<c:if test="${index == 0 }">
							
								<table >
									<tr>
										<td class="trente"><a href="ajout?aff=0" class="btn btn-default btn-xs bleu" ><i class="fas fa-plus-square"></i></a></td>
							
										<td class="width100">
											<span class="text-primary"><strong>Divers</strong></span> 
										</td>
									</tr>
								</table>

								<table class=" decalageHaut table table-striped">
									<c:forEach items="${fraisAffaire}" var="f" varStatus="bStatus">
										<fmt:formatDate value="${f.date }" var="fDate" pattern="dd/MM/yyyy" />
										<tr>
											<td> <span class="text-primary"><strong>${fDate}</strong> - 
											<a href="voir?index=${f.id }">${f.intitule }</a>
											</span> (${f.lieu })<br>
												<span class="text-info"><strong><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montantTTC}" />€</strong></span>
												(HT : 
												<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montantHT}" />€
												 - TVA : 
												 <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.tva}" />€)
											
											</td>
											<td>
												<c:if test="${f.validate }">
													<i class="fas fa-check-circle fraisAjax fraisCheck bleu text-right" id="${f.id}" ></i>
												</c:if>
												<c:if test="${!f.validate }">
													<i class="far fa-circle fraisAjax fraisCheck bleu text-right" id="${f.id}" ></i>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
								
							</c:if>
							<c:if test="${index != 0 }">
								
								<table >
									<tr>
										<td class="trente"><a href="ajout?aff=0" class="btn btn-default btn-xs bleu" ><i class="fas fa-plus-square"></i></a></td>
										<td><a href="affaire?index=0"><strong>Divers</strong></a></td>
									</tr>
								</table>
							</c:if>	
					</li>
					
					<c:forEach items="${listeA}" var="a" varStatus="bStatus">
						<fmt:formatDate value="${a.debut }" var="aDate" pattern="dd/MM/yyyy" />
						<li class="list-group-item" id="f${a.id}">
							<c:if test="${index == a.id }">
							
								<table>
									<tr>
										<td class="trente "><a href="ajout?aff=${a.id }" class="btn btn-default btn-xs bleu ${a.plusDeFrais? 'disabled':'' }" ><i class="fas fa-plus-square"></i></a></td>
							
										<td class="width100 " >
											<span class="text-primary"><strong>${a.nom}</strong></span> du <span class="text-primary">${aDate } (${a.nbJours }j)</span> à ${a.lieu } (${a.contact.societe.nom})
										</td>
									</tr>
								</table>
										
								<table class=" decalageHaut table table-striped">
									<c:forEach items="${fraisAffaire}" var="f" varStatus="bStatus">
										<fmt:formatDate value="${f.date }" var="fDate" pattern="dd/MM/yyyy" />
										<tr>
											<td><span class="text-primary"><strong>${fDate}</strong> - 
											<a href="voir?index=${f.id }">${f.intitule }</a>
											</span> (${f.lieu })<br>
												<span class="text-info"><strong><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montantTTC}" />€</strong></span>
												(HT : 
												<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.montantHT}" />€
												 - TVA : 
												 <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${f.tva}" />€)
											
											</td>
											
											<td>
												<c:if test="${f.validate }">
													<i class="fas fa-check-circle fraisAjax fraisCheck bleu text-right" id="${f.id}" ></i>
												</c:if>
												<c:if test="${!f.validate }">
													<i class="far fa-circle fraisAjax fraisCheck bleu text-right" id="${f.id}" ></i>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</table>
								
								
							</c:if>
							<c:if test="${index != a.id }">
								<table >
									<tr>
										<td class="trente"><a href="ajout?aff=${a.id }" class="btn btn-default btn-xs bleu ${a.plusDeFrais? 'disabled':'' }" ><i class="fas fa-plus-square"></i></a></td>
										<td><a href="affaire?index=${a.id}"><strong>${a.nom}</strong></a> du <span class="text-primary">${aDate } (${a.nbJours }j)</span> à ${a.lieu } (${a.contact.societe.nom})</td>
									</tr>
								</table>
							</c:if>	
						</li>
					</c:forEach>
				</ul>		
			</div>
		</div>

	
	</div>

	<%@ include file="/WEB-INF/jsp/footer.jspf" %>		

</body>
</html>
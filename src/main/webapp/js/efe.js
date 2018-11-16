$(function(){
/*	
	$("#nA").on("click", function(){
		var att = $(this).attr("id");
		var rep = confirm("Voulez-vous supprimer l'élément ?");
		if (rep)
			window.location="supprimer?index=" + att;
	});
*/	
	$("#nA").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=nA";
	});
	$("#nD").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=nD";
	});
	$("#vA").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=vA";
	});
	$("#vD").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=vD";
	});
	$("#sA").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=sA";
	});
	$("#sD").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=sD";
	});
	$("#dA").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=dA";
	});
	$("#dD").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=dD";
	});
	$("#aA").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=aA";
	});
	$("#aD").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=aD";
	});
	$("#cA").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=cA";
	});
	$("#cD").on("click", function(){
		window.location="recherche?txt=" + $("#txt").val() + "&tri=cD";
	});

});
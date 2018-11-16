$(function(e){
	$('.fraisAjax').on("click", function() {
		var id = $(this).attr('id');
//		var isV = $(this).is(':checked');
		var isV = $(this).hasClass('fa-circle');
		
		if (isV){
			$(this).removeClass('fa-circle');
			$(this).addClass('fa-check-circle');
			$(this).addClass('fas');
			$(this).removeClass('far');
		}else{
			$(this).addClass('fa-circle');
			$(this).addClass('far');
			$(this).removeClass('fa-check-circle');
			$(this).removeClass('fas');
			
		}
		
		var host = window.location.host;
		console.log("http://" + host+"/moncrm/rs/frais/"+id+"/"+isV);
		
		$.get("http://" + host+"/moncrm/app/fraisrs/"+id+"/"+isV, false, rafraichir, 'text');

		
		
		
		/*		$.get("http://176.132.183.175:9000/CRM/rs/frais/"+id+"/"+isV, false, rafraichir, 'text'); */
/*		$.get("http://31.38.50.171:8181/CRM/rs/frais/"+id+"/"+isV, false, rafraichir, 'text');
		$.get("http://localhost:8080/CRM/rs/frais/"+id+"/"+isV, false, rafraichir, 'text');*/
});

});




function rafraichir(){
	console.log("appel OK");
}
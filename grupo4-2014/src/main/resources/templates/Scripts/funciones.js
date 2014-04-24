
function cargaamigos() {
  	$.ajax({
        type: "GET",
        url: "/lists",
        success: function(ajaxresult){
        	var html = "";
        	for(var k in ajaxresult) {
   				html = html + ajaxresult[k].id;
   					html = html + " ";
			}
  	     $(".panel-body").html(html);
		}
	});
}
function loadFriends() {
	$.ajax({
	    type: "GET",
		url: "/users",
		success: function(ajaxresult){
			$("#listUsers").empty();
			var row = $("#rowTemplateUsers").html().replace("{{USER}}", "Yo");
			$("#listUsers").append(row);
			for(var k in ajaxresult) {
				row = $("#rowTemplateUsers").html().replace("{{USER}}", ajaxresult[k].username);
				$("#listUsers").append(row);
			}
			$('#listUsers a').click(loadLists);
		}
	});
}

function loadLists() {
	$.ajax({
	    type: "GET",
		url: "/lists",
		success: function(ajaxresult){
			$("#listOfList").empty();
			for(var k in ajaxresult) {
				row = $("#rowTemplateLists").html().replace("{{LIST}}", ajaxresult[k].name);
				$("#listOfList").append(row);
			}
			$('#listOfList a').click(loadItems);
		}
	});
}

function loadItems() {
	$.ajax({
	    type: "GET",
		url: "lists/12341/items",
		success: function(ajaxresult){
			$("#listOfItems").empty();
			for(var k in ajaxresult) {
				row = $("#rowTemplateItems").html().replace("{{VOTES}}", ajaxresult[k].votes).replace("{{ITEM}}", ajaxresult[k].label);
				$("#listOfItems").append(row);
			}
		}
	});
}
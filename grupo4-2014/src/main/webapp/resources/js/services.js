function loadFriends(loadUserLists) {
	$.ajax({
	    type: "GET",
		url: "/users",
		success: function(ajaxresult){
			$("#listUsers").empty();
			var row = null;
			for(var k in ajaxresult) {
				app.model.users[ajaxresult[k].username] = ajaxresult[k];
				if(loadUserLists && 'Yo' == ajaxresult[k].username){
					row = $("#rowTemplateUsersActive").html().replace("{{USER}}", ajaxresult[k].username);
				}else{
					row = $("#rowTemplateUsers").html().replace("{{USER}}", ajaxresult[k].username);
				}
				$("#listUsers").append(row);
			}
			$('#listUsers a').click(loadLists);
			if(loadUserLists){
				loadListsFrom('Yo', true);
			}
		}
	});
}

function loadLists() {
	var userName = $(this).text();
	$("#listUsers a").removeClass('active');
	$(this).addClass('active');
	loadListsFrom(userName, false);
}

function loadListsFrom(userName, loadItemsList){
	app.model.userSelected = app.model.users[userName];
	var user = app.model.users[userName];
	if(userName == "Yo"){
		$("#labelPanelLists").text("Mis listas");
	}else{
		$("#labelPanelLists").text("Listas de " + userName);
	}
	$.ajax({
		type: "GET",
		url: "/user/" + user.id + "/lists/",
		success: function(ajaxresult){
			var listName = '';
			var row = null;
			$("#listOfList").empty();
			for(var k in ajaxresult) {
				if(loadItemsList && listName == ''){
					listName = ajaxresult[k].name;
					row = $("#rowTemplateListsActive").html().replace("{{LIST}}", ajaxresult[k].name);
				}else{
					row = $("#rowTemplateLists").html().replace("{{LIST}}", ajaxresult[k].name);
				}
				user.lists[ajaxresult[k].name] = ajaxresult[k]; 
				$("#listOfList").append(row);
			}
			$('#listOfList a').click(loadItems);
			if(loadItemsList){
				loadItemsFrom(listName);
			}else{
				$("#listOfItems").empty();
				$('#labelPanelItems').text("Items de la lista...");
			}
		}
	});
}

function loadItems() {
	var listName = $(this).text();
	$("#listOfList a").removeClass('active');
	$(this).addClass('active');
	loadItemsFrom(listName);
}

function loadItemsFrom(listName){
	$('#labelPanelItems').text("Items de la lista " + listName);
	var list = app.model.userSelected.lists[listName];
	$.ajax({
		type: "GET",
		url: "/user/" + app.model.userSelected.id + "/lists/" + list.id + "/items",
		success: function(ajaxresult){
			$("#listOfItems").empty();
			for(var k in ajaxresult) {
				row = $("#rowTemplateItems").html().replace("{{VOTES}}", ajaxresult[k].votes).replace("{{ITEM}}", ajaxresult[k].label);
				$("#listOfItems").append(row);
			}
		}
	});
}
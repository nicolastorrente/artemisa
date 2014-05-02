function loadFriends(loadUserLists) {
	$.ajax({
	    type: "GET",
		url: "/users",
		success: function(ajaxresult){
			$("#listUsers").empty();
			if(loadUserLists){
				$("#myLists").empty();
			}
			var row = null;
			for(var k in ajaxresult) {
				app.model.users[ajaxresult[k].id] = ajaxresult[k];
				if(loadUserLists && 'Yo' == ajaxresult[k].username){
					row = $("#rowTemplateUsersActive").html().replace("{{USER}}", "Mis Listas");
					row = row.replace("{{USER_ID}}", ajaxresult[k].id);
					$("#myLists").append(row);
					app.model.myId = ajaxresult[k].id;
				}else{
					row = $("#rowTemplateUsers").html().replace("{{USER}}", ajaxresult[k].username);
					row = row.replace("{{USER_ID}}", ajaxresult[k].id);
					$("#listUsers").append(row);
				}
			}
			$('#listUsers a, #myLists a').click(loadLists);
			if(loadUserLists){
				loadListsFrom(app.model.myId, true);
			}
		}
	});
}

function loadLists() {
	  var userId = $(this).attr('id');
	  $("#listUsers a").removeClass('active');
	  $("#myLists a").removeClass('active');
	  $(this).addClass('active');
	  loadListsFrom(userId, false);
};

function loadListsFrom(userId, loadItemsList){
	app.model.userSelected = app.model.users[userId];
	var user = app.model.userSelected;
	if(app.model.myId == userId){
		$("#labelPanelLists").text("Mis listas");
	}else{
		$("#labelPanelLists").text("Listas de " + user.username);
	}
	$.ajax({
		type: "GET",
		url: "/users/" + user.id + "/lists/",
		success: function(ajaxresult){
			var listId = '';
			var row = null;
			$("#listOfList").empty();
			for(var k in ajaxresult) {
				if(loadItemsList && listId == ''){
					listId = ajaxresult[k].id;
					row = $("#rowTemplateListsActive").html().replace("{{LIST}}", ajaxresult[k].name).replace("{{ID_LIST}}", ajaxresult[k].id);
				}else{
					row = $("#rowTemplateLists").html().replace("{{LIST}}", ajaxresult[k].name).replace("{{ID_LIST}}", ajaxresult[k].id);
				}
				user.lists[ajaxresult[k].id] = ajaxresult[k]; 
				$("#listOfList").append(row);
			}
			$('#listOfList a').click(loadItems);
			if(loadItemsList){
				loadItemsFrom(listId);
			}else{
				$("#listOfItems").empty();
				$('#labelPanelItems').text("Items de la lista...");
			}
		}
	});
}

function loadItems() {
	$("#listOfList a").removeClass('active');
	$(this).addClass('active');
	var userId = $(this).attr('id');
	loadItemsFrom(userId);
}

function loadItemsFrom(listId){
	app.model.selectedList = app.model.userSelected.lists[listId];
	var list = app.model.userSelected.lists[listId];
	$('#labelPanelItems').text("Items de la lista " + list.name);
	$("#listOfItems").empty();
	for(var k in list.items) {
		row = $("#rowTemplateItems").html().replace("{{VOTES}}", list.items[k].votes).replace("{{ITEM}}", list.items[k].label);
		$("#listOfItems").append(row);
	}
}

$(function addUser() {
	  $('#AgregarUsuario').on('click', function () {
		  var userjson = {};
		  userjson['username'] = $('#username').val();
		  userjson['lists'] = [];
		  $.ajax({
			    url : "/users",
			    type: "POST",
			    data : JSON.stringify(userjson),
			    contentType: 'application/json',
			    success: function(data, textStatus, jqXHR)
			    {
			    	loadFriends(false);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar usuario.');
			    }
			});
	  });
});

$(function addList() {
	  $('#AgregarLista').on('click', function () {
		  var userjson = {};
		  userjson['name'] = $('#lista_nombre').val();
		  userjson['items'] = [];
		  $.ajax({
			    url : "/users/" + app.model.userSelected.id + "/lists",
			    type: "POST",
			    data : JSON.stringify(userjson),
			    contentType: 'application/json',
			    success: function(data, textStatus, jqXHR)
			    {
			    	loadListsFrom(app.model.userSelected.id,false);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar lista.');
			    }
			});
	  });
});

$(function EliminarLista() {
	  $('#eliminar_Lista').on('click', function (e) {
		  e.preventDefault();
		  $.ajax({ 
			    url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id, // 12341 lista harcodeada 
			    type: "DELETE",
			    success: function(data, textStatus, jqXHR)
			    {
			    	loadFriends(app.model.userSelected.id);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar lista.');
			    }
			});
	  });
});
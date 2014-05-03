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
			var listId = 0;
			var row = null;
			app.model.userLists = [];
			$("#listOfList").empty();
			for(var k in ajaxresult) {
				if(loadItemsList && listId == 0){
					app.model.selectedList = ajaxresult[k];
					listId = ajaxresult[k].id;
					row = $("#rowTemplateListsActive").html().replace("{{LIST}}", ajaxresult[k].name).replace("{{LIST_ID}}", ajaxresult[k].id);
				}else{
					row = $("#rowTemplateLists").html().replace("{{LIST}}", ajaxresult[k].name).replace("{{LIST_ID}}", ajaxresult[k].id);
				}
				app.model.userLists[ajaxresult[k].id] = ajaxresult[k]; 
				$("#listOfList").append(row);
			}
			$('#listOfList a').click(loadItems);
			if(loadItemsList){
				loadItemsFrom(listId, true);
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
	loadItemsFrom(app.model.selectedList.id, true);
}

function refreshItems() {
	$("#listOfItems a").removeClass('active');
	$(this).addClass('active');
}

function loadItemsFrom(listId, loadItemsList){
	$('#labelPanelItems').text("Items de la lista " + app.model.selectedList.name);
	$.ajax({
		type: "GET",
		url: "/users/" + app.model.userSelected.id + "/lists/" + listId,
		success: function(ajaxresult){
			$("#listOfItems").empty();
			var itemId = 0;
			var row = null;
			app.model.selectedList.items = [];
			for(var k in ajaxresult.items) {
				if(loadItemsList && itemId == 0){
					itemId = ajaxresult.items[k].id;
					row = $("#rowTemplateItemsActive").html().replace("{{VOTES}}", ajaxresult.items[k].votes).replace("{{ITEM_ID}}", ajaxresult.items[k].id).replace("{{ITEM}}", ajaxresult.items[k].label);
					app.model.selectedItem = ajaxresult.items[k];
				} else {
					row = $("#rowTemplateItems").html().replace("{{VOTES}}", ajaxresult.items[k].votes).replace("{{ITEM_ID}}", ajaxresult.items[k].id).replace("{{ITEM}}", ajaxresult.items[k].label);
				}
				app.model.selectedList.items[ajaxresult.items[k].id] = ajaxresult.items[k]; 
				$("#listOfItems").append(row);
				$('#listOfItems a').click(refreshItems);
			}
		}
	});
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
			    	alert('Agregada lista "' + $('#lista_nombre').val() + '" al usuario ' + app.model.userSelected.username);
			    	loadFriends(true);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar lista.');
			    }
			});
	  });
});

$(function agregarItem() {
	  $('#AgregarItem').on('click', function () {
		  var userjson = {}; //poner user posta
		  userjson['label'] = $('#item-label').val();;
		  $.ajax({
			    url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id + "/items",
			    type: "POST",
			    data : JSON.stringify(userjson),
			    contentType: 'application/json',
			    success: function(data, textStatus, jqXHR)
			    {
			    	alert('Agregado item "' + $('#item-label').val() + '" a la lista ' + app.model.selectedList.name);
			    	$('#item-label').val("");
			    	loadItems();
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar item.');
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
			    	alert('Eliminada lista "' + app.model.selectedList.name + '" al usuario ' + app.model.userSelected.username);
			    	loadFriends(true);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar lista.');
			    }
			});
	  });
});

$(function EliminarItem() {
	  $('#eliminar_Item').on('click', function (e) {
		  e.preventDefault();
		  $.ajax({ 
			    url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id + "/items/" + app.model.selectedItem.id, 
			    type: "DELETE",
			    success: function(data, textStatus, jqXHR)
			    {
			    	alert('Eliminado item "' + app.model.selectedItem.label + '" a la lista ' + app.model.selectedList.name);
			    	loadItems();
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al borrar item.');
			    }
			});
	  });
});

$(function VotarItem() {
	  $('#votar_Item').on('click', function (e) {
		  e.preventDefault();
		  $.ajax({ 
			    url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id + "/items/" + app.model.selectedItem.id + "/vote", 
			    type: "PUT",
			    success: function(data, textStatus, jqXHR)
			    {
			    	loadItems();
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al votar item.');
			    }
			});
	  });
});

function SelecLista(listId) {
	app.model.selectedList = app.model.userLists[listId];
}
function SelecItem(itemId) {
	app.model.selectedItem = app.model.selectedList.items[itemId]
}

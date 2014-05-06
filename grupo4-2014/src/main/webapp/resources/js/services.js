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
					if('Yo' != ajaxresult[k].username) {
						$("#listUsers").append(row);
					}
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
	app.model.selectedList = null;
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
			showLists(user, ajaxresult, loadItemsList);
		}
	});
}

function showLists(user, ajaxresult, loadItemsList){
	var listId = 0;
	var row = null;
	$("#listOfList").empty();
	user.lists = {};
	for(var k in ajaxresult) {
		if(loadItemsList && listId == 0){
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
		showItemsFrom(listId);
	}else{
		$("#listOfItems").empty();
		$('#labelPanelItems').text("Items de la lista...");
	}
}

function loadItems() {
	$("#listOfList a").removeClass('active');
	$(this).addClass('active');
	var userId = $(this).attr('id');
	showItemsFrom(userId);
}

function showItemsFrom(listId){
	 app.model.selectedList = app.model.userSelected.lists[listId];
	 app.model.selectedItem = null;
	 var list = app.model.userSelected.lists[listId];
	 $('#labelPanelItems').text("Items de la lista " + list.name);
	 $("#listOfItems").empty();
	 var items = list.items;
	 list.items = {}; // para usar como mapa por id en lugar de lista
	 for(var k in items) {
	 	row = $("#rowTemplateItems").html().replace("{{VOTES}}", items[k].votes).replace("{{ITEM}}", items[k].label).replace("{{ID_ITEM}}", items[k].id);
	 	$("#listOfItems").append(row);
	 	list.items[items[k].id] = items[k]; 
	 }
	 $('#listOfItems a').click(selectItem);
}

function loadlist(listId){
	$.ajax({
		type: "GET",
		url: "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id,
		success: function(ajaxresult){
			app.model.userSelected.lists[listId] = ajaxresult;
			showItemsFrom(listId);
		},
		error: function (jqXHR, textStatus, errorThrown)
	    {
			alert('Error al cargar la lista.');
	    }
	});
}

function selectItem() {
	 $("#listOfItems a").removeClass('active');
	 $(this).addClass('active');
	 var itemId = $(this).attr('id');
	 app.model.selectedItem = app.model.selectedList.items[itemId];
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
			    	$('#username').val("");
			    	loadFriends(false);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	$('#username').val("");
			    	alert('Error al agregar usuario.');
			    }
			});
	  });
});

$(function addListModal() {
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
			    	$('#lista_nombre').val("");
			    	loadListsFrom(app.model.userSelected.id,false);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	$('#lista_nombre').val("");
			    	alert('Error al agregar lista.');
			    }
			});
	  });
});

$(function addItemLaunch() {
	  $('#addItemLaunch').on('click', function () {
		  if(app.model.selectedList){
			  $('#item').modal({show:true});
		  }else{
			  $('#noListSelectedModal').modal({show:true});
		  }
	  });
});

$(function addItem() {
	  $('#AgregarItem').on('click', function () {
		  var userjson = {};
		  userjson['label'] = $('#item-label').val();
		  $.ajax({
			    url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id + "/items",
			    type: "POST",
			    data : JSON.stringify(userjson),
			    contentType: 'application/json',
			    success: function(data, textStatus, jqXHR)
			    {
			    	$('#item-label').val("");
			    	loadlist(app.model.selectedList.id);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	$('#item-label').val("");
			    	alert('Error al agregar item.');
			    }
			});
	  });
});

$(function deleteList() {
	  $('#eliminar_Lista').on('click', function () {
		  if(app.model.selectedList){
			  $.ajax({ 
				  url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id, // 12341 lista harcodeada 
				  type: "DELETE",
				  success: function(data, textStatus, jqXHR)
				  {
					  delete app.model.userSelected.lists[app.model.selectedList.id];
					  app.model.selectedList = null;
					  showLists(app.model.userSelected, app.model.userSelected.lists, false);
				  },
				  error: function (jqXHR, textStatus, errorThrown)
				  {
					  alert('Error al agregar lista.');
				  }
			  });
		  }else{
			  $('#noListSelectedModal').modal({show:true});
		  }
	  });
});

$(function deleteItem() {
	  $('#eliminar_Item').on('click', function () {
		  if(app.model.selectedItem){
			  $.ajax({ 
				  url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id + "/items/" + app.model.selectedItem.id, 
				  type: "DELETE",
				  success: function(data, textStatus, jqXHR)
				  {
					  delete app.model.selectedList.items[app.model.selectedItem.id];
					  app.model.selectedItem = null;
					  showItemsFrom(app.model.selectedList.id);
				  },
				  error: function (jqXHR, textStatus, errorThrown)
				  {
					  alert('Error al borrar item.');
				  }
			  });
		  }else{
			  $('#noItemSelectedModal').modal({show:true});
		  }
	  });
});

$(function voteItem() {
	  $('#votar_Item').on('click', function (e) {
		  if(app.model.selectedItem){
			  $.ajax({ 
				  url : "/users/" + app.model.userSelected.id + "/lists/" + app.model.selectedList.id + "/items/" + app.model.selectedItem.id + "/vote", 
				  type: "PUT",
				  success: function(data, textStatus, jqXHR)
				  {
					  app.model.selectedItem.votes++;
					  showItemsFrom(app.model.selectedList.id);
				  },
				  error: function (jqXHR, textStatus, errorThrown)
				  {
					  alert('Error al votar item.');
				  }
			  });
		  }else{
			  $('#noItemSelectedModal').modal({show:true});
		  }
	  });
});

$(".modal").on('shown.bs.modal', function() {
    $(this).find("[autofocus]:first").focus();
});
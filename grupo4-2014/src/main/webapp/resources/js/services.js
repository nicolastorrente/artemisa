function loadFriends(loadUserLists) {
	$.ajax({
	    type: "GET",
		url: "/users",
		success: function(ajaxresult){
			$("#listUsers, #myLists").empty();
			var row = null;
			for(var k in ajaxresult) {
				app.model.users[ajaxresult[k].username] = ajaxresult[k];
				if(loadUserLists && 'Yo' == ajaxresult[k].username){
					row = $("#rowTemplateUsersActive").html().replace("{{USER}}", "Mis Listas");
					row = row.replace("{{USER_ID}}", "Yo");
					$("#myLists").append(row);
				}else{
					row = $("#rowTemplateUsers").html().replace("{{USER}}", ajaxresult[k].username);
					row = row.replace("{{USER_ID}}", ajaxresult[k].username);
					$("#listUsers").append(row);
				}
			}
			$('#listUsers a, #myLists a').click(loadLists);
			if(loadUserLists){
				loadListsFrom('Yo', true);
			}
		}
	});
}

function loadLists() {
	var userName = $(this).attr('id');
	$("#listUsers a").removeClass('active');
	$("#myLists a").removeClass('active');
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
		url: "/users/" + user.id + "/lists/",
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
		url: "/users/" + app.model.userSelected.id + "/lists/" + list.id + "/items",
		success: function(ajaxresult){
			$("#listOfItems").empty();
			for(var k in ajaxresult) {
				row = $("#rowTemplateItems").html().replace("{{VOTES}}", ajaxresult[k].votes).replace("{{ITEM}}", ajaxresult[k].label);
				$("#listOfItems").append(row);
			}
		}
	});
}

$(function agregarUsuario() {
	  $('#AgregarUsuario').on('click', function () {
		  var userjson = {}; //poner user posta
		  userjson['id'] = 777;
		  userjson['username'] = $('#username').val();
		  userjson['lists'] = [];
		  $.ajax({
			    url : "/users",
			    type: "POST",
			    data : JSON.stringify(userjson),
			    contentType: 'application/json',
			    success: function(data, textStatus, jqXHR)
			    {
			    	alert('Usuario "' + $('#username').val() + '" creado.');
			    	loadFriends(true);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar usuario.');
			    }
			});
	  });
});

$(function agregarLista() {
	  $('#AgregarLista').on('click', function () {
		  var userjson = {}; //poner user posta
		  userjson['id'] = 777;
		  userjson['name'] = $('#lista_nombre').val();
		  userjson['items'] = [];
		  $.ajax({
			    url : "/users/" + app.model.userSelected.id + "/lists",
			    type: "POST",
			    data : JSON.stringify(userjson),
			    contentType: 'application/json',
			    success: function(data, textStatus, jqXHR)
			    {
			    	alert('Agregada lista "' + $('#lista_nombre').val() + '" al usuario ' + app.model.userSelected.id);
			    	loadFriends(app.model.userSelected.id);
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
			    url : "/users/" + app.model.userSelected.id + "/lists/" + 12341, // 12341 lista harcodeada 
			    type: "DELETE",
			    success: function(data, textStatus, jqXHR)
			    {
			    	alert('Eliminada lista "' + 'harcodeada' + '" al usuario ' + app.model.userSelected.id);
			    	loadFriends(app.model.userSelected.id);
			    },
			    error: function (jqXHR, textStatus, errorThrown)
			    {
			    	alert('Error al agregar lista.');
			    }
			});
	  });
});
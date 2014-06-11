var access_token;
var user_id;
var user_name;

function statusChangeCallback(response) {
	if (response.status === 'connected') {
		testAPI();
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '227825597416489',
		cookie : true,
		xfbml : true,
		version : 'v2.0'
	});
	
	FB.login(function() {
	}, {
		scope : 'user_friends,publish_actions,email,user_likes'
	});

	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});

};

(function(d, s, id) {
	var js, fjs = d.getElementsByTagName(s)[0];
	if (d.getElementById(id))
		return;
	js = d.createElement(s);
	js.id = id;
	js.src = "//connect.facebook.net/en_US/sdk.js";
	fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

function testAPI() {
	FB.api('/me', function(response) {
		access_token = FB.getAuthResponse()['accessToken'];
		user_name = response.name;
		user_id = response.id;
		login(user_id, user_name, access_token);
	});
};

function publishToWall(){
	FB.ui({
		method : 'feed',
		name : 'Mirá la lista que cree: ' + $('#lista_nombre').val(),
		caption : 'TACS',
		description : ('UTN - FRBA - 2014'),
		link : 'http://www.frba.utn.edu.ar/',
		picture : 'http://www.fbrell.com/public/f8.jpg'
	}, function(response) {
		if (response && response.post_id) {
			$('#alertMuro').slideDown("slow");
			refreshListModal();
		} else {
			// alert('No se pudo publicar :(.'); queda feo mostrar esto
			refreshListModal();
		}
	});
}

function publishVote(){
	FB.ui({
		method : 'feed',
		name : 'Vote un item: ', //app.model.selectedItem.id
		caption : 'TACS',
		description : ('UTN - FRBA - 2014'),
		link : 'http://www.frba.utn.edu.ar/',
		picture : 'http://www.fbrell.com/public/f8.jpg'
	}, function(response) {
		if (response && response.post_id) {
			// Exito
			refreshListModal();
		} else {
			// Fracaso
		}
	});
}
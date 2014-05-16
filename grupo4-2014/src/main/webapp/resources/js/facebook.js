var access_token;
var user_id;
var user_name;

function statusChangeCallback(response) {
	if (response.status === 'connected') {
		testAPI();
	} else if (response.status === 'not_authorized') {
		document.getElementById('status').innerHTML = 'Please log '
				+ 'into this app.';
	} else {
		document.getElementById('status').innerHTML = 'Please log '
				+ 'into Facebook.';
	}
}

function checkLoginState() {
	FB.getLoginStatus(function(response) {
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function() {
	FB.init({
		appId : '235401943325672',
		cookie : true,
		xfbml : true,
		version : 'v2.0'
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
		document.getElementById('status').innerHTML = 'Gracias por loguearte, '
				+ response.name;
		alert(user_name + " tu token es:\n\n" + access_token + "\n\nY tu id: "
				+ user_id);
	});
}

$('#publicar_Muro').on('click', function () {
	alert("todavia no esta activado");
   /* FB.ui(
    	      {
    	       method: 'feed',
    	       name: 'The Facebook SDK for Javascript',
    	       caption: 'Bringing Facebook to the desktop and mobile web',
    	       description: (
    	          'A small JavaScript library that allows you to harness ' +
    	          'the power of Facebook, bringing the user\'s identity, ' +
    	          'social graph and distribution power to your site.'
    	       ),
    	       link: 'https://developers.facebook.com/docs/reference/javascript/',
    	       picture: 'http://www.fbrell.com/public/f8.jpg'
    	      },
    	      function(response) {
    	        if (response && response.post_id) {
    	          alert('Post was published.');
    	        } else {
    	          alert('Post was not published.');
    	        }
    	      }
    	    );*/
});
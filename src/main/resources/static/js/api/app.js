var apiclient = apiclient;
var app = (function () {
	
	var prueba = function(){
		alert("esto es una prueba")
	}
	    
	var loginUser = function(username, pass) {
		var newUser = {
			correo : username,
			nombre : "",
			passwd : pass,
			invitaciones: [],
			notificaciones : []
		};
		apiclient.loginUser(JSON.stringify(newUser), username);
	}
	
	
	var addUser = function(username, nombre, passwd, repPasswd) {
		var newUser = {
			correo : username,
			nombre : nombre,
			passwd : passwd,
			invitaciones: [],
			notificaciones : []
		};
		apiclient.addUser(JSON.stringify(newUser));
	}
	
	
	var addProject = function(){
		apiclient.getUser(postProject);
	}

	var postProject = function(resp) {
		var newProject = {
			id : 1,
			nombre : $("#pnombre").val(),
			descripcion : $("#pdescripcion").val(),
			creador : resp,
			participantes : [resp],
			mensajes : []
		};
		apiclient.addProject(JSON.stringify(newProject));
	}
	
	
	var onload = function(){
		apiclient.getUsers(getTable);
	}
    
    return {
    	loginUser : loginUser,
    	addUser : addUser,
		prueba: prueba,
		addProject : addProject
    };
})();
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
		apiclient.loginUser(JSON.stringify(newUser));
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
	
	
	var addProject = function(id, nombre, desc, usuario_creador) {
		var newProject = {
			id : username,
			nombre : nombre,
			descripcion : passwd,
			creador : usuario_creador,
			participantes : [],
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
    	prueba: prueba
    };
})();
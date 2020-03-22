var apiclient = apiclient;
var apiprofile = (function () {
    var appUrl = "http://localhost:8080/treecore";
	var showInvitaciones = function(resp){
		for(var i = 0; i < resp.length; i++) {
			putInvitaciones(resp[i].proyecto, resp[i].nombreProyecto, resp[i].remitente); 
		};
		$("#numero").text(resp.length);
		$("#anuncio").text("Tinene " + resp.length + " invitaciones");
	}
	var showProyectos = function(resp){
		for(var i = 0; i < resp.length; i++) {
			putProyectos(resp[i].nombre, resp[i].id); 
		};
	}
	var showNotificaciones = function(resp){
		for(var i = 0; i < resp.length; i++) {
			putNotificaciones(resp[i].informacion, resp[i].fecha); 
		};
	}
	var showUsuario = function(resp){
		$("#nombre").text(resp.nombre);
	}
	var getInvitaciones = function(){
		apiclient.getInvitaciones(showInvitaciones);
	}
	var getProyectos = function(){
		apiclient.getProyectosUsuario(showProyectos);
	}
	var getNotificaciones = function(){
		apiclient.getNotificaciones(showNotificaciones);
	}
	var getUsuario = function(){
		apiclient.getUser(showUsuario);
	}
	var putInvitaciones = function(proyecto, proyectoNombre, remitente){
		$("#invitaciones").append(
		'<li> <a href=http://localhost:8080/project.html>' + remitente + '<span class="message">' + remitente + ' te invita a participar en el proyecto ' + proyectoNombre +  '</span> </a><button type="button" class="btn btn-success">Aceptar</button> <button type="button" class="btn btn-danger">Rechazar </button> </li>'
		);
	}
	var putProyectos = function(proyecto, id){
		$("#proyectos").append(
		'<tr><td><a href=http://localhost:8080/project.html >' + proyecto + '</a></td></tr>'
		);
	}
	var putNotificaciones = function (informacion, fecha){
		var f = fecha.slice(0,10);
		$("#notificaciones").append(
		'<div class="alert alert-info"><b>' + informacion + ' </b>' + f + '</div>'
		);
	}
	var crearProyecto = function(){
		location.replace("http://localhost:8080/addProject.html")
	}
	var verificar = function(){
		if (sessionStorage.correo == null){
			location.replace("http://localhost:8080/login.html")
		}
	}
	var salir = function(){
		sessionStorage.removeItem('correo');
		location.replace("http://localhost:8080/login.html")
	}
    return {
		verificar : verificar,
		invitaciones : getInvitaciones,
		proyectos : getProyectos,
		notificaciones : getNotificaciones,
		usuario : getUsuario,
		crear : crearProyecto,
		salir : salir
    };
})();
var apiclient = apiclient;
var invitacion;
var apiprofile = (function () {

	var aceptarInvitacion = function (p, r, nombre) {
		var newInvitacion = {
			remitente: r,
			receptor: localStorage.correo,
			proyecto: p
		};
		apiclient.getProjectTeam(p, notificarNuevoColaborador, nombre);
		apiclient.aceptarInvitacion(JSON.stringify(newInvitacion), nombre, putProyectos);
		apiclient.eliminarInvitacion(JSON.stringify(newInvitacion));
		deleteInvitacion(p);
		stompClient.send("/treecore/aceptarInvitacion", {}, JSON.stringify(newInvitacion));
	}

	var eliminarInvitacion = function (p, r) {
		var newInvitacion = {
			remitente: r,
			receptor: localStorage.correo,
			proyecto: p
		};
		apiclient.eliminarInvitacion(JSON.stringify(newInvitacion));
		deleteInvitacion(p);
	}

	var deleteInvitacion = function (p) {
		var str = '#inv' + p;
		console.log(str);
		$(str).remove();
		var act = parseInt($("#numero").text()) - 1;
		$("#numero").text(act);
		$("#anuncio").text("Tiene " + act + " invitaciones");
	}

	var notificarNuevoColaborador = function (lista, nombre) {
		console.log(lista);
		var notificacion = new Notificacion("El usuario " + localStorage.correo + " se ha unido al proyecto " + nombre);
		notificacion = JSON.stringify(notificacion);
		for (var i = 0; i < lista.length; i++) {
			if (lista[i].correo != localStorage.correo) {
				apiclient.addNotificacion(notificacion, lista[i].correo, publicarNotificacion);
			}
		}
	}

	var publicarNotificacion = function (notificacion, correo) {
		stompClient.send("/treecore/notificacion." + correo, {}, notificacion);
	}

	class Notificacion {
		constructor(informacion) {
			this.id = 0;
			this.fecha = null;
			this.informacion = informacion;
		}
	}

	var showInvitaciones = function (resp) {
		for (var i = 0; i < resp.length; i++) {
			putInvitaciones(resp[i]);
		};
		$("#numero").text(resp.length);
		$("#anuncio").text("Tiene " + resp.length + " invitaciones");
	}
	var showProyectos = function (resp) {
		for (var i = 0; i < resp.length; i++) {
			putProyectos(resp[i].nombre, resp[i].id);
		};
	}
	var showNotificaciones = function (resp) {
		for (var i = 0; i < resp.length; i++) {
			putNotificaciones(resp[i].informacion, resp[i].fecha);
		};
	}
	var showUsuario = function (resp) {
		$("#nombre").text(resp.nombre);
	}
	var getInvitaciones = function () {
		apiclient.getInvitaciones(showInvitaciones);
	}
	var getProyectos = function () {
		apiclient.getProyectosUsuario(showProyectos);
	}
	var getNotificaciones = function () {
		apiclient.getNotificaciones(showNotificaciones);
	}
	var getUsuario = function () {
		apiclient.getUser(showUsuario);
	}
	var putInvitaciones = function (resp) {
		proyectoNombre = resp.nombreProyecto;
		remitente = resp.remitente;
		$("#invitaciones").append(
			'<div id="inv' + resp.proyecto + '" > <li>  <a href=/tree.html>' + remitente + '<span class="message">' + remitente + ' te invita a participar en el proyecto ' + proyectoNombre + '</span> </a><button type="button" class="btn btn-success" onclick="apiprofile.aceptarInvitacion(\'' + resp.proyecto + "' , '" + remitente + "' , '" + proyectoNombre + "' , '" + '\')" >Aceptar</button> <button type="button" class="btn btn-danger" onclick="apiprofile.eliminarInvitacion(\'' + resp.proyecto + "' , '" + remitente + "' , '" + '\')" >Rechazar </button> </li> </div>'
		);
	}
	var putProyectos = function (proyecto, id) {
		$("#proyectos").append(
			'<tr><td><a onclick=apiprofile.proyecto(' + id + ') >' + proyecto + '</a></td></tr>'
		);
	}
	var putNotificaciones = function (informacion, fecha) {
		var f = fecha.slice(0, 10);
		$("#notificaciones").append(
			'<div class="alert alert-info"><b>' + informacion + ' </b>' + f + '</div>'
		);
	}
	var crearProyecto = function () {
		location.replace("/addProject.html")
	}
	var verificar = function () {
		if (localStorage.correo == null) {
			location.replace("/login.html")
		}
		else {
			conectar()
		}
	}
	var salir = function () {
		localStorage.removeItem('correo');
		location.replace("/login.html")
	}

	var proyecto = function (id) {
		console.log(id);
		sessionStorage.proyecto = id;
		location.replace("/tree.html")
	}


	var conectar = function () {
		var socket = new SockJS('/stompendpoint');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			stompClient.subscribe('/project/user/invitacion.' + localStorage.correo, function (eventbody) {
				var invitacion = JSON.parse(eventbody.body);
				$("#invitaciones").append(
					'<div id="inv' + invitacion.proyecto + '" > <li> <a href=/tree.html>' + invitacion.remitente + '<span class="message">' + invitacion.remitente + ' te invita a participar en el proyecto ' + invitacion.nombreProyecto + '</span> </a><button type="button" class="btn btn-success" onclick="apiprofile.aceptarInvitacion(\'' + invitacion.proyecto + "' , '" + invitacion.remitente + "' , '" + invitacion.nombreProyecto + "' , '" + '\')" >Aceptar</button> <button type="button" class="btn btn-danger" onclick="apiprofile.eliminarInvitacion(\'' + invitacion.proyecto + "' , '" + invitacion.remitente + "' , '" + '\')" >Rechazar </button> </li> </div>'
				);
				var act = parseInt($("#numero").text()) + 1;
				$("#numero").text(act);
				$("#anuncio").text("Tiene " + act + " invitaciones");
			});
			stompClient.subscribe('/project/user/notificacion.' + localStorage.correo, function (eventbody) {
				var notificacion = JSON.parse(eventbody.body);
				console.log(notificacion);
				var f = notificacion.fecha.slice(0, 10);
				console.log("publicar notificacion");
				$('<div class="alert alert-info"><b>' + notificacion.informacion + ' </b>' + f + '</div>').insertAfter("#notificacionTitle");
			});

		});
	}

	return {
		verificar: verificar,
		invitaciones: getInvitaciones,
		proyectos: getProyectos,
		notificaciones: getNotificaciones,
		usuario: getUsuario,
		crear: crearProyecto,
		salir: salir,
		eliminarInvitacion: eliminarInvitacion,
		aceptarInvitacion: aceptarInvitacion,
		proyecto: proyecto
	};
})();

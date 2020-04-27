var apiclient = apiclient;
var apiprofile = (function () {

	var aceptarInvitacion = function (p, r) {
		var newInvitacion = {
			remitente: r,
			receptor: localStorage.correo,
			proyecto: p,
			receptor: localStorage.correo
		};
		apiclient.aceptarInvitacion(JSON.stringify(newInvitacion));
		apiclient.eliminarInvitacion(JSON.stringify(newInvitacion));

	}

	var eliminarInvitacion = function (p, r) {
		var newInvitacion = {
			remitente: r,
			receptor: localStorage.correo,
			proyecto: p,
			receptor: localStorage.correo
		};
		apiclient.eliminarInvitacion(JSON.stringify(newInvitacion));
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
			'<li> <a href=/tree.html>' + remitente + '<span class="message">' + remitente + ' te invita a participar en el proyecto ' + proyectoNombre + '</span> </a><button type="button" class="btn btn-success" onclick="apiprofile.aceptarInvitacion(\'' + resp.proyecto + "' , '" + remitente + "' , '" + '\')" >Aceptar</button> <button type="button" class="btn btn-danger" onclick="apiprofile.eliminarInvitacion(\'' + resp.proyecto + "' , '" + remitente + "' , '" + '\')" >Rechazar </button> </li>'
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
		else{
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
					'<li> <a href=/tree.html>' + invitacion.remitente + '<span class="message">' + invitacion.remitente + ' te invita a participar en el proyecto ' + invitacion.nombreProyecto + '</span> </a><button type="button" class="btn btn-success" onclick="apiprofile.aceptarInvitacion(\'' + invitacion.proyecto + "' , '" + invitacion.remitente + "' , '" + '\')" >Aceptar</button> <button type="button" class="btn btn-danger" onclick="apiprofile.eliminarInvitacion(\'' + invitacion.proyecto + "' , '" + invitacion.remitente + "' , '" + '\')" >Rechazar </button> </li>'
				);
				var act = parseInt($("#numero").text()) + 1;
				console.log(act);
				$("#numero").text(act);
				$("#anuncio").text("Tiene " + act  + " invitaciones");
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
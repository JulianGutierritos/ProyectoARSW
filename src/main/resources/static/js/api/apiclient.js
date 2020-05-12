var apiclient = (function () {
	var appUrl = "https://treecore.herokuapp.com/treecore/v1";
	//var appUrl = "http://localhost:8080/treecore/v1";
	return {

		getUser: function (callback) {
			jQuery.ajax({
				url: appUrl + "/users/" + localStorage.correo,
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		getInvitaciones: function (callback) {
			jQuery.ajax({
				url: appUrl + "/users/" + localStorage.correo + "/invitations",
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		getProyectosUsuario: function (callback) {
			jQuery.ajax({
				url: appUrl + "/users/" + localStorage.correo + "/projects",
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		getNotificaciones: function (callback) {
			jQuery.ajax({
				url: appUrl + "/users/" + localStorage.correo + "/notifications",
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		loginUser: function (user, username) {
			var postRequest = $.ajax({
				url: appUrl + "/login",
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					localStorage.correo = username;
					location.replace("/profile.html")
				},
				function () {
					app.manageLoginError();
				}
			);
		},

		aceptarInvitacion: function (invitacion) {
			var postRequest = $.ajax({
				url: appUrl + "/projects/team",
				type: 'PUT',
				data: invitacion,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					//apiprofile.getProyectos();
				},
				function () {
					alert("failure acept");
				}
			);
		},

		eliminarInvitacion: function (invitacion) {
			var postRequest = $.ajax({
				url: appUrl + "/users/invitations",
				type: 'DELETE',
				data: invitacion,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					//location.reload();
				},
				function () {
					alert("failure delete");
				}
			);
		},

		addUser: function (user, callback) {
			var postRequest = $.ajax({
				url: appUrl,
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					location.replace("/login.html")
				},
				function (ans) {
					callback(ans);
					//alert("sign up failed");
				}
			);
		},

		addInvitacion: function (invitacion, callback) {
			var postRequest = $.ajax({
				url: appUrl + "/user/invitation",
				type: 'POST',
				data: invitacion,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					callback(invitacion);
					alert("Se ha enviado la invitacion");
				},
				function () {
					alert(postRequest.responseText);
				}
			);
		},

		addNotificacion: function (notificacion, usuario, callback) {
			var postRequest = $.ajax({
				url: appUrl + "/users/" + usuario + "/notification",
				type: 'POST',
				data: notificacion,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					callback(notificacion, usuario);
				},
				function () {
					alert(postRequest.responseText);
				}
			);
		},

		getProjects: function (callback) {
			jQuery.ajax({
				url: appUrl + "/projects",
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		get: function (callback) {
			jQuery.ajax({
				url: appUrl + "/projects",
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		getProject: function (id, callback) {
			jQuery.ajax({
				url: appUrl + "/projects/" + id,
				type: "GET",
				success: function (response) {
					callback(response);
				}
			});
		},
		getProjectTeam: function (id, callback, n) {
			jQuery.ajax({
				url: appUrl + "/projects/" + id + "/team",
				type: "GET",
				success: function (response) {
					callback(response, n);
				}
			});
		},
		getProjectRamas: function (id, callback) {
			jQuery.ajax({
				url: appUrl + "/projects/" + id + "/ramas",
				type: "GET",
				success: function (response) {
					callback(response);
				}
			});
		},

		getAutenticado: function () {
			return localStorage.correo;
		},

		addProject: function (project) {
			var postRequest = $.ajax({
				url: appUrl + "/projects",
				type: 'POST',
				data: project,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					alert("successful project creation");
					location.reload();
				},
				function () {
					alert("failed project creation");
				}
			);
		},

		getProjectByName: function (projectName, callback) {
			jQuery.ajax({
				url: appUrl + "/project/" + projectName,
				type: "GET",
				success: function (response) {
					callback(response);
				}
			});
		},

		getRoot: function (idProyecto, idRama, callback) {
			jQuery.ajax({
				url: appUrl + "/projects/" + idProyecto + "/rama/" + idRama,
				type: "GET",
				success: function (response) {
					callback(response);
				}
			});
		},

		addProjectRoot: function (projectId, root, callback) {
			var postRequest = $.ajax({
				url: appUrl + "/projects/" + projectId + "/ramas",
				type: 'POST',
				data: root,
				contentType: "application/json"
			});
			postRequest.then(
				function () {
					callback(postRequest.responseText);
				},
				function () {
					alert("failed root creation");
				}
			);
		},

		deleteRoot:function (rama){
			console.log(rama);
			var delRequest = $.ajax({
				url: appUrl + "/delete/project/rama",
				type: 'DELETE',
				data: rama,
				contentType: "application/json"
			});
			delRequest.then(
				function () {
					location.reload();
				},
				function () {
					alert("failure delete");
				}
			);
		},

		deleteProject:function (proyecto){
			var delRequest = $.ajax({
				url: appUrl + "/delete/project",
				type: 'DELETE',
				data: proyecto,
				contentType: "application/json"
			});
			delRequest.then(
				function () {
					console.log("success")
				},
				function () {
					alert("failure delete");
				}
			);
		},
		
		getLastId: function (callback) {
			jQuery.ajax({
				url: appUrl + "/ramas/lastId",
				type: "GET",
				success: function (respuesta) {
					callback(respuesta);
				}
			});
		},

		projectSinc:function(){
			
		}

	};
})();

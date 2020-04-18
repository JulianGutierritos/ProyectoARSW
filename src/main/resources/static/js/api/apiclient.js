var apiclient = (function () {
	//var appUrl = "https://treecore.herokuapp.com/treecore";
	var appUrl = "http://localhost:8080/treecore";
    return {
    	
        getUser: function (callback) {
        	jQuery.ajax({
        		url: appUrl + "/users/" + sessionStorage.correo,
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
        },
		
		getInvitaciones: function (callback) {
        	jQuery.ajax({
        		url: appUrl + "/users/" + sessionStorage.correo + "/invitations",
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
        },
		
		getProyectosUsuario: function (callback) {
        	jQuery.ajax({
        		url: appUrl + "/users/" + sessionStorage.correo + "/projects",
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
        },
		
		getNotificaciones: function (callback) {
        	jQuery.ajax({
        		url: appUrl + "/users/" + sessionStorage.correo + "/notifications",
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
        },

        loginUser : function (user, username){
        	var postRequest=$.ajax({
				url:  appUrl+"/login",
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					sessionStorage.correo = username;
					location.replace("/profile.html");
				},
				function(){
					alert("failure login");
				}
			);
		},
		
		aceptarInvitacion : function (invitacion){
        	var postRequest=$.ajax({
				url:  appUrl+"/projects/team",
				type: 'PUT',
				data: invitacion,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					apiprofile.getProyectos();
				},
				function(){
					alert("failure acept");
				}
			);
		},
		
		eliminarInvitacion : function (invitacion){
        	var postRequest=$.ajax({
				url:  appUrl+"/users/invitations",
				type: 'DELETE',
				data: invitacion,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					location.reload(); 
				},
				function(){
					alert("failure delete");
				}
			);
        },
        
        addUser : function (user){
        	var postRequest=$.ajax({
				url:  appUrl,
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					alert("successful sign up");
					location.reload(); 
				},
				function(){
					alert("sign up failed");
				}
			);
        },
        
        getProjects: function (callback) {
        	jQuery.ajax({
        		url: appUrl+"/projects",
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
        },
		
		get: function (callback) {
        	jQuery.ajax({
        		url: appUrl+"/projects",
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
		},
		
		getProject: function (id, callback) {
        	jQuery.ajax({
        		url: appUrl+"/projects/" + id,
        		type: "GET",
        		success: function(response) {
        			callback(response);
        		}
        	});
        },
		
		getAutenticado : function(){
			return sessionStorage.correo;
		},
        
        addProject : function (project){
        	var postRequest=$.ajax({
				url:  appUrl+"/projects",
				type: 'POST',
				data: project,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					alert("successful project creation");
					location.reload(); 
				},
				function(){
					alert("failed project creation");
				}
			);
        }
        
    };
})();
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
	
	
	var getTable = function (users) {
		
        $("#usersTableBody").empty();
        users.map(function (user) {
            $("#usersTableBody").append(
                "<tr> " +
                "<td>" + user.name + "</td> " +
                "<td>" + user.birthday + "</td> " +
                "<td>" + user.document + "</td> " +
                "<td>" + user.phone + "</td> " +
                "<td>" + user.email + "</td> " +
                "</tr>"
            );
        });
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
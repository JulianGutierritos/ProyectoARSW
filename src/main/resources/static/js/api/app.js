var apiclient = apiclient;
var app = (function () {

	var prueba = function () {
		alert("esto es una prueba")
	}

	var passHash = function (objPassId) {
		var pwdObj = document.getElementById(objPassId);
		var hashObj = new jsSHA("SHA-512", "TEXT", { numRounds: 1 });
		hashObj.update(pwdObj.value);
		var hash = hashObj.getHash("HEX");
		pwdObj.value = hash;
		return hash;

	}

	var loginUser = function (username, pass) {
		passHash = passHash('pass');

		var newUser = {
			correo: username,
			nombre: "",
			passwd: passHash,
			invitaciones: [],
			notificaciones: []
		};
		apiclient.loginUser(JSON.stringify(newUser), username);
	}



	var addUser = function (username, nombre, passwd, repPasswd) {
		if (passwd == repPasswd) {
			passwdHash = passHash('passwd');
			passHash('repPasswd');

			var newUser = {
				correo: username,
				nombre: nombre,
				passwd: passwdHash,
				invitaciones: [],
				notificaciones: []
			};
			apiclient.addUser(JSON.stringify(newUser));
		}

		else {
			alert("Las contraseñas no coinciden")
		}
	}


	var addProject = function () {
		apiclient.getUser(postProject);
	}

	var postProject = function (resp) {
		var newProject = {
			id: 1,
			nombre: $("#pnombre").val(),
			descripcion: $("#pdescripcion").val(),
			creador: resp,
			participantes: [resp],
			mensajes: []
		};
		apiclient.addProject(JSON.stringify(newProject));
	}


	var onload = function () {
		apiclient.getUsers(getTable);
	}

	var verificar = function () {
		if (localStorage.correo != null) {
			location.replace("/profile.html")
		}
	}

	openNav = function () {
		document.getElementById("mySidenav").style.width = "250px";
	}

	closeNav = function () {
		document.getElementById("mySidenav").style.width = "0";
	}

	return {
		loginUser: loginUser,
		addUser: addUser,
		prueba: prueba,
		addProject: addProject,
		verificar: verificar,
		openNav: openNav,
		closeNav: closeNav
	};
})();
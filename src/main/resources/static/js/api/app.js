var apiclient = apiclient;
var app = (function () {

	var prueba = function () {
		alert("esto es una prueba")
	}

	var clearField = function (object) {
		object.value = "";
	}

	var passHash = function (objPassId) {
		var pwdObj = document.getElementById(objPassId);
		var hashObj = new jsSHA("SHA-512", "TEXT", { numRounds: 1 });
		hashObj.update(pwdObj.value);
		var hash = hashObj.getHash("HEX");
		clearField(pwdObj);

		return hash;

	}

	var loginUser = function (username) {
		clearField(document.getElementById('username'));
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


	var validarEmail = function (valor) {
		var patron = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;

		if (valor.search(patron) == 0) {
			return true;
		}
		return false;

	}



	var addUser = function (username, nombre, passwd, repPasswd) {
		validEmail = validarEmail(username);

		if (validEmail) {

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
				apiclient.addUser(JSON.stringify(newUser), manageRegError);
			}

			else {
				alert("Las contraseñas no coinciden")
			}
		}

		else {
			manageRegMailError();
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

	var openNav = function () {
		document.getElementById("mySidenav").style.width = "20%";
	}

	var closeNav = function () {
		document.getElementById("mySidenav").style.width = "0";
	}

	var hiddenElem = function (elemId) {
		var el = document.getElementById(elemId);
		el.style.display = (el.style.display == 'none') ? 'block' : 'none';
	}

	var hiddenErrorLoginMessage = function () {
		hiddenElem("errorMesLog");
	}

	var hiddenRegMessage = function () {
		hiddenElem("mesReg");
	}

	var manageErrorPanel = function (panelDiv, panelPos) {
		$(panelDiv).dialog({
			autoOpen: false,
			hide: "puff",
			show: "slide",
			height: 100,
			modal: true,
			close: function () {
				location.reload();
			},
			overlay: {
				opacity: 0.5,
				background: "black"
			}
		});

		$(panelDiv).dialog({
			position: {
				my: 'top',
				at: 'top',
				of: $(panelPos)
			}
		});

		$(panelDiv).dialog("open");
	}

	var manageLoginError = function () {
		manageErrorPanel("#errorMesLog", '#errorMesLogPos');
	}

	var manageRegMailError = function () {
		changeRegErrorDivText("Invaid Mail");
		manageErrorPanel("#mesReg", '#errorMesRegPos');
	}

	var manageRegError = function (ans) {
		changeRegErrorDivText(ans["responseText"]);
		manageErrorPanel("#mesReg", '#errorMesRegPos');
	}

	var changeRegErrorDivText = function (message) {
		$('#mesReg').html(message);
	}

	return {
		loginUser: loginUser,
		addUser: addUser,
		prueba: prueba,
		addProject: addProject,
		verificar: verificar,
		openNav: openNav,
		closeNav: closeNav,
		hiddenErrorLoginMessage: hiddenErrorLoginMessage,
		hiddenRegMessage: hiddenRegMessage,
		manageLoginError: manageLoginError,
		manageRegError: manageRegError,
		print: print
	};
})();
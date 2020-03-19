var component = (function() {
	
	listaElements=[]

	var dibRect = function() {

		var mousePosition;
		var offset = [ 0, 0 ];
		var div;
		var isDown = false;
		var xInicio = (screen.width / 2) + "px";
		var yInicio = (screen.height / 2) + "px";

		div = document.createElement("startPoint");
		div.style.position = "absolute";
		div.style.left = xInicio;
		div.style.top = yInicio;
		div.style.width = "100px";
		div.style.height = "100px";
		div.style.background = "red";
		div.style.color = "blue";

		document.body.appendChild(div);

		div.addEventListener('mousedown', function(e) {
			isDown = true;
			offset = [ div.offsetLeft - e.clientX, div.offsetTop - e.clientY ];
		}, true);

		document.addEventListener('mouseup', function() {
			isDown = false;
		}, true);

		document.addEventListener('mousemove', function(event) {
			event.preventDefault();
			if (isDown) {
				mousePosition = {

					x : event.clientX,
					y : event.clientY

				};
				div.style.left = (mousePosition.x + offset[0]) + 'px';
				div.style.top = (mousePosition.y + offset[1]) + 'px';
			}
		}, true);
	};

	function dibujarElementoSVG(objeto, nombreElemento, elementoPadre) {
		let elemento = document.createElementNS(SVG_NS, nombreElemento);
		for ( var nombre in objeto) {
			if (objeto.hasOwnProperty(nombre)) {
				elemento.setAttributeNS(null, nombre, objeto[nombre]);
			}
		}
		elementoPadre.appendChild(elemento);
		// opcional: la función devuelve el elemento creado para poder
		// utilizarlo más tarde
		
		
		return elemento;
	}

	return {

		addComponent : dibRect,
		dibujarSVG : dibujarElementoSVG
	}

})();

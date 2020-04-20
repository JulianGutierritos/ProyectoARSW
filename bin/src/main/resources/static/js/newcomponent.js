var newcomponent = (function () {
	
	var dibujarUnaEstrella = function dibujarUnaEstrella() {

		var canvas = document.getElementById("lienzo2");


		if (canvas && canvas.getContext) {
			var ctx = canvas.getContext("2d");
			if (ctx) {
				var arrastrar = false;
				var delta = new Object();
				var L = 5;
				var paso = 2;
				var R = 100;
				var X = canvas.width / 2;
				var Y = canvas.height / 2;

				var dibujarUnaEstrella = function dibujarUnaEstrella(R, L, paso, X, Y) {

					ctx.fillStyle = "#6ab150";
					var estrella = L / paso
					var rad = (2 * Math.PI) / estrella;

					ctx.beginPath();
					for (var i = 0; i < L; i++) {
						x = X + R * Math.cos(rad * i);
						y = Y + R * Math.sin(rad * i);
						ctx.lineTo(x, y);
					}
					ctx.closePath();
					ctx.fill();
				};

				dibujarUnaEstrella(R, L, paso, X, Y);

				
			}
		}
	};
	
    return {
    	dibujarUnaEstrella : dibujarUnaEstrella
    };
    
})();
var apiclient = apiclient;
var stompClient = null;
var proyecto = null;
var map = (function () {

	var currentRootId;
	var currentRootParentId;
	var currentRootParent;
	var currentProject;
	var currentUser;
	var currentRoot;

	function init() {
		var $ = go.GraphObject.make;

		myDiagram =
			$(go.Diagram, "myDiagramCanvas",
				{
					// when the user drags a node, also move/copy/delete the whole subtree starting with that node
					"commandHandler.copiesTree": true,
					"commandHandler.copiesParentKey": true,
					"commandHandler.deletesTree": true,
					"draggingTool.dragsTree": true,
					"undoManager.isEnabled": true
				});

		// when the document is modified, add a "*" to the title and enable the "Save" button
		myDiagram.addDiagramListener("Modified", function (e) {
			var button = document.getElementById("SaveButton");
			if (button) button.disabled = !myDiagram.isModified;
			var idx = document.title.indexOf("*");
			if (myDiagram.isModified) {
				if (idx < 0) document.title += "*";
			} else {
				if (idx >= 0) document.title = document.title.substr(0, idx);
			}
		});

		// a node consists of some text with a line shape underneath
		myDiagram.nodeTemplate =
			$(go.Node, "Vertical",
				{ selectionObjectName: "TEXT" },
				$(go.TextBlock,
					{
						name: "TEXT",
						minSize: new go.Size(30, 15),
						editable: true
					},
					// remember not only the text string but the scale and the font in the node data
					new go.Binding("text", "text").makeTwoWay(),
					new go.Binding("scale", "scale").makeTwoWay(),
					new go.Binding("font", "font").makeTwoWay()),
				$(go.Shape, "LineH",
					{
						stretch: go.GraphObject.Horizontal,
						strokeWidth: 3, height: 3,
						// this line shape is the port -- what links connect with
						portId: "", fromSpot: go.Spot.LeftRightSides, toSpot: go.Spot.LeftRightSides
					},
					new go.Binding("stroke", "brush"),
					// make sure links come in from the proper direction and go out appropriately
					new go.Binding("fromSpot", "dir", function (d) { return spotConverter(d, true); }),
					new go.Binding("toSpot", "dir", function (d) { return spotConverter(d, false); })),
				// remember the locations of each node in the node data
				new go.Binding("location", "loc", go.Point.parse).makeTwoWay(go.Point.stringify),
				// make sure text "grows" in the desired direction
				new go.Binding("locationSpot", "dir", function (d) { return spotConverter(d, false); })
			);

		// selected nodes show a button for adding children
		myDiagram.nodeTemplate.selectionAdornmentTemplate =
			$(go.Adornment, "Spot",
				$(go.Panel, "Auto",
					// this Adornment has a rectangular blue Shape around the selected node
					$(go.Shape, { fill: null, stroke: "dodgerblue", strokeWidth: 3 }),
					$(go.Placeholder, { margin: new go.Margin(4, 4, 0, 4) })
				),
				// and this Adornment has a Button to the right of the selected node
				$("Button",
					{
						alignment: go.Spot.Right,
						alignmentFocus: go.Spot.Left,
						click: addNodeAndLink  // define click behavior for this Button in the Adornment
					},
					$(go.TextBlock, "+",  // the Button content
						{ font: "bold 8pt sans-serif" })
				),
				$("Button",
					{
						alignment: go.Spot.Right,
						alignmentFocus: go.Spot.Right,
						click: currentRoot,
					},
					$(go.TextBlock, "me",  // the Button content
						{ font: "bold 8pt sans-serif" })
				)
			);

		// the context menu allows users to change the font size and weight,
		// and to perform a limited tree layout starting at that node
		myDiagram.nodeTemplate.contextMenu =
			$("ContextMenu",
				$("ContextMenuButton",
					$(go.TextBlock, "Bigger"),
					{ click: function (e, obj) { changeTextSize(obj, 1.1); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Smaller"),
					{ click: function (e, obj) { changeTextSize(obj, 1 / 1.1); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Bold/Normal"),
					{ click: function (e, obj) { toggleTextWeight(obj); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Copy"),
					{ click: function (e, obj) { e.diagram.commandHandler.copySelection(); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Delete"),
					{ click: function (e, obj) { e.diagram.commandHandler.deleteSelection(); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Undo"),
					{ click: function (e, obj) { e.diagram.commandHandler.undo(); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Redo"),
					{ click: function (e, obj) { e.diagram.commandHandler.redo(); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Layout"),
					{
						click: function (e, obj) {
							var adorn = obj.part;
							adorn.diagram.startTransaction("Subtree Layout");
							layoutTree(adorn.adornedPart);
							adorn.diagram.commitTransaction("Subtree Layout");
						}
					}
				)
			);

		// a link is just a Bezier-curved line of the same color as the node to which it is connected
		myDiagram.linkTemplate =
			$(go.Link,
				{
					curve: go.Link.Bezier,
					fromShortLength: -2,
					toShortLength: -2,
					selectable: false
				},
				$(go.Shape,
					{ strokeWidth: 3 },
					new go.Binding("stroke", "toNode", function (n) {
						if (n.data.brush) return n.data.brush;
						return "black";
					}).ofObject())
			);

		// the Diagram's context menu just displays commands for general functionality
		myDiagram.contextMenu =
			$("ContextMenu",
				$("ContextMenuButton",
					$(go.TextBlock, "Paste"),
					{ click: function (e, obj) { e.diagram.commandHandler.pasteSelection(e.diagram.toolManager.contextMenuTool.mouseDownPoint); } },
					new go.Binding("visible", "", function (o) { return o.diagram && o.diagram.commandHandler.canPasteSelection(o.diagram.toolManager.contextMenuTool.mouseDownPoint); }).ofObject()),
				$("ContextMenuButton",
					$(go.TextBlock, "Undo"),
					{ click: function (e, obj) { e.diagram.commandHandler.undo(); } },
					new go.Binding("visible", "", function (o) { return o.diagram && o.diagram.commandHandler.canUndo(); }).ofObject()),
				$("ContextMenuButton",
					$(go.TextBlock, "Redo"),
					{ click: function (e, obj) { e.diagram.commandHandler.redo(); } },
					new go.Binding("visible", "", function (o) { return o.diagram && o.diagram.commandHandler.canRedo(); }).ofObject()),
				$("ContextMenuButton",
					$(go.TextBlock, "Save"),
					{ click: function (e, obj) { save(); } }),
				$("ContextMenuButton",
					$(go.TextBlock, "Load"),
					{ click: function (e, obj) { load(); } })
			);

		myDiagram.addDiagramListener("SelectionMoved", function (e) {
			var rootX = myDiagram.findNodeForKey(0).location.x;
			myDiagram.selection.each(function (node) {
				if (node.data.parent !== 0) return; // Only consider nodes connected to the root
				var nodeX = node.location.x;
				if (rootX < nodeX && node.data.dir !== "right") {
					updateNodeDirection(node, "right");
				} else if (rootX > nodeX && node.data.dir !== "left") {
					updateNodeDirection(node, "left");
				}
				layoutTree(node);
			});
		});

		apiclient.getProject(sessionStorage.proyecto, map.load);
		hiddenComponentAddCollaborator();

	}

	function spotConverter(dir, from) {
		if (dir === "left") {
			return (from ? go.Spot.Left : go.Spot.Right);
		} else {
			return (from ? go.Spot.Right : go.Spot.Left);
		}
	}

	function changeTextSize(obj, factor) {
		var adorn = obj.part;
		adorn.diagram.startTransaction("Change Text Size");
		var node = adorn.adornedPart;
		var tb = node.findObject("TEXT");
		tb.scale *= factor;
		adorn.diagram.commitTransaction("Change Text Size");
	}

	function toggleTextWeight(obj) {
		var adorn = obj.part;
		adorn.diagram.startTransaction("Change Text Weight");
		var node = adorn.adornedPart;
		var tb = node.findObject("TEXT");
		// assume "bold" is at the start of the font specifier
		var idx = tb.font.indexOf("bold");
		if (idx < 0) {
			tb.font = "bold " + tb.font;
		} else {
			tb.font = tb.font.substr(idx + 5);
		}
		adorn.diagram.commitTransaction("Change Text Weight");
	}

	function updateNodeDirection(node, dir) {
		myDiagram.model.setDataProperty(node.data, "dir", dir);
		// recursively update the direction of the child nodes
		var chl = node.findTreeChildrenNodes(); // gives us an iterator of the child nodes related to this particular node
		while (chl.next()) {
			updateNodeDirection(chl.value, dir);
		}
	}

	function addNodeAndLink(e, obj) {
		var adorn = obj.part;
		var diagram = adorn.diagram;
		diagram.startTransaction("Add Node");
		var oldnode = adorn.adornedPart;
		var olddata = oldnode.data;
		// copy the brush and direction to the new node data
		var newdata = { text: "idea", brush: olddata.brush, dir: olddata.dir, parent: olddata.key };

		//dataList.add(newdata)
		// alert(dataList.length)
		//alert(myDiagram.model.toJson())

		diagram.model.addNodeData(newdata);
		layoutTree(oldnode);
		//Add new component
		diagram.commitTransaction("Add Node");

		// if the new node is off-screen, scroll the diagram to show the new node
		var newnode = diagram.findNodeForData(newdata);
		if (newnode !== null) diagram.scrollToRect(newnode.actualBounds);

		newCurrentRoot(newnode)
	}

	var newCurrentRoot = function (newnode) {

		currentRootId = newnode.data.key; //current root id 
		currentRootParentId = newnode.data.parent; //cuando es 0, el padre es el proyecto

		setValuesToAddRoot();
	}


	var setValuesToAddRoot = function () {
		if (currentRootParentId == 0) {
			currentRootParentId = null;
		}

		if (currentRootParentId != null) {
			apiclient.getRoot(currentProject.id, currentRootParentId, setCurrentRootParent);//se necesita el id del padre y el nombre
			apiclient.getUser(setCurrentUser);
		}

		apiclient.getRoot(currentProject.id, currentRootId, setCurrentRootObject);

		hiddenComponentAdd();

	}


	function layoutTree(node) {
		if (node.data.key === 0) {  // adding to the root?
			layoutAll();  // lay out everything
		} else {  // otherwise lay out only the subtree starting at this parent node
			var parts = node.findTreeParts();
			layoutAngle(parts, node.data.dir === "left" ? 180 : 0);
		}
	}

	function layoutAngle(parts, angle) {
		var layout = go.GraphObject.make(go.TreeLayout,
			{
				angle: angle,
				arrangement: go.TreeLayout.ArrangementFixedRoots,
				nodeSpacing: 5,
				layerSpacing: 20,
				setsPortSpot: false, // don't set port spots since we're managing them with our spotConverter function
				setsChildPortSpot: false
			});
		layout.doLayout(parts);
	}

	function layoutAll() {
		var root = myDiagram.findNodeForKey(0);
		if (root === null) return;
		myDiagram.startTransaction("Layout");
		// split the nodes and links into two collections
		var rightward = new go.Set(/*go.Part*/);
		var leftward = new go.Set(/*go.Part*/);
		root.findLinksConnected().each(function (link) {
			var child = link.toNode;
			if (child.data.dir === "left") {
				leftward.add(root);  // the root node is in both collections
				leftward.add(link);
				leftward.addAll(child.findTreeParts());
			} else {
				rightward.add(root);  // the root node is in both collections
				rightward.add(link);
				rightward.addAll(child.findTreeParts());
			}
		});
		// do one layout and then the other without moving the shared root node
		layoutAngle(rightward, 0);
		layoutAngle(leftward, 180);
		myDiagram.commitTransaction("Layout");
	}

	// Show the diagram's model in JSON format
	function save() {
		document.getElementById("mySavedModel").value = myDiagram.model.toJson();
		console.log(myDiagram.model.toJson());
		myDiagram.isModified = false;
	}

	function load(project) {
		proyecto = project
		var ramlist = project.ramas;
		var list = [];
		var pro = { "key": 0, "text": project.nombre, "loc": "0 0" };
		list.push(pro);
		currentProject = project;
		for (var i = 1; i <= ramlist.length; i++) {
			var rama = ramlist[i - 1];
			//alert(JSON.stringify(rama))
			//alert(rama.ramaPadre)

			var padre = rama.ramaPadre;
			var idPadre;
			if (padre == null) {
				idPadre = 0;
			}
			else {
				idPadre = padre.id;
			}
			//alert(JSON.stringify(padre))
			var cadena = { "key": rama.id, "parent": idPadre, "text": rama.nombre };
			list.push(cadena);
		};



		var val = { "class": "go.TreeModel", "nodeDataArray": list };
		myDiagram.model = go.Model.fromJson(val);
		layoutAll();
		conectar();
		mensajes(project);
	}

	function loadN() {
		myDiagram.model = go.Model.fromJson(document.getElementById("mySavedModel").value);
	}

	var mensajes = function (project) {
		for (var i = 0; i < project.mensajes.length; i++) {
			var mensaje = project.mensajes[i];
			$("#chat").append(
				'<li> <div class="commenterImage"> <img src="img/default.jpg" /> </div> <div class="commentText"></div> <p class="">' + mensaje.contenido + '</p> <span class="date sub-text">' + mensaje.usuario.nombre + ' on ' + mensaje.fecha + '</span> </div> </li>'
			);
		}
	}

	var enviar = function () {
		apiclient.getUser(publicar)
	}

	var publicar = function (rem) {
		if ($("#mensaje").val() != "") {
			var mensaje = new Mensaje(rem, null, $("#mensaje").val());
			$("#mensaje").val("");
			stompClient.send("/treecore/mensaje." + sessionStorage.proyecto, {}, JSON.stringify(mensaje));
		}
	}

	var invitar = function () {
		if ($("#colaborador").val() != "") {
			var invitacion = new Invitacion(localStorage.correo, proyecto.id, proyecto.nombre, $("#colaborador").val());
			stompClient.send("/treecore/invitacion." + $("#colaborador").val(), {}, JSON.stringify(invitacion));
		}
		$("#colaborador").val("");
		hiddenComponentAddCollaborator();
	}

	var conectar = function () {
		var socket = new SockJS('/stompendpoint');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function (frame) {
			stompClient.subscribe('/project/mensaje.' + sessionStorage.proyecto, function (eventbody) {
				var mensaje = JSON.parse(eventbody.body);
				$("#chat").append(
					'<li> <div class="commenterImage"> <img src="img/default.jpg" /> </div> <div class="commentText"></div> <p class="">' + mensaje.contenido + '</p> <span class="date sub-text">' + mensaje.usuario.nombre + ' on ' + mensaje.fecha + '</span> </div> </li>'
				);
			});
			stompClient.subscribe('/project/update/tree', function (root) {
				updateTree(root);
			});
		});
	}

	class Mensaje {
		constructor(usuario, fecha, contenido) {
			this.usuario = usuario;
			this.fecha = fecha;
			this.contenido = contenido;
		}
	}

	class Invitacion {
		constructor(remitente, proyecto, nombreProyecto, receptor) {
			this.remitente = remitente;
			this.proyecto = proyecto;
			this.nombreProyecto = nombreProyecto;
			this.receptor = receptor;
		}
	}


	var hiddenComponentAdd = function () {
		var el = document.getElementById("componentInfo");
		el.style.display = (el.style.display == 'none') ? 'block' : 'none';
	}

	var hiddenComponentAddCollaborator = function () {
		var el = document.getElementById("componentCollaborator");
		el.style.display = (el.style.display == 'none') ? 'block' : 'none';
	}

	var currentRoot = function (e, obj) {
		var adorn = obj.part;
		var diagram = adorn.diagram;
		diagram.startTransaction("Add Node");
		var oldnode = adorn.adornedPart;
		var olddata = oldnode.data;//oldata is the current root

		currentRootId = olddata.key; //current root id 
		currentRootParentId = olddata.parent; //cuando es 0, el padre es el proyecto

		setValuesToAddRoot();

	}

	setCurrentRootParent = function (rootParent) {
		currentRootParent = rootParent;
	}

	setCurrentUser = function (user) {
		currentUser = user;
	}

	setCurrentRootObject = function (root) {
		currentRoot = root;
	}



	var addRootInfo = function (name, messDecr) {

		var newRoot = {
			id: currentRootId,
			nombre: name,
			ramaPadre: currentRootParent,
			descripcion: messDecr,
			archivos: [],
			fechaDeCreacion: "",
			creador: currentUser

		};
		let jroot = JSON.stringify(newRoot);
		//apiclient.addProjectRoot(currentProject.id, JSON.stringify(newRoot));
		stompClient.send("/treecore/projects/"+currentProject.id+"/add/rama",{},jroot);
	}
	

	var delComponent = function () {
		let jroot = JSON.stringify(currentRoot);
		//apiclient.deleteRoot(JSON.stringify(currentRoot));
		stompClient.send("/treecore/project/delete/rama",{},jroot);
	}


	var back = function () {
		location.replace("/profile.html")
	}

	var verificar = function () {
		if (sessionStorage.proyecto == null) {
			location.replace("/profile.html")
		}
	}


	var updateTree=function(root){
		if(root!=null){
			location.reload();
		}
	}


	var closeOption=function(){
		location.reload();
	}

	return {
		init: init,
		hiddenComponentAdd: hiddenComponentAdd,
		delComponent: delComponent,
		save: save,
		load: load,
		layoutAll: layoutAll,
		enviar: enviar,
		addRootInfo: addRootInfo,
		back: back,
		verificar: verificar,
		hiddenComponentAddCollaborator: hiddenComponentAddCollaborator,
		invitar: invitar,
		closeOption:closeOption
	}
})();
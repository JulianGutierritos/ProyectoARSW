var apifiles = (function () {
    var appUrl = "https://treecore.herokuapp.com/treecore";
    //var appUrl = "http://localhost:8080/treecore";

    return {
		
		searchFiles: function (path, callback) {
        	jQuery.ajax({
        		url: appUrl+"/files/" + path,
        		type: "GET",
        		success: function(response) {
        			callback(response);
                }
            });
		},
		searchFile: function (path, callback) {
        	jQuery.ajax({
        		url: appUrl+"/file/" + path,
        		type: "GET",
        		success: function(response) {
        			callback(response);
                }
            });
		},
		
		postFile: function (path,file) {
			var postRequest = $.ajax({
                url: appUrl+"/file/" + path,
                type: "POST",
                data: function () {
                    var data = new FormData();
                    data.append("name", file.name);
                    data.append("file", file);
                    return data;
                }(),
                contentType: false,
                processData: false,
                success: function (response) {   
					alert("Archivo " + file.name+ " cargado")                     
                },
                error: function (jqXHR, textStatus, errorMessage) {
                    alert(file.name + " no cargado");
                }
            });
			
		},

    };
})();
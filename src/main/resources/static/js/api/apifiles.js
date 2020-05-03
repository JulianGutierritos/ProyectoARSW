var apifiles = (function () {
    //var appUrl = "https://treecore.herokuapp.com/treecore/v1";
    var appUrl = "http://localhost:8080/treecore/v1";

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

        putFile: function (path,file) {
			var putRequest = $.ajax({
                url: appUrl+"/file/" + path,
                type: "PUT",
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
        
        deleteFile:function (path){
			var delRequest = $.ajax({
				url: appUrl+"/file/" + path,
				type: 'DELETE',
			});
			delRequest.then(
				function () {
					alert("Archivo borrado") 
				},
				function () {
					alert("failure delete");
				}
			);
        },

    };
})();
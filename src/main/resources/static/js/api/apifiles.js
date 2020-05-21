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
                    swal(
						'¡Éxito al cargar el archivo!',
						'Su archivo ' + file.name + ' fue cargado con éxito.',
						'success'
					);                   
                },
                error: function (jqXHR, textStatus, errorMessage) {
                    swal(
						'Error al cargar el archivo',
						'Su archivo ' + file.name + ' no pudo ser cargado. Por favor intentelo de nuevo.',
						'error'
					);     
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
                    swal(
						'¡Éxito al cargar el archivo!',
						'Su archivo ' + file.name + ' fue cargado con éxito.',
						'success'
					);                   
                },
                error: function (jqXHR, textStatus, errorMessage) {
                    swal(
						'Error al cargar el archivo',
						'Su archivo ' + file.name + ' no pudo ser cargado. Por favor intentelo de nuevo.',
						'error'
					); 
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
				}
			);
        },

    };
})();
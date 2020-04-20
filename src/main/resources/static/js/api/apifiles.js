var apifiles = (function () {
    //var appUrl = "https://treecore.herokuapp.com/treecore";
    var appUrl = "http://localhost:8080/treecore";

    var showFiles = function(resp){
		for(var i = 0; i < resp.length; i++) {
			console.log(resp[i]);
		};
	}
    return {
		
		getFiles: function (path, callback) {
        	jQuery.ajax({
        		url: appUrl+"/files/" + path,
        		type: "GET",
        		success: function(response) {
        			callback(response);
                }
            });
        }
        
    };
})();
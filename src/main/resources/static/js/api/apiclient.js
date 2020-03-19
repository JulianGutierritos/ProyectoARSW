var apiclient = (function () {
    var appUrl = "http://localhost:8080/treecore";

    return {
    	
        getUsers: function (callback) {
        	jQuery.ajax({
        		url: appUrl,
        		type: "GET",
        		success: function(respuesta) {
        			callback(respuesta);
        		}
        	});
        },

        loginUser : function (user){
        	var postRequest=$.ajax({
				url:  appUrl+"/login",
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					alert("successful process");
					location.reload(); 
				},
				function(){
					alert("failure login");
				}
			);
        },
        
        addUser : function (user){
        	var postRequest=$.ajax({
				url:  appUrl,
				type: 'POST',
				data: user,
				contentType: "application/json"
			});
			postRequest.then(
				function(){
					alert("successful sign up");
					location.reload(); 
				},
				function(){
					alert("sign up failed");
				}
			);
        }
        
    };
})();
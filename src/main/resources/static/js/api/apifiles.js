var apifiles = (function () {
    var appUrl = "https://treecore.herokuapp.com/treecore";
    //var appUrl = "http://localhost:8080/treecore";

    var showFiles = function(resp){
		for(var i = 0; i < resp.length; i++) {
			console.log(resp[i]);
		};
	}
	var showFile = function(resp){
		if (resp != null){
			console.log(resp);
		}
	}
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
                },
                error: function (jqXHR, textStatus, errorMessage) {
                    console.log(errorMessage);
                }
            });
			
		},
	
		
		getFiles: function(opt){
			var path = document.getElementById("getPath").value;
			path= path.replace(/[/]/g, '+++');;
			path= path.replace(/" "/g, "%20");
			//console.log(path);
			if(opt=="C"){
				this.searchFiles(path,showFiles);
			}else if (opt=="A"){
				this.searchFile(path,showFile);
			}
			
		},

		uploadFile: function(){
			var x = document.getElementById("myFile");
			var path = document.getElementById("postPath").value;
			path= path.replace(/[/]/g, '+++');;
			path= path.replace(/" "/g, "%20");
			var txt = "";
			if ('files' in x) {
				if (x.files.length == 0) {
				txt = "Select one or more files.";
				} else {
					for (var i = 0; i < x.files.length; i++) {
						txt += "<br><strong>" + (i+1) + ". file</strong><br>";
						var file = x.files[i];
						if ('name' in file) {
						txt += "name: " + file.name + "<br>";
						}
						if ('size' in file) {
						txt += "size: " + file.size + " bytes <br>";
						}
						this.postFile(path,file);
					}
				}
			} 
			else {
				if (x.value == "") {
				txt += "Select one or more files.";
				} else {
				txt += "The files property is not supported by your browser!";
				txt  += "<br>The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead. 
				}
			}
			document.getElementById("demo").innerHTML = txt;

		}
    };
})();
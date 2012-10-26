$(function() {
	getStamps();
});

getStamps = function() {
    $.ajax({
        url: "rest/stamps",
        dataType: "json",
        success: function(response) {
            var resp = response.stamp;
            jQuery.each(resp, function(i, data) {
                $("#CatalogArea").prepend("<tr><td>" + data.issueNumber + "</td><td>" + data.name + "</td><td><input type='button' value='Edit'></td></tr>");
            });
        }
    });
};

addStamp = function() {
    $.ajax({
    	url: "rest/stamps",
    	type: "POST",
    	data: {
    		issueNumber: $("#issueNumber").val(),
    		name: $("#name").val(),
    		type: $("#type").val()
    	},
    	success: function(response) {
    	    var type = $("#issueNumber").val().charAt(0);
    	    var number = $("#issueNumber").val().substr(1);
    	    $("#issueNumber").val(type + (++number));
    	    $("#name").val("");
    	    
            $("#CatalogArea").prepend("<tr><td>" + response.issueNumber + "</td><td>" + response.name + "</td><td><input type='button' value='Edit'></td></tr>");
    	}
    });
};
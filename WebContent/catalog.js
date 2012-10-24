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
                $("#CatalogArea").append("<tr><td>" + data.issueNumber + "</td><td>" + data.name + "</td></tr>");
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
    	success: function() {
    	    alert("addition done");    		
    	}
    });
};
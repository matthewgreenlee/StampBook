getStamps = function() {
	var jqxhr = $.ajax({
		url : "./rest/stamps/1",
		data : null,
		success : function() {
			var stampDoc = $.parseXML(jqxhr.responseText);
			var issueNumber = $(stampDoc).find("issueNumber");
			$("#CatalogArea").append($(issueNumber).text());
			console.log(jqxhr.responseText);
		},
		dataType : "xml"
	});
};

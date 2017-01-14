app.controller("NotificationController", function($scope) {
	// Send a 'test' command to the server
	var url = window.location.href;
	var arr = url.split("/");
	var host = arr[0]+"//"+arr[2];

	var testUrl = host+"/ModWillie/api/test/";
	console.log(testUrl);
	$scope.test = function(id) {
		$.get(testUrl+id, function(data) {
			console.log("Received: ", data);
		});
	};
});
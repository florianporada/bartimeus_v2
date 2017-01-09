app.controller("NotificationController", function($scope) {
	// Send a 'test' command to the server
	$scope.test = function(id) {
		$.get("http://localhost/ModWillie/api/test/"+id, function(data) {
			console.log("Received: ", data);
		});
	};
});
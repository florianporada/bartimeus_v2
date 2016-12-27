app.controller("BaseController", function($scope, $translate) {
	$scope.lang = function(lang) {
        window.localStorage.setItem("language", lang);
        $translate.use(lang);
        $scope.LANG = lang;
	}
});
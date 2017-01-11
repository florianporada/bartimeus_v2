// Taken from: http://stackoverflow.com/a/31723003
// When using ng-bind-html the content will not be evaluated.
// So whe use a custom directive that will compile the HTML and show it

app.directive("compileHtml", function($compile) {
    return {
        restrict: "A",
        link: function(scope, ele, attrs) {
            scope.$watch(function () {
                return scope.$eval(attrs.compileHtml);
            }, function (value) {
                ele.html(value);
                $compile(ele.contents())(scope);
            });
        }
    }
});
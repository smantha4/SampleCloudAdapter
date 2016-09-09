  angular
        .module('portfoliomanagerApp').directive('watchlistmini', function() {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            watchlist: '=',
        },
        templateUrl: "app/home/watchlist-mini.html",
        controller: ['$scope', '$http', function($scope, $http) {



        }],
        link: function(scope, iElement, iAttrs, ctrl) {
          console.log("Linking the watchlistmini directive");
        }
    };
});


angular
        .module('portfoliomanagerApp').directive('uiLabel', function() {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            value: '=',
            label: '@',
            showGainColor:'@'
        },
        templateUrl: 'app/home/ui-label-template.html',
        controller: ['$scope', '$http', function($scope, $http) {



        }],
        link: function(scope, iElement, iAttrs, ctrl) {
          console.log("Linking the watchlistmini directive");
        }
    };
});

angular
        .module('portfoliomanagerApp').directive('uiLabelLarge', function() {
    return {
        restrict: 'E',
        replace: true,
        scope: {
            value: '=',
            label: '@'
        },
        templateUrl: 'app/home/ui-label-template.html',
        controller: ['$scope', '$http', function($scope, $http) {



        }],
        link: function(scope, iElement, iAttrs, ctrl) {
          console.log("Linking the watchlistmini directive");
        }
    };
});


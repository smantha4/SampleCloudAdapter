(function() {
    'use strict';

    angular
        .module('portfoliomanagerApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$http', '$mdSidenav'];

    function HomeController($scope, Principal, LoginService, $state, $http, $mdSidenav) {
        var vm = this;

        $scope.showQuotes = false;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.login = LoginService.open;
        vm.register = register;
        $scope.$on('authenticationSuccess', function() {
            getAccount();
        });

        getAccount();

        function getAccount() {
            Principal.identity().then(function(account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        function register() {
            $state.go('register');
        }

        $scope.getQuote = function() {
            console.log("Fetching prices...");

            $http.get('/api/quote?query=' + $scope.symbol).
            success(function(data) {
                $scope.showQuotes = true;
                var quote = new Object();
                quote = data;
                $scope.quote = quote;
            });

        }

        $scope.toggleSidenav = function() {
            console.log('toggleSidenav');
            $mdSidenav('nav').toggle();
        };

    }
})();

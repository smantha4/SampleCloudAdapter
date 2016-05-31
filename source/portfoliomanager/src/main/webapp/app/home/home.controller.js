(function() {
    'use strict';

    angular
        .module('portfoliomanagerApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$http', '$mdSidenav', '$mdDialog', '$mdMedia'];

    function HomeController($scope, Principal, LoginService, $state, $http, $mdSidenav, $mdDialog, $mdMedia) {
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

        /**
         * Get the list of watchlists
         */
        $scope.getWatchLists = function() {

            $http.get('/api/watchlist').
            success(function(data) {
                console.log('Fetched watchlist data');
                $scope.watchlists = data.watchLists;
                //Choose the first watchlist
                $scope.currentWatchlist = $scope.watchlists[0].id;
                $scope.getWatchList();
            });
        }

        $scope.selectWatchlist = function(id) {
            console.log("selected watchlist" + id);
            $scope.currentWatchlist = id;
            $mdSidenav('nav').toggle();
            $scope.getWatchList();
        }

        $scope.getWatchList = function() {
            /* body... */
            $http.get('/api/watchlist/' + $scope.currentWatchlist).
            success(function(data) {
                console.log('Fetched watchlist data');
                $scope.watchlistDetail = data.watchLists;

                //Get Messages for watchlist
                if ($scope.currentWatchlist) {
                    $scope.getMessagesForWatchlist();
                }

            });
        }

        $scope.getMessagesForWatchlist = function() {
            $http.get('/api/watchlist/messages?watchlistid=' + $scope.currentWatchlist).
            success(function(data) {
                $scope.messages = data.messages;

                //Get Messages for watchlist

            });
        }


        $scope.addWatchlist = function() {


            console.log("Add new watchlist");
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
            $mdDialog.show({
                    controller: NewWatchlistController,
                    templateUrl: 'app/home/watchlist-add-dialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true,
                    fullscreen: useFullScreen
                })
                .then(function(answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });
            $scope.$watch(function() {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function(wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });
        }

        /**
         * @param {[type]}
         * @param {[type]}
         */
        function NewWatchlistController($scope, $mdDialog) {

            $scope.newWatchlistem = new Object();
            $scope.hide = function() {
                $mdDialog.hide();
            };
            $scope.cancel = function() {
                $mdDialog.cancel();
            };
            $scope.save = function(answer) {
                $http.post('/api/watchlist', newWatchlist)
                    .success(function(data) {
                        //Reload the watchlist
                        $scope.getWatchList();
                        $mdDialog.hide(answer);
                    });

            };
        }


        /**
         * show dialog to add stock to the watchlist
         */
        $scope.addToWatchlist = function() {


            console.log("Add stock to watchlist");
            var useFullScreen = ($mdMedia('sm') || $mdMedia('xs')) && $scope.customFullscreen;
            $mdDialog.show({
                    controller: WatchlistController,
                    scope: $scope.$new(),
                    templateUrl: 'app/home/watchlist-item-add-dialog.html',
                    parent: angular.element(document.body),
                    clickOutsideToClose: true,
                    fullscreen: useFullScreen
                })
                .then(function(answer) {
                    $scope.status = 'You said the information was "' + answer + '".';
                }, function() {
                    $scope.status = 'You cancelled the dialog.';
                });
            $scope.$watch(function() {
                return $mdMedia('xs') || $mdMedia('sm');
            }, function(wantsFullScreen) {
                $scope.customFullscreen = (wantsFullScreen === true);
            });
        }

        /**
         * @param {[type]}
         * @param {[type]}
         */
        function WatchlistController($scope, $mdDialog) {

            $scope.newWatchlistem = new Object();
            $scope.hide = function() {
                $mdDialog.hide();
            };
            $scope.cancel = function() {
                $mdDialog.cancel();
            };
            $scope.save = function(answer) {
                $http.post('/api/watchlist/' + $scope.currentWatchlist + '/watchliststockitem', $scope.newWatchlistem)
                    .success(function(data) {
                        //Reload the watchlist
                        $scope.getWatchList();
                        $mdDialog.hide(answer);
                    });

            };
        }

        $scope.getWatchLists();
        $scope.getWatchList();
    }




})();

(function() {
    'use strict';

    angular
        .module('portfoliomanagerApp')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$scope', 'Principal', 'LoginService', '$state', '$http', '$mdSidenav', '$mdDialog', '$mdMedia', '$location'];

    function HomeController($scope, Principal, LoginService, $state, $http, $mdSidenav, $mdDialog, $mdMedia, $location) {
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

        $scope.currentWatchlist = $location.search()['watchlist'];


        /**
         * Get the list of watchlists
         */
        $scope.getWatchLists = function() {

            $http.get('/api/watchlist').
            success(function(data) {
                console.log('Fetched watchlist data');
                $scope.watchlists = data.watchLists;
                //Choose the first watchlist

                if (!$scope.currentWatchlist || $scope.currentWatchlist == 'null') {
                    $scope.currentWatchlist = $scope.watchlists[0].id;
                }
                $scope.getWatchList();
                $scope.getWatchlistsWithDetails();
            });
        }

        /*
         *   Get all the watchlist with details
         *   This method is used by the all-watchlist screen
         */
        $scope.getWatchlistsWithDetails = function() {
            /* body... */
            $scope.watchlistsWithDetails = [];

            if ($scope.watchlists) {

                console.log($scope.watchlists);

                for (var i = 0; i < $scope.watchlists.length; i++) {
                    console.log('Fetching data for mini view' + $scope.watchlists[i].id);

                    $http.get('/api/watchlist/' + $scope.watchlists[i].id).
                    success(function(data) {
                        $scope.watchlistsWithDetails.push(data);
                    });
                }
            } else {
                alert("No watchlist available");
            }
        }

        $scope.selectWatchlist = function(id) {
            $scope.currentWatchlist = id;
            $mdSidenav('nav').toggle();
            $scope.getWatchList();
        }

        /**
         * get the watchlist details
         * @return {[type]} [description]
         */
        $scope.getWatchList = function() {

            if ($scope.currentWatchlist) {
                /* body... */
                $http.get('/api/watchlist/' + $scope.currentWatchlist).
                success(function(data) {
                    console.log('Fetched watchlist data');
                    $scope.watchlistDetail = data;

                    //Get Messages for watchlist
                    if ($scope.currentWatchlist) {
                        console.log("Fetching messages for watchlist " + $scope.currentWatchlist);
                        $scope.getWatchListMessages($scope.currentWatchlist);
                    }
                });
            }
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
                    scope: $scope.$new(),
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
            $scope.newWatchlist = new Object();

            $scope.hide = function() {
                $mdDialog.hide();
            };
            $scope.cancel = function() {
                $mdDialog.cancel();
            };
            $scope.save = function(answer) {
                $http.post('/api/watchlist', $scope.newWatchlist)
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


        /**
         * Delete stock item
         * @param  {[type]} symbol [description]
         * @return {[type]}        [description]
         */
        $scope.deleteStockItem = function(symbol) {

            $http.delete('/api/watchlist/' + $scope.currentWatchlist + '/watchliststockitem/' + symbol)
                .success(function(data) {
                    console.log('Deleted stock item');
                    $scope.getWatchList();
                });

        }

        /**
         * delete watchlist
         * @param  {[type]} id [description]
         * @return {[type]}    [description]
         */
        $scope.deleteWatchList = function(id) {
            $http.delete('/api/watchlist/' + id)
                .success(function(data) {
                    console.log('Deleted stock item');
                    $scope.currentWatchlist = null;
                });
        }

        /**
         * gets the messages for a watchlist
         * @param  {[type]} id [description]
         * @return {[type]}    [description]
         */
        $scope.getWatchListMessages = function(id) {
            $http.get('/api/watchlist/messages/?watchlistid=' + id)
                .success(function(data) {
                    console.log('Fetched Messages');
                    $scope.messages = data;
                });
        }



        $scope.getWatchLists();
        $scope.getWatchList();
    }




})();

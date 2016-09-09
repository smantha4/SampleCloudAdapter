(function() {
    'use strict';

    angular
        .module('portfoliomanagerApp')
        .config(function($mdIconProvider) {
            $mdIconProvider
                .iconSet('social', 'img/icons/sets/social-icons.svg', 16)
                .defaultIconSet('img/icons/sets/core-icons.svg', 16);
        })
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('home', {
                parent: 'app',
                url: '/',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/home/home.html',
                        controller: 'HomeController',
                        controllerAs: 'vm'
                    }
                }
            })
            .state('watchlist', {
                parent: 'app',
                url: '/watchlist',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/home/watchlist.html',
                        controller: 'HomeController',
                        controllerAs: 'vm'
                    }
                }
            }).state('marketview', {
                parent: 'app',
                url: '/marketview',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/home/marketview.html',
                        controller: 'MarketviewController',
                        controllerAs: 'mv'
                    }
                }
            }).state('marketview-page1', {
                parent: 'app',
                url: '/marketview1',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/home/marketview-page1.html',
                        controller: 'MarketviewController',
                        controllerAs: 'mv'
                    }
                }
            }).state('marketview-page2', {
                parent: 'app',
                url: '/marketview2',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/home/marketview-page2.html',
                        controller: 'MarketviewController',
                        controllerAs: 'mv'
                    }
                }
            }).state('watchlist-all', {
                parent: 'app',
                url: '/watchlist-all',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'app/home/watchlist-all.html',
                        controller: 'HomeController',
                        controllerAs: 'vm'
                    }
                }
            });
    }
})();

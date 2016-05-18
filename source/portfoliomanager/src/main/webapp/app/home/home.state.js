(function() {
    'use strict';

    angular
        .module('portfoliomanagerApp')
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
            });
    }
})();

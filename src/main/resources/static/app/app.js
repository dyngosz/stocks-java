(function (angular) {

    'use strict';

    angular.module('App', ['ngRoute', 'ngResource'])
        .config(['$routeProvider', function ($routeProvider) {
            $routeProvider
                .when('/stock', {
                    templateUrl: 'app/views/stock.html',
                    controller: 'StockController'
                })
                .when('/stock/:stockID', {
                    templateUrl: 'app/views/stock.html',
                    controller: 'StockController'
                })
                .otherwise({
                    redirectTo: '/stock'
                });

        }])
})(window.angular);
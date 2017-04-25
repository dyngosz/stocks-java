(function (angular) {

    'use strict';

    var App = angular.module('App');
    App.directive('navbarMenu', [function () {

        return {
            controller: 'NavBarController',
            templateUrl: 'app/views/navbar-menu.html'
        };
    }]);
})(window.angular);
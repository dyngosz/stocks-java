(function(angular) {

	'use strict';

	var App = angular.module('App');

	App.controller('StockController', [ '$scope', '$http', '$routeParams',
			'$rootScope', function($scope, $http, $routeParams, $rootScope) {

				$scope.init = function() {
					if ($rootScope.stockID !== undefined) {
						$scope.getStockData($rootScope.stockID);
						return;
					} else if ($routeParams.stockID !== undefined) {
						$rootScope.stockID = $routeParams.stockID;
					} else {
						$rootScope.stockID = 'FB';
					}
					$scope.getStockData($rootScope.stockID);
				};

				$scope.getStockData = function(city) {

					$http.get('/stock', {
						params : {
							stockID : encodeURI(stockID)
						}
					}).then(function(response) {
						$scope.showError = false;
						$scope.stock = response.data;
						console.log(response.data);
						$scope.stockAvailable = true;
					}, function(response) {
						$scope.showError = true;
					})
				};

				$scope.init();

			} ]);

})(window.angular);
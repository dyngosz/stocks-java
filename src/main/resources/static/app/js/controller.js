(function(angular) {

	'use strict';

	var App = angular.module('App');

	App.controller('StockController', [ '$scope', '$http', '$routeParams',
			'$rootScope', function($scope, $http, $routeParams, $rootScope) {

		$scope.dataSource = {
//			    "chart": {
//			      "caption": "Column Chart Built in Angular!",
//			      "captionFontSize": "30",
//			      // more chart properties - explained later
//			    },
//			    "data": [{
//			        "label": "CornflowerBlue",
//			        "value": "42"
//			      }, //more chart data
//			    ]
			  };
		
		
		
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

				
				
				$scope.getStockData = function(stockID) {
					$scope.loading = true;
					$scope.content = false;
					$http.get('/stock', {
						params : {
							stockID : encodeURI(stockID)
						}
					}).then(function(response) {
						$scope.showError = false;
						$scope.stock = response.data;
						$scope.stockAvailable = true;
						
					}, function(response) {
						$scope.showError = true;
					}).finally(function() {
					    // called no matter success or failure
					    $scope.loading = false;
						$scope.content = true;
						
					  });
				};

				$scope.init();

			} ]);

	App.controller('NavBarController', [ '$scope', '$location', '$rootScope',
			function($scope, $location, $rootScope) {

				$scope.isActive = function(viewLocation) {
					return viewLocation === $location.path();
				};

				$scope.getStockData = function(stockID) {
					$rootScope.stockID = stockID;
					$location.path('/stock/' + stockID)
				};
			} ]);

})(window.angular);
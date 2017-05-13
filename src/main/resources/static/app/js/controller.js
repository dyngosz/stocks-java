(function(angular) {

	'use strict';

	var App = angular.module('App');

	App.controller('StockController', [ '$scope', '$http', '$routeParams',
			'$rootScope', function($scope, $http, $routeParams, $rootScope) {
				$scope.dataSource ={};
				$scope.chartData = [];

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
						
						$scope.content = true;

						if($scope.stock == null){
							$scope.content = false;
						}
						
						$scope.stockAvailable = true;

				        const r = response.data.dates.map((d, i) => Object.assign({
							  label: d,
							  value: response.data.stockValues[i]
							}))	
				        $scope.dataSource = {
								chart: {
									"caption": "Price of the stock",
							        "xAxisName": "Day",
							        "yAxisName": "Price in $",
							        "lineThickness": "3",
							        "paletteColors": "#0075c2",
							        "baseFontColor": "#333333",
							        "baseFont": "Helvetica Neue,Arial",
							        "captionFontSize": "14",
							        "subcaptionFontSize": "14",
							        "subcaptionFontBold": "0",
							        "showBorder": "0",
							        "bgColor": "#ffffff",
							        "showShadow": "0",
							        "canvasBgColor": "#ffffff",
							        "canvasBorderAlpha": "0",
							        "divlineAlpha": "100",
							        "divlineColor": "#999999",
							        "divlineThickness": "1",
							        "divLineDashed": "1",
							        "divLineDashLen": "1",
							        "showXAxisLine": "1",
							        "xAxisLineThickness": "1",
							        "xAxisLineColor": "#999999",
							        "showAlternateHGridColor": "0"
						        },
						        data: [{
				                    'label': " ",
				                    "value": " "
				                }]
				        		};

				        $scope.dataSource.data = r;
				        
					}, function(response) {
						$scope.showError = true;
					}).finally(function() {
					    // called no matter success or failure
					    $scope.loading = false;
						
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
angular.module('responsableApp.StatistiquesDrctv', [])
	.directive('simpleStat', [function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/simpleStat.tmpl.html',
			link: function (scope,element,attrs) {

			},
			controller:function ($scope,$timeout,$rootScope) {
				
				var scope = $rootScope;
				$scope.refreshStat = function () {
					scope.getAllSpecialites.$promise.then(function () {
					$scope.getStatistiques.$promise.then(function(d) {
						console.log($scope.absencesSpecialite);
				       $scope.chartOptions = {
							dataSource:$scope.absencesSpecialite,
							series: $scope.typeSeries[0],
							commonSeriesSettings:{
								argumentField : $scope.argumentField,
								bar:{color:'#23A86D'},
								type:$scope.typeChart,
								hoverMode: "allArgumentPoints",
				        		selectionMode: "allArgumentPoints",
					        	label: {
						            visible: false,
						            format: "fixedPoint",
						            precision: 0
					        	}
							},
							legend:{
								visible:false,
								verticalAlignment: "bottom",
				       			horizontalAlignment: "center",
							},
				    		tooltip: {
						        enabled: true,
						        customizeText: function () {
						            return this.seriesName +": "+this.valueText+" absences";
				        		}
				    		}
						};
						$scope.loading = false;
						scope.setLoadingPage(false);
				   	});
					});
					$scope.loading = true;
					scope.setLoadingPage(false);
				};
				$scope.refreshStat();
				

				$scope.changeType = function (index) {
					console.log($scope.typeSeries[index]);
					
					$timeout(function () {
						$scope.chartOptions.series = $scope.typeSeries[index];
						$scope.loading = false;
					},1);
					$scope.loading = true;
				}	
			}
		};
	}])
	.directive('pieStat', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/pieStat.tmpl.html',
			link: function (scope,element,attrs) {

			},
			controller:function ($scope,$timeout) {

				$scope.getPie.$promise.then(function(d) {
					console.log($scope.dataSources);
			       $scope.pieOptions = {
						dataSource:$scope.dataSources,
						tooltip: {
							enabled: true,
							percentPrecision: 2,
							customizeText: function(point) { 
								return point.argumentText+" - "+point.valueText + " - " + point.percentText;
							}
						},
						series: [{
							type: "doughnut",
							argumentField: "module",
							label: {
								visible: false,
								//format: "millions",
								connector: {
									visible: true
								}
							}
						}],
						palette: ['#23a86d','#5bc0de','#d9534f','#f0ad4e'],
						legend:{
							visible:true,
							verticalAlignment: "bottom",
			       			horizontalAlignment: "center",
						}

					};
					$scope.loadingPie = false;
			   	});
				$scope.loadingPie = true;
		}
	}})
	.directive('statListeEtudiants', [function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/statListeEtudiants.tmpl.html',
			link: function (scope,element,attrs) {
				//scope.count = attrs.count;
			}
		};
	}])
	;

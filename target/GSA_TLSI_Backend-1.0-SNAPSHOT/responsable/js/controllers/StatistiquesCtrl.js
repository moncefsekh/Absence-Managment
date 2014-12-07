angular.module('responsableApp.StatistiquesCtrl', [])
	.controller('StatistiquesCtrl',function ($scope,$rootScope,$routeParams,$timeout,$filter,ngTableParams,PedagogieModel,StatistiquesModel,EtudiantsModel) {
	 	var scope = $rootScope;
		scope.setLoadingPage(true);
	 	var nbrAbsence = scope.AppParams.nbrAbsence;
		var nbrJustifier = scope.AppParams.nbrJustifier;
		var specialite = $routeParams.specialite;
		$scope.specialite = angular.uppercase(specialite);
		var dataSources = [];
		StatistiquesModel.etudiantsExclus().get({specialite:specialite},function (absences) {
			var getEtudiants = [];
			angular.forEach(absences, function (abs) {
				if((abs.absence.nbr_absence-abs.absence.nbr_absence_just)>=0 || abs.absence.nbr_absence>=nbrAbsence){
					getEtudiants.push(abs);
				}
			});
			//var getEtudiants = absences;
			

			$scope.tableParams = new ngTableParams({
		                page: 1,            // show first page
		                count: 10,           // count per page
		                sorting: {
		            		"absence.nbr_absence": 'asc'     // initial sorting
		        		}
		            }, {
		                groupBy: function(item) {
				            return item.absence.code_midule+"";
				        },
		                total: 0, // length of data
		                getData: function($defer, params) {
		                		params.total(getEtudiants.length);
					            var orderedData = params.sorting() ?
					                    $filter('orderBy')(getEtudiants, $scope.tableParams.orderBy()) :
					                    getEtudiants;

					            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
					    }
					}
			);   
		});

		scope.getAllSpecialites.$promise.then(function () {
	
			$scope.refreshStatistiques = function (specialite) {
				console.log(specialite);
				$scope.getStatistiques = StatistiquesModel.absencesSpecialite().get({specialite:specialite},function (stat) {
					$scope.absencesSpecialite = [];
					angular.forEach(stat, function (abs) {
						$scope.absencesSpecialite.push(abs.absence);
					});
					$scope.typeChart = 'bar';
					$scope.argumentField = 'date_absence';
					$scope.typeSeries = [
						[
							{valueField:'nbr_absence', name:specialite}
						],
						[
							{valueField:'nbr_absence', name:specialite, type:'splineArea',color:'#6fcfa5'},
							{valueField:'nbr_absence', name:specialite, type:'spline',color:'#6fcfa5'}
						]
						]	
				});
			}
			$scope.refreshStatistiques(scope.getSpecialites[0].code_specialite);

		});


		$scope.getPie = StatistiquesModel.etudiantsExclus().get({specialite:specialite},function (absences) {
			//console.log(absencesJour);
				var dataSources = [];
				angular.forEach(absences,function  (abs) {
					console.log(abs);
					dataSources.push({module:abs.absence.code_midule,val:abs.absence.nbr_absence});
				});
			$scope.dataSources = dataSources;

		});

});
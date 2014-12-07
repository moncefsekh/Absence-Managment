angular.module('etudiantApp.AbsencesDrctv',  [])
	.directive('menuModules', function () {
		return {
			restrict: 'AC',
			templateUrl: 'partials/menuModules.tmpl.html',
			controller : function ($scope,$location,ActualitesModel) {
				$scope.modules = ActualitesModel.modules().query({code_specialite:'GL',annee_specialite:'L2'});
				$scope.isShowMenu  = false;
				$scope.selectedSemestreCode = 's1';
				
				if($location.path() != "/"){
					$scope.isShowMenu = true;
					$scope.selectModule = function (code) {
						$scope.selectedModuleCode = code;
					};
					$scope.selectSemestre = function  (code) {
						$scope.selectedSemestreCode = code;
					};
				}
			}
		};
	})
	.directive('listeAbsences', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/listeAbsences.tmpl.html',
			link : function (scope,element,attrs) {
				scope.titreListe = attrs.titreListe;
			}
		};
	})
	.directive('miniListeAbs', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/miniListeAbsences.tmpl.html',
			controller : function ($scope,AbsencesModel) {
				$scope.absencesSemaine = AbsencesModel.absencesSemaine().get({id:'1'});
			},
			link : function (scope,element,attrs) {
				scope.titreListeMini = attrs.titreListe;
			}
		};
	}).directive('miniListeExc', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/miniListeExclusion.tmpl.html',
			link : function (scope,element,attrs) {
				scope.titreListeExc = attrs.titreListe;
			}
		};
	});
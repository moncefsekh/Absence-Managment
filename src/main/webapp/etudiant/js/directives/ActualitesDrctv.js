angular.module('etudiantApp.ActualitesDrctv', [])
	.directive('listeAct', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/listeActualites.tmpl.html',
			link : function (scope,element,attrs) {
				scope.titreListe = attrs.titreListe;
			}
		};
	})
	.directive('miniListeAct', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/miniListeActualites.tmpl.html',
			controller : function ($scope,ActualitesModel) {
				$scope.actualitesSemaine = ActualitesModel.actualitesSemaine().get({code_specialite:'GL',annee_specialite:'L2'});
			},
			link : function (scope,element,attrs) {
				scope.titreListeMini = attrs.titreListe;
			}
		};
	});;
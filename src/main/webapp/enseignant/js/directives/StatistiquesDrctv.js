angular.module('enseignantApp.StatistiquesDrctv', [])
	.directive('listeEtudiantsStat', [function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/listeEtudiantsStat.tmpl.html',
			link: function (scope,element,attrs) {
				scope.count = attrs.count;
			}
		};
	}]);

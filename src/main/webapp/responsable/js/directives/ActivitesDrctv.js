angular.module('responsableApp.ActivitesDrctv', [])
	.directive('activites', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/activites.tmpl.html',
			link : function (scope,element,attrs) {
				scope.titreListe = attrs.titreListe;
			}
		};
	});
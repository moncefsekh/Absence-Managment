angular.module('enseignantApp.SeancesDrctv', [])
	.directive('listeEns', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/listeSeances.tmpl.html',
			//scope:false,
			link : function (scope,element,attrs) {
				scope.titreListe = attrs.titreListe;
			},
			controller:function($scope,$modal){
				$scope.openEtatAvancement = function () {

				    var modalInstance = $modal.open({
				      	templateUrl: 'etat.form.html',
				      	controller: ModalInstanceCtrl,
				    });
			  	};

				var ModalInstanceCtrl = function ($scope, $modalInstance ) {
					  $scope.ok = function () {
					    $modalInstance.close();
					  };

					  $scope.cancel = function () {
					    $modalInstance.dismiss('cancel');
					  };
				};
			}
		};
	})
	.directive('miniListeEns', function () {
		return {
		restrict: 'EA',
		templateUrl : 'partials/miniListeSeances.tmpl.html',
		controller : function ($scope,SeancesModel) {
			$scope.seancesSemaine = SeancesModel.seancesSemaine().get({id:"1"});
		},

		link : function (scope,element,attrs) {
			scope.titreListeMini = attrs.titreListe;
		}
		};
	});
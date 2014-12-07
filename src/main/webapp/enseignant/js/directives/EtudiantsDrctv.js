angular.module('enseignantApp.EtudiantsDrctv',  [])
	.directive('menuEtudiants', function () {
		return {
			restrict: 'AC',
			templateUrl: 'partials/menuEtudiants.tmpl.html',
			controller : function ($scope,$location,SectionsModel,GroupesModel,SeanceModel) {
				$scope.isShowMenu  = false;
				if($location.path() != "/"){
					$scope.isShowMenu = true;
					$scope.selectSection = function (code) {
						$scope.selectedSecCode = code;
						$scope.selectedGroupeCode = '';
						$scope.selectedEnsCode = '';
					};
					$scope.selectGroupe = function (code) {
						$scope.selectedGroupeCode = code;
						$scope.selectedEnsCode = '';
					};
					$scope.selectEns = function (code) {
						$scope.selectedEnsCode = code;
					};
					
					$scope.sections = SectionsModel.sections().get();
					$scope.groupes = GroupesModel.groupes().get();
					$scope.enseignements = SeanceModel.enseignements().get();
				}
			}
		};
	})
	.directive('listeEtudiants', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/listeEtudiants.tmpl.html',
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
	});
angular.module('responsableApp.EtudiantsDrctv',  [])
	.directive('listeEtudiants', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/listeEtudiants.tmpl.html',
			controller: function ($scope,$modal,EtudiantsModel) {
				$scope.openDetailsEtudiant = function (numEtudiant) {
				    var modalInstance = $modal.open({
				      	templateUrl: 'etudiant.details.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		etudiant:function () {
				      			var etudiant = new Object();
				      			etudiant.numEtudiant = numEtudiant;
				      			etudiant.annee = $scope.annee;
				      			etudiant.specialite = $scope.specialite;
				      			return etudiant;
				      		},
				      		etudiants:function () {
				      			return $scope.getEtudiants;
				      		}
				      	}
				    });
			  	};

				var ModalInstanceCtrl = function ($scope, $modalInstance ,etudiant,etudiants) {
						EtudiantsModel.modules().get({idEtudiant:etudiant.numEtudiant},function (mods) {
    						$scope.modulesEtudiants = mods
    					});

						
						angular.forEach(etudiants, function (etu) {
							if(angular.equals(etu.etudiant.idEtudiant , etudiant.numEtudiant)){
								etu.etudiant.annee = etudiant.annee;
								etu.etudiant.specialite = etudiant.specialite;
								$scope.getEtudiant = etu.etudiant;
							}
						});
					  	$scope.cancel = function () {
					    	$modalInstance.dismiss('cancel');
					  	};
				};
			}/*end - controller*/

		}/*end - return*/
	})
	.directive('miniListeEtudiants', [function () {
		return {
			restrict: 'A',
			templateUrl:'partials/miniListeEtudiants.tmpl.html',
		};
	}]);


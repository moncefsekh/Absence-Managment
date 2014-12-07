angular.module('responsableApp.EnseignantsDrctv', [])
	.directive('listeEnseignants', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/listeEnseignants.tmpl.html',
			controller: function ($scope,$modal,EnseignantsModel) {
				$scope.openDetailsEnseignant = function (idEnseignant) {
				    var modalInstance = $modal.open({
				      	templateUrl: 'enseignant.details.html',
				      	controller: ModalInstanceDetailsCtrl,
				      	resolve:{
				      		enseignant:function () {
				      			var enseignant = new Object();
				      			enseignant.idEnseignant = idEnseignant;
				      			enseignant.specialite = $scope.specialite;
				      			return enseignant;
				      		},
				      		enseignants:function () {
				      			return $scope.getEnseignants;
				      		}
				      	}
				    });
			  	};

				var ModalInstanceDetailsCtrl = function ($scope, $modalInstance ,enseignants,enseignant) {
						EnseignantsModel.modules().get({idEnseignant:enseignant.idEnseignant},function (modules) {
    						$scope.modulesEnseignants = modules;
    					});
						angular.forEach(enseignants, function (ens) {
							if(ens.enseignant.idEnseignant ==enseignant.idEnseignant){
								ens.enseignant.specialite = enseignant.specialite;
								$scope.getEnseignant = ens.enseignant;
							}
						});
					  	$scope.cancel = function () {
					    	$modalInstance.dismiss('cancel');
					  	};
				};
				$scope.openFormEnseignant = function (typeModal,idEnseignant) {
				    var modalInstance = $modal.open({
				      	templateUrl: 'enseignant.form.html',
				      	controller: ModalInstanceFormCtrl,
				      	resolve:{
				      		enseignant:function () {
				      			var enseignant = new Object();
				      			enseignant.idEnseignant = idEnseignant;
				      			enseignant.specialite = $scope.specialite;
				      			return enseignant;
				      		},
				      		enseignants:function () {
				      			return $scope.getEnseignants;
				      		},
				      		type:function () {
				      			return typeModal;
				      		}
				      	}
				    });
			  	};

				var ModalInstanceFormCtrl = function ($scope, $modalInstance ,enseignants,enseignant,type) {

						$scope.typeModal = type;
						var oldEnseignant = {};
						$scope.getEnseignant = new Object();
						angular.forEach(enseignants, function (ens) {
							//console.log(ens);
							if(ens.enseignant.idEnseignant ==enseignant.idEnseignant){
								oldEnseignant = JSON.parse(JSON.stringify(ens.enseignant));
								ens.enseignant.specialite = enseignant.specialite;
								$scope.getEnseignant = ens.enseignant;

								//console.log($scope.getEnseignant);
							}
						});
						$scope.submit = function () {
							var newEnseignant = {
						  		nom:$scope.getEnseignant.nom,
						  		prenom:$scope.getEnseignant.prenom,
						  		dateNaissance:$scope.getEnseignant.dateNaissance,
								grade:$scope.getEnseignant.grade
					  		};
					  		if(angular.equals(type,"Modifier")){
					  			newEnseignant.idEnseignant= $scope.getEnseignant.idEnseignant;
					  			console.log(newEnseignant);
					  			EnseignantsModel.modifierEnseignant().put(newEnseignant);
					  		}else{
					  			console.log(newEnseignant);
								EnseignantsModel.ajouterEnseignant().post(newEnseignant);
					  		}
							$modalInstance.close();
					  		
						}
					  	$scope.cancel = function () {
					  		$scope.getEnseignant.nom = oldEnseignant.nom;
					  		$scope.getEnseignant.prenom = oldEnseignant.prenom;
					  		$scope.getEnseignant.dateNaissance = oldEnseignant.dateNaissance;
					  		$scope.getEnseignant.grade = oldEnseignant.grade;
					    	$modalInstance.dismiss('cancel');
					  	};
				};
			}/*end - controller*/
		};
	}).directive('miniListeEnseignants', function () {
		return {
			restrict: 'A',
			templateUrl:'partials/miniListeEnseignants.tmpl.html',
			scope:{},
			controller:function ($scope,$routeParams,$filter,EnseignantsModel,ngTableParams) {
				EnseignantsModel.actuellement().get(function (getEnseignants) {	
				//getEnseignants
					$scope.nombreEnseignants = getEnseignants.length;
					//console.log($scope.nombreEnseignants);
			        $scope.tableParams = new ngTableParams({        
			                sorting: {
			            		"id": 'asc'     // initial sorting
			        		}
			            }, {
			                total: 0, // length of data
			                getData: function($defer, params) {
			                		params.total(getEnseignants.length);// length of data
			                   	 	var orderedData = params.sorting() ?
		                            $filter('orderBy')(getEnseignants, params.orderBy()) :
		                            getEnseignants;
		                            $defer.resolve(orderedData);
			                }
			            });
		    	});
			}
		};
	});
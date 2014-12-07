angular.module('responsableApp.PedagogieDrctv', [])
	.directive('listeEns', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/listeEnseignements.tmpl.html',
			scope:false,
			link : function (scope,element,attrs) {
				scope.titreListe = attrs.titreListe;
			}
		};
	})
	.directive('listeGroupes', [function () {
		return {
			restrict: 'A',
			templateUrl:'partials/listeGroupes.tmpl.html',
			controller : function ($scope,$element,$modal,PedagogieModel) {
				var selectedGroupe;
				$scope.selectGroupe = function (event,id) {
					selectedGroupe = id;
					$(event.target).parent().parent().find('tr').removeClass('active');
					$(event.target).parent().addClass('active');
					$scope.selectedGroupe = true;
				}


			  	$scope.openGroupe = function (typeModal) {

				    var modalInstance = $modal.open({
				      	templateUrl: 'groupe.form.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		type:function () {
				      			return typeModal;
				      		},
				      		groupe:function () {
				      			var groupe = {
				      				id_Groupe : selectedGroupe,
				      				specialite :$scope.specialite
				      			}
				      			return groupe;
				      		},
				      		groupes: function () {
				      			return $scope.groupes;
				      		},
				      		annees:function () {
				      			return $scope.annees;
				      		},
				      		sections:function () {
				      			return $scope.sections;
				      		}
				      	}
				    });
				    modalInstance.result.then(function () {
					      $scope.refreshGroupes();
					});
			  	};

				var ModalInstanceCtrl = function ($scope, $modalInstance ,type,groupes,groupe,annees,sections) {
					  $scope.typeGroupeModal = type;
					  $scope.annees = annees;
					  $scope.sections = sections;
					  $scope.specialite = groupe.specialite;
					  $scope.getGroupe = {};
					  var oldGroupe={};
					  if(angular.equals(type,"Modifier")){
						  angular.forEach(groupes, function  (gr) {
						  		if(gr.groupe.id_Groupe == groupe.id_Groupe){
						  			oldGroupe = JSON.parse(JSON.stringify(gr.groupe));
						  			$scope.getGroupe = gr.groupe;
						  		}
						  });
					  }
					  $scope.submit = function () {
					  	var newGroupe = {
					  		codeGroupe:$scope.getGroupe.code_groupe,
					  		nomGroupe:$scope.getGroupe.nom_groupe,
					  		annee:$scope.getGroupe.annee,
							idSection:$scope.getGroupe.idSection
					  	};
					  	if(angular.equals(type,"Modifier")){
					  		newGroupe.id_Groupe = $scope.getGroupe.id_Groupe;
					  		console.log(newGroupe);
					  		PedagogieModel.modifierGroupe().put(newGroupe).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else{
					  		console.log(newGroupe);
					  		PedagogieModel.creerGroupe().post(newGroupe).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}
					  };

					  $scope.cancel = function () {
					  	$scope.getGroupe.id_Groupe=oldGroupe.id_Groupe;
				  		$scope.getGroupe.code_groupe=oldGroupe.code_groupe;
				  		$scope.getGroupe.nom_groupe=oldGroupe.nom_groupe;
				  		$scope.getGroupe.annee = oldGroupe.annee;
				  		$scope.getGroupe.idSection = oldGroupe.idSection;
					    $modalInstance.dismiss('cancel');
					  };
				};


		}
		};
	}])//////////////////////////////////////////////////////////////////////////////////////
	.directive('listeModules', [function () {
		return {
			restrict: 'A',
			templateUrl:'partials/listeModules.tmpl.html',
			controller : function ($scope,$element,$modal,PedagogieModel) {
				var selectedModule;
				$scope.selectModule = function (event,id) {
					selectedModule = id;
					$(event.target).parent().parent().find('tr').removeClass('active');
					$(event.target).parent().addClass('active');
					$scope.selectedModule = true;
				}


			  	$scope.openModule = function (typeModal) {

				    var modalInstance = $modal.open({
				      	templateUrl: 'module.form.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		type:function () {
				      			return typeModal;
				      		},
				      		module:function () {
				      			var module = {
				      				id_Module : selectedModule,
				      				specialite :$scope.specialite
				      			}
				      			return module;
				      		},
				      		modules: function () {
				      			return $scope.modules;
				      		},
				      		annees:function () {
				      			return $scope.annees;
				      		}
				      	}
				    });
				    modalInstance.result.then(function () {
					      $scope.refreshModules();
					});
			  	};



				var ModalInstanceCtrl = function ($scope, $modalInstance, type,modules,module,annees) {
					  $scope.typeModuleModal = type;
					  $scope.annees = annees;
					  $scope.getModule = new Object();
					  $scope.specialite = module.specialite;
					  var oldModule = new Object();
					  if(angular.equals(type,"Modifier")){
						  angular.forEach(modules, function  (mod) {
						  		if(mod.module.id_Module == module.id_Module){
						  			oldModule = JSON.parse(JSON.stringify(mod.module));
						  			$scope.getModule = mod.module;
						  		}
						  });
					  }
					  $scope.submit = function () {
					  	var newModule = {
					  		code_module:$scope.getModule.code_module,
					  		nom_module:$scope.getModule.nom_module,
							nbrCredit:$scope.getModule.nbrCredit,
							semestre:$scope.getModule.semestre,
							idSpecialite:$scope.getModule.idSpecialite
					  	};
					  	if(angular.equals(type,"Modifier")){
					  		newModule.id_Module = $scope.getModule.id_Module;
					  		console.log(newModule);
					  		PedagogieModel.modifierModule().put(newModule).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else{
					  		console.log(newModule);
					  		PedagogieModel.creerModule().post(newModule).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}
					  };

					  $scope.cancel = function () {
				  		$scope.getModule.code_module=oldModule.code_module;
				  		$scope.getModule.nom_module=oldModule.nom_module;
				  		$scope.getModule.nbrCredit=oldModule.nbrCredit;
				  		$scope.getModule.semestre=oldModule.semestre;
				  		$scope.getModule.annee=oldModule.annee;
				  		$scope.getModule.specialite = oldModule.specialite;
					    $modalInstance.dismiss('cancel');
					  };
				};
		}
	}
	}])//////////////////////////////////////////////////////////////////////////////////////////////////////////
	.directive('listeSections', [function () {
		return {
			restrict: 'A',
			templateUrl:'partials/listeSections.tmpl.html',
			controller : function ($scope,$element,$modal) {
				var selectedSection;
				$scope.selectSection = function (event,id) {
					selectedSection = id;
					$(event.target).parent().parent().find('tr').removeClass('active');
					$(event.target).parent().addClass('active');
					$scope.selectedSection = true;
				}


			  	$scope.openSection = function (typeModal) {

			  		console.log(selectedSection);
				    var modalInstance = $modal.open({
				      	templateUrl: 'section.form.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		type:function () {
				      			return typeModal;
				      		},
				      		section:function () {
				      			var section = {
				      				idSection : selectedSection,
				      				specialite :$scope.specialite
				      			}
				      			return section;
				      		},
				      		sections: function () {
				      			return $scope.sections;
				      		},
				      		annees:function () {
				      			return $scope.annees;
				      		}
				      	}
				    });
				    modalInstance.result.then(function () {
					      $scope.refreshSections();
					});
			  	};

				var ModalInstanceCtrl = function ($scope, $modalInstance,PedagogieModel, type,sections,section,annees) {
					  $scope.typeSectionModal = type;
					  $scope.annees = annees;
					  $scope.specialite = section.specialite;
					  $scope.getSection = {};
					  var oldSection={};
					  if(angular.equals(type,"Modifier")){
						  angular.forEach(sections, function  (sec) {
						  		if(sec.section.idSection == section.idSection){
						  			oldSection = JSON.parse(JSON.stringify(sec.section));
						  			$scope.getSection = sec.section;
						  		}
						  });
					  }
					  $scope.submit = function () {
					  	var newSection = {
					  		codeSection:$scope.getSection.codeSection,
					  		nomSection:$scope.getSection.nomsection,
					  		annne:$scope.getSection.annne,
							idSpecialite:$scope.getSection.idSpecialite
					  	};
					  	if(angular.equals(type,"Modifier")){
					  		newSection.idSection = $scope.getSection.idSection;
					  		console.log(newSection);
					  		PedagogieModel.modifierSection().put(newSection).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else{
					  		console.log(newSection);
					  		PedagogieModel.creerSection().post(newSection).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}
					  	
					  };

					  $scope.cancel = function () {
				  		$scope.getSection.codeSection=oldSection.codeSection;
				  		$scope.getSection.nomsection=oldSection.nomsection;
				  		$scope.getSection.annne = oldSection.annne;
				  		$scope.getSection.idSpecialite = oldSection.idSpecialite;
					    $modalInstance.dismiss('cancel');
					  };
				};
		}
	}
	}])//////////////////////////////////////////////////////////////////////////////////////
	.directive('listeSeances', [function () {
		return {
			restrict: 'A',
			templateUrl:'partials/listeSeances.tmpl.html',
			controller : function ($scope,$element,$modal,PedagogieModel) {
				
				var selectedSeance={};
				$scope.selectSeance = function (event,seance) {
					selectedSeance = seance;
					$(event.target).parent().parent().find('tr').removeClass('active');
					$(event.target).parent().addClass('active');
					$scope.selectedSeance = true;
				}




			  	$scope.openSeance = function (typeModal) {

				    var modalInstance = $modal.open({
				      	templateUrl: 'seance.form.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		type:function () {
				      			return typeModal;
				      		},
				      		seance:function () {
				      			var seance = {
				      				idSeance : selectedSeance.idSeance, 
				      				id_Dates : selectedSeance.id_Dates,
				      				specialite :$scope.specialite
				      			}
				      			return seance;
				      		},
				      		seances: function () {
				      			return $scope.seances;
				      		},
				      		annees:function () {
				      			return $scope.annees;
				      		},
				      		modules:function  () {
				      			return $scope.modules;
				      		},
				      		allEnseignants:function () {
				      			return $scope.allEnseignants;
				      		}
				      	}
				    });
				    modalInstance.result.then(function () {
					      $scope.refreshSeances();
					});
			  	};



				var ModalInstanceCtrl = function ($scope, $modalInstance,PedagogieModel, type,seances,seance,annees,modules,allEnseignants) {
					  $scope.typeSeanceModal = type;
					  $scope.modules = modules;
					  $scope.allEnseignants = allEnseignants;
					  $scope.annees = annees;
					  $scope.getSeance = new Object();
					  $scope.specialite = seance.specialite;
					  var oldSeance = new Object();
					  if(!angular.equals(type,"Créer")){
						  angular.forEach(seances, function  (sea) {
						  		if(sea.seance.id_Dates == seance.id_Dates){
						  			oldSeance = JSON.parse(JSON.stringify(sea.seance));
						  			$scope.getSeance = sea.seance;
						  			//console.log($scope.getSeance);
						  		}
						  });
					  }
					  $scope.submit = function () {
					  	
					  	if(angular.equals(type,"Modifier")){
					  		var newSeance = {
					  			idSeance:$scope.getSeance.id_Seance,
						  		type:$scope.getSeance.type,
						  		idModule:$scope.getSeance.idModule,
								idEnseignant:$scope.getSeance.idEnseignant,
								dateSeance:$scope.getSeance.date_seance,
								heureSeance:$scope.getSeance.heure_seance
					  		};
					  		console.log(newSeance);
					  		PedagogieModel.modifierSeance().put(newSeance).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else if(angular.equals(type,"Changer")){

					  		var newSeance = {
						  		id_Dates:$scope.getSeance.id_Dates,
						  		dateChangement:$scope.getSeance.date_changement,
								heureChangement:$scope.getSeance.heure_changement
							}
					  		console.log(newSeance);
					  		PedagogieModel.changerSeance().put(newSeance).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else{
					  		var newSeance = {
						  		type:$scope.getSeance.type,
						  		idModule:$scope.getSeance.idModule,
								idEnseignant:$scope.getSeance.idEnseignant,
								dateSeance:$scope.getSeance.date_seance,
								heureSeance:$scope.getSeance.heure_seance
					  		};
					  		console.log(newSeance);
					  		PedagogieModel.creerSeance().post(newSeance).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}
					  };

					  $scope.cancel = function () {
				  		$scope.getSeance.type=oldSeance.type;
				  		$scope.getSeance.idModule=oldSeance.idModule;
				  		$scope.getSeance.annee_specialite=oldSeance.annee_specialite;
				  		$scope.getSeance.idSpecialite=oldSeance.idSpecialite;
				  		$scope.getSeance.date_seance=oldSeance.date_seance;
				  		$scope.getSeance.heure_seance = oldSeance.heure_seance;
				  		$scope.getSeance.date_changement=oldSeance.date_changement;
				  		$scope.getSeance.heure_changement = oldSeance.heure_changement;
					    $modalInstance.dismiss('cancel');
					  };
				};
				
		}
	}
	}]);
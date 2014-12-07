angular.module('responsableApp.EtudiantsCtrl', [])
	.controller('EtudiantsCtrl',function ($scope,$rootScope,$routeParams,$filter,EtudiantsModel,ngTableParams,$timeout) {
		var scope = $rootScope;
		scope.setLoadingPage(true);

		var selectedSpecialite = $routeParams.specialite;
		var selectedAnnee = $routeParams.annee;

		$scope.specialite = angular.uppercase(selectedSpecialite);
		$scope.annee= angular.uppercase(selectedAnnee);
		//$scope.nombreAbsencesJour = EtudiantsModel.absencesJour().get({specialite:selectedSpecialite,annee:selectedAnnee});

		EtudiantsModel.etudiants().get({specialite:selectedSpecialite,annee:selectedAnnee},function  (getEtudiants) {
		
			$scope.getEtudiants = getEtudiants;
			//getEtudiants


	        $scope.tableParams = new ngTableParams({
	                page: 1,            // show first page
	                count: 10,           // count per page
	                sorting: {
	            		"etudiant.nom_Etudiant": 'asc'     // initial sorting
	        		}
	            }, {
	                total: 0, // length of data
	                getData: function($defer, params) {
	                		params.total(getEtudiants.length);// length of data
	                   	 	var orderedData = params.sorting() ?
                                $filter('orderBy')(getEtudiants, params.orderBy()) :
                                getEtudiants;
                            $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
	                }
	            });


    	
	    	$scope.getNombreAbsences = function (type) {
	    		var nombre = 0;
		    	angular.forEach(getEtudiants, function (etudiant) {
		    		var nombreAbsences = etudiant.etudiant.nombreAbsences;
		    		if(type==3)
		    			(etudiant.etudiant.nombreAbsences >= 3) ? nombre++ : nombre;
		    		else{
		    			(etudiant.etudiant.nombreAbsences == type) ? nombre++ : nombre;
		    		}   
		    	});
		    	return nombre;
	    	}

	    	$scope.getModulesEtudiants = function (idEtudiant) {
	    		EtudiantsModel.modules().get({idEtudiant:idEtudiant},function (modules) {
	    			$scope.modulesEtudiants = modules;
	    		})
	    	}

			EtudiantsModel.absencesJourSpecialite().get({specialite:'gl'},function (nombreAbsencesJour) {
				$scope.nombreAbsencesJour = nombreAbsencesJour.absence.nbr_absence;
			});

    	}).$promise.then(function () {
    		scope.setLoadingPage(false);
    	});


    	
    	
	
	});
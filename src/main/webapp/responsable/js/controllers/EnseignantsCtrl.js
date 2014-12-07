angular.module('responsableApp.EnseignantsCtrl', [])
	.controller('EnseignantsCtrl',function ($scope,$rootScope,$routeParams,$filter,EnseignantsModel,ngTableParams,$timeout) {
		var scope = $rootScope;
		scope.setLoadingPage(true);
		var localLoading = true;
		$scope.specialite = angular.uppercase($routeParams.specialite);
		$scope.nombreEnseignants = 0;

		EnseignantsModel.enseignants().get({specialite:$routeParams.specialite},function (getEnseignants) {
		
		$scope.getEnseignants = getEnseignants;
        	$scope.tableParams = new ngTableParams({
                page: 1,            // show first page
                count: 10,           // count per page
                sorting: {
            		"enseignant.nom": 'asc'     // initial sorting
        		}
            }, {
                total: 0, // length of data
                getData: function($defer, params) {
                		params.total(getEnseignants.length);// length of data
                   	 	var orderedData = params.sorting() ?
                            $filter('orderBy')(getEnseignants, params.orderBy()) :
                            getEnseignants;
                        $defer.resolve(orderedData.slice((params.page() - 1) * params.count(), params.page() * params.count()));
                }
            });
	    $scope.getNombreTypeEnseignants = function (type) {
			var nombre = 0;
	    	angular.forEach(getEnseignants, function (ens) {
				
	    		var grade = ens.enseignant.grade;
	    		if(angular.equals(grade,type))
	    			nombre++;
	    	});
	    	return nombre;
	    }
    	}).$promise.then(function () {
    		localLoading = localLoading || false;
    		scope.setLoadingPage(!localLoading);
    	});


		//+
    	EnseignantsModel.actuellement().get({specialite:$routeParams.specialite},function (getEnseignants) {	
			$scope.nombreEnseignants = getEnseignants.length;
		}).$promise.then(function () {
    		localLoading = localLoading || false;
    		scope.setLoadingPage(!localLoading);
    	});


		
	   

    	
	
	});
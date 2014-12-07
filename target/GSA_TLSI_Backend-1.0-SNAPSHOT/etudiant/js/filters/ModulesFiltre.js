angular.module('etudiantApp.ModulesFiltre', [])


	//$('.module').hide();
	//$('.module[semster="'++'"]').show();

.filter('filtreActualite',function () {
		return function (array,module) {
			if(!module){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(sea){

				if(angular.equals(sea.actualites.code_module, module)){
					res.push(sea);
				}
			});
			return res;
		}
	})

	.filter('filtreModules',function(){
		return function(array,semestre){
			if(!semestre){
				return array;
			}
			var res = [];
			angular.forEach(array, function(module){
				if(module.module.semestre.toLowerCase().indexOf(semestre) !== -1){
					res.push(module);
				}
			});
			return res;
		}

	//$('.module').hide();
	//$('.module[semster="'++'"]').show();

	}).filter('filtreSeance',function () {
		return function (array,module) {
			if(!module){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(sea){

				if(angular.equals(sea.absence.code_midule, module)){
					res.push(sea);
				}
			});
			return res;
		}
	})
	.filter('filtreAbsences',function () {
		return function (array,maxAbsence) {
			if(!maxAbsence){
				return array;
			}
			var res = [];
			angular.forEach(array, function(abs){
				console.log(abs.absence.nbr_absence );
				console.log(abs.absence.nbr_absence_just );
				console.log(maxAbsence );

				if(((abs.absence.nbr_absence - abs.absence.nbr_absence_just)>=0) || abs.absence.nbr_absence>5){
					res.push(abs);
				}
			});
			return res;
		}
	});
	
	
angular.module('enseignantApp.SeancesFilter', [])
	.filter('filtreSection',function () {
		return function (array,secCode) {
			if(!secCode){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(sea){
				if(angular.equals(sea.secCode, secCode)){
					res.push(sea);
				}
			});
			return res;
		}
	})
	.filter('filtreGroupe',function () {
		return function (array,groupeCode) {
			if(!groupeCode){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(sea){
				if(angular.equals(sea.groupeCode, groupeCode)){
					res.push(sea);
				}
			});
			return res;
		}
	})
	.filter('filtreEns',function () {
		return function (array,ensCode) {
			if(!ensCode){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(sea){
				if(angular.equals(sea.ensCode, ensCode)){
					res.push(sea);
				}
			});
			return res;
		}
	});
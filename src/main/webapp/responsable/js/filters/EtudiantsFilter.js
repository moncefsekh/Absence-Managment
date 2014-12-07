angular.module('responsableApp.EtudiantsFilter', [])
	.filter('filtreGroupe',function () {
		return function (array,codeGroupe) {
			if(!codeGroupe){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(etu){
				if(angular.equals(etu.etudiant.codeGroupe, codeGroupe)){
					res.push(etu);
				}
			});
			return res;
		}
	})
	.filter('filtreAnnee',function () {
		return function (array,annee) {
			if(!annee){	
				return array;
			}
			var res = [];
			angular.forEach(array, function(etu){
				if(angular.equals(etu.absence.anneeSpecilaite, annee)){
					res.push(etu);
				}
			});
			return res;
		}

	}).filter('unique', function () {

		  return function (items, filterOn) {

		    if (filterOn === false) {
		      return items;
		    }

		    if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
		      var hashCheck = {}, newItems = [];

		      var extractValueToCompare = function (item) {
		        if (angular.isObject(item) && angular.isString(filterOn)) {
		          return item[filterOn];
		        } else {
		          return item;
		        }
		      };

		      angular.forEach(items, function (item) {
		        var valueToCheck, isDuplicate = false;

		        for (var i = 0; i < newItems.length; i++) {
		          if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
		            isDuplicate = true;
		            break;
		          }
		        }
		        if (!isDuplicate) {
		          newItems.push(item);
		        }

		      });
		      items = newItems;
		    }
		    return items;
		  };
	});
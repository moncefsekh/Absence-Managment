angular.module('responsableApp.EtudiantsService', [])
	.service('EtudiantsModel',function ($http) {
		this.ok = function () {
			var etudiants = [];
			$http.get("etudiants.json").
    			success(function (data) {
    				angular.forEach(data, function (etudiant) {
			        	//console.log(data);
			        	etudiants.push(etudiant);
			        });
    		});
    		return etudiants;
		};

		this.getEtudiants = function () {
			var etudiants = [];
			var config = {
		        params: {
		            'rows': 15,
		            'id': '{randomNumber|100}',
		            'ln': '{firstName}',
		            'fn': '{lastName}',
		            'numCarte': '{zip}',
		            'delay':0,
		            'callback': "JSON_CALLBACK"
		        }
		    }
		    $http.jsonp("http://www.filltext.com", config, {})
		    	.success(function (data) {
			        angular.forEach(data, function (etudiant) {
			        	etudiants.push(etudiant);
			        });
		    	});
		    return etudiants;
		};
		this.getEnseignement = function (ensId) {
			var enseignement = [];
			var config = {
		        params: {
		            'rows': 3,
		            'code' : '{randomNumber|100}',
		            'module': '{firstName}',
		            'groupe': '{lastName}',
		            'chapitre':'{lorem|3}',
		            'titre':'{lorem|5}',
		            'etat': '{lorem|20}',
		            'date':'{date|1-3-2014,1-6-2014}',
		            'callback': "JSON_CALLBACK"
		        }
		    };
		    $http.jsonp("http://www.filltext.com", config, {})
		    	.success(function (data) {
			        angular.forEach(data, function (ens) {
			        	enseignement.push(ens);
			        });
		    	});
		  	return enseignement;
		};
	});
	
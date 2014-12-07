angular.module('enseignantApp.enseignementsService', [])
	.service('EnseignementsModel',function ($http) {
		
		this.getEnseignementsForGroupe = function () {
			var enseignements = [];
			var config = {
		        params: {
		            'rows': 2,
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
			        	enseignements.push(ens);
			        });
		    	});
		  	return enseignements;
		};
		this.getEnseignementsForModule = function (modCode) {
			var enseignements = [];
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
			        	enseignements.push(ens);
			        });
		    	});
		  	return enseignements;

		};
		this.getEnseignementsForSection = function (secCode) {
			var enseignements = [];
			var config = {
		        params: {
		            'rows': 4,
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
			        	enseignements.push(ens);
			        });
		    	});
		  	return enseignements;
		};

		this.getEnseignement = function (ensId) {
			var enseignement = [];
			var config = {
		        params: {
		            'rows': 1,
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
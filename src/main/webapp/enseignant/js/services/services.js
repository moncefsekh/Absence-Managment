angular.module('enseignantApp.services', [])
	.service('SectionsModel', function () {
		this.getSections = function () {
			return [
			{id: 1,code:'2GL',designation : "2eme année GL"},
			{id: 2,code:'3GL',designation : "3eme année GL"},
			{id: 3,code:'2SITW',designation : "2eme année SITW"}
			];
		}


		
	})
	.service('ModuleModel', function () {

		this.getModules = function () {
			return [
			{id:1,secCode:'3GL',designation : "TD - TQL ", code:'TQL_TD'},
			{id:2,secCode:'3GL',designation : "TP - GPL", code:'GPL_TP'},
			{id:3,secCode:'2GL',designation : "TD - POOJ", code:'POOJ_TD'},
			{id:5,secCode:'2SITW',designation : "TP - ORACLE 2", code:'ORACLE2_TP'},
			{id:4,secCode:'2SITW',designation : "TD - PS", code:'PS_TD'}
			];
		};
	})
	.service('GroupeModel', function () {
		this.getGroupes = function () {
			return [
				{id:1,designation : "Groupe 1",nombre:'23', modCode :'TQL_TD'},
				{id:2,designation : "Groupe 2",nombre:'21',  modCode :'TQL_TD'},
				{id:3,designation : "Groupe 3",nombre:'25', modCode  :'TQL_TD'},
				{id:4,designation : "Groupe 1",nombre:'21', modCode :'GPL_TP'},
				{id:5,designation : "Groupe 2",nombre:'23', modCode  :'GPL_TP'},
				{id:6,designation : "Groupe 3",nombre:'25', modCode  :'GPL_TP'},
				{id:7,designation : "Groupe 1",nombre:'21', modCode :'POOJ_TD'},
				{id:8,designation : "Groupe 2",nombre:'23', modCode  :'ORACLE2_TP'},
				{id:9,designation : "Groupe 3",nombre:'21', modCode  :'ORACLE2_TP'},
				{id:10,designation : "Groupe 2",nombre:'25', modCode  :'PS_TD'}
			];
		}
	});
	
    // $http.jsonp("http://www.filltext.com", config, {}).success(function (data) {
    //     $scope.users = data
    // });
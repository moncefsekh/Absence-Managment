var GestionAbsences = angular.module('GestionAbsences', ['ngAnimate']);

GestionAbsences.controller('Menu', function ($scope) {
	
	var sections = $scope.sections = [
	{
		id: 1,
		activate : false,
		designation : "2eme année GL",
		show:true
	},
	{
		id: 2,
		activate : false,
		designation : "3eme année GL",
		show:true
	},
	{
		id: 3,
		activate : false,
		designation : "2eme année SITW",
		show:true
	}
	];

	var modules = $scope.modules = [
	{
		id:1,
		id_section:2,
		activate : false,
		designation : "TQL",
		show:true
		
	},
	{
		id:2,
		id_section:2,
		activate : false,
		designation : "GPL",
		show:true
	},
	{
		id:3,
		id_section:1,
		activate : false,
		designation : "POOJ",
		show:true
	},
	{
		id:4,
		id_section:3,
		activate : false,
		designation : "ORACLE 2",
		show:true
	}
	];

	var seances = $scope.seances = [
	{
		id:1,
		id_module:1,
		activate : false,
		designation:"TD",
		show:true
	},
	{
		id:2,
		id_module:2,
		activate : false,
		designation:"TP",
		show:true
	},
	{
		id:3,
		id_module:3,
		activate : false,
		designation:"TD",
		show:true
	}
	];

	var groupes = $scope.groupes = [
	{
		id:1,
		id_seance:1,
		activate : false,
		designation:"Groupe 1",
		etudiants:21,
		show:true
	},
	{
		id:2,
		id_seance:2,
		activate : false,
		designation : "Groupe 3",
		etudiants:21,
		show:true
	},
	{
		id:3,
		id_seance:2,
		activate : false,
		designation:"Groupe 2",
		etudiants:21,
		show:true
	},
	{
		id:4,
		id_seance:2,
		activate : false,
		designation:"Groupe 1",
		etudiants:21,
		show:true
	}
	];

	var enseignements = $scope.enseignements = [
    {
        id : 1,
        id_groupe : 4, 
        id_seance : 2,
        etat:"Etat Lorem ipsum dolor sit amet, consectetur adipisicing elit. Esse, doloremque."
    }
    ];
	

	$scope.pressed = function(array) {
		var rep = 0;
		angular.forEach(array,function (element) {
			if(element.activate){
				rep = element.id;
			}
		});
		return rep;

	};

    $scope.active = function(id, array, nextArray, nextArray2) {
        angular.forEach(array, function(element) {
            element.activate = false;
            if (element.id === id) {
                element.activate = true;
            }
        });
        angular.forEach(nextArray, function(element) {
            element.activate = false;
        });
        angular.forEach(nextArray2, function(element) {
            element.activate = false;
        });
    };

	$scope.hideOthers = function () {
		angular.forEach(sections,function (element) {
			element.show = element.activate;
		});
		angular.forEach(modules,function (element) {
			element.show = element.activate;
		});
		angular.forEach(seances,function (element) {
			element.show = element.activate;
		});
		angular.forEach(groupes,function (element) {
			element.show = element.activate;
		});
	};

	$scope.initMenus = function () {
		angular.forEach(sections,function (element) {
			element.show =true;
			element.activate = false;
		});
		angular.forEach(modules,function (element) {
			element.show =true;
			element.activate = false;
		});
		angular.forEach(seances,function (element) {
			element.show =true;
			element.activate = false;
		});
		angular.forEach(groupes,function (element) {
			element.show =true;
			element.activate = false;
		});
		
	}
});
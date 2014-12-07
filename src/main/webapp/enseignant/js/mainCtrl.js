var enseignantApp = angular.module('enseignantApp', [
    'ngAnimate',
    'ngRoute',
    'ngResource',
    'ui.bootstrap',

    'enseignantApp.EtudiantsDrctv',
    'enseignantApp.SeancesDrctv',
    'enseignantApp.StatistiquesDrctv',

    'enseignantApp.EtudiantsFactory',
    'enseignantApp.SeancesFactory',
    'enseignantApp.SectionsFactory',
    'enseignantApp.GroupesFactory',

    'enseignantApp.SeancesCtrl',
    'enseignantApp.EtudiantsCtrl',
    'enseignantApp.StatistiquesCtrl',

    'enseignantApp.SeancesFilter'
])
.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'SeancesCtrl',
            templateUrl: 'partials/enseignements.html'  
        })
        .when('/etudiants/:specCode/:annee/:sec_code/:groupeCode/:seaCode/:idDate',{
            templateUrl: 'partials/etudiants.html',
            controller: 'EtudiantsCtrl'
        })
        .when('/statistiques', {
            templateUrl: 'partials/statistiques.html',
            controller: 'StatistiquesCtrl'
        })
        .when('/etudiants', {
            controller: 'SeancesCtrl',
            templateUrl: 'partials/enseignements.html'  
        })
        .when('/etudiants/:secCode', {
            controller: 'SeancesCtrl',
            templateUrl: 'partials/enseignements.html'  
        })
        .when('/etudiants/:secCode/:groupeCode', {
            controller: 'SeancesCtrl',
            templateUrl: 'partials/enseignements.html'  
        })
        .when('/etudiants/:secCode/:groupeCode/:ensCode', {
            controller: 'SeancesCtrl',
            templateUrl: 'partials/enseignements.html'  
        });

}]).run(function ($rootScope,$resource) {
   
    $rootScope.logout=function () {
        $resource('../rest/service/logoutEnseignant', {}, {
            post: {method:'POST', isArray:false}
        }).post(function () {
            $rootScope.checkCompte();
        });
    }
    $rootScope.checkCompte = function () {
        $resource('../rest/service/getInfoEnseignant', {}, {
            get: {method:'GET', isArray:false}
        }).get(function (info) {
            if(angular.isUndefined(info.enseignant)){
                window.location = window.location.origin +'/GSA_TLSI_Backend/';
            }
        });
    };
    $rootScope.checkCompte();
});

var etudiantApp = angular.module('etudiantApp', [
    'ngAnimate',
    'ngRoute',
    'ngResource',
    'ui.bootstrap',

    'etudiantApp.AbsencesDrctv',
    'etudiantApp.ActualitesDrctv',

    'etudiantApp.AbsencesFactory',
    'etudiantApp.ActualitesFactory',
    'etudiantApp.EtudiantFactory',

    'etudiantApp.ActualitesCtrl',
    'etudiantApp.AbsencesCtrl',

    'etudiantApp.ModulesFiltre'
])
.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'ActualitesCtrl',
            templateUrl: 'partials/actualites.html'  
        })
        .when('/actualites', {
            controller: 'ActualitesCtrl',
            templateUrl: 'partials/actualites.html'
        })
        .when('/actualites/:modCode', {
            controller: 'ActualitesCtrl',
            templateUrl: 'partials/actualites.html'
        })
        .when('/absences', {
            controller: 'AbsencesCtrl',
            templateUrl: 'partials/absences.html'  
        })        
        .when('/absences/:modCode', {
            controller: 'AbsencesCtrl',
            templateUrl: 'partials/absences.html'  
        })
        .when('/reglements', {
            templateUrl: 'partials/reglements.html',
            controller: 'ActualitesCtrl'
        });
        //.otherwise();

}]).run(function ($rootScope,EtudiantModel,$resource) {
    $rootScope.checkCompte = function () {
        EtudiantModel.InfoEtudiant().get(function (etudiant) {
           console.log(etudiant);
            if(angular.isDefined(etudiant.infoEtudiant)){
                $rootScope.infos = etudiant.infoEtudiant;
                console.log($rootScope.infos);
            }else{
                window.location = window.location.origin +'/GSA_TLSI_Backend/';
            }
            
        });
    };
    $rootScope.checkCompte();

    $rootScope.logout=function () {
        $resource('../rest/service/logoutEtudiant', {}, {
            post: {method:'POST', isArray:false}
        }).post(function () {
            $rootScope.checkCompte();
        });
    }
    
        
    });
    

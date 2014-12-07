var responsableApp = angular.module('responsableApp', [
    'ngAnimate',
    'ngRoute',
    'ngResource',
    'ngTable',
    'dx',
    'ui.bootstrap',
    
    'responsableApp.ActivitesDrctv',
    'responsableApp.EnseignantsDrctv',
    'responsableApp.PedagogieDrctv',
    'responsableApp.EtudiantsDrctv',
    'responsableApp.StatistiquesDrctv',
    'responsableApp.WidgetDrctv',
    'responsableApp.ParametresDrctv',

    'responsableApp.EnseignantsFactory',
    'responsableApp.EtudiantsFactory',
    'responsableApp.PedagogieFactory',
    'responsableApp.StatistiquesFactory',
    'responsableApp.ParametresFactory',
    'responsableApp.AgentsFactory',

    'responsableApp.EnseignantsCtrl',
    'responsableApp.PedagogieCtrl',
    'responsableApp.EtudiantsCtrl',
    'responsableApp.ParametresCtrl',
    'responsableApp.StatistiquesCtrl',
    'responsableApp.TableauDeBordCtrl',

    'responsableApp.EtudiantsFilter'
])
.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'partials/tableauDeBord.html',
            controller: 'TableauDeBordCtrl'
        })
        .when('/pedagogie/:specialite', {
            templateUrl: 'partials/pedagogie.html',  
            controller: 'PedagogieCtrl'
        })
        .when('/etudiants/:specialite/:annee', {
            templateUrl: 'partials/etudiants.html',
            controller: 'EtudiantsCtrl'
        })
        .when('/enseignants/:specialite', {
            templateUrl: 'partials/enseignants.html',
            controller: 'EnseignantsCtrl'
        })
        .when('/parametres', {
            templateUrl: 'partials/parametres.html',
            controller: 'ParametresCtrl'
        }).when('/statistiques/:specialite', {
            templateUrl: 'partials/statistiques.html',
            controller: 'StatistiquesCtrl'
        });
}]).run(function (ParametresModel,PedagogieModel,$rootScope,$resource) {
    $rootScope.AppParams = {};
    $rootScope.loadingPage=true;
    $rootScope.getSpecialites = [];


    $rootScope.logout=function () {
        $resource('../rest/service/logoutResponsable', {}, {
            post: {method:'POST', isArray:false}
        }).post(function () {
            $rootScope.checkCompte();
        });
    }
    $rootScope.checkCompte = function () {
        $resource('../rest/service/getInfoResponsable', {}, {
            get: {method:'GET', isArray:false}
        }).get(function (info) {
            if(info.absence.id_Compte==0){
                window.location = window.location.origin +'/GSA_TLSI_Backend/';
            }
        });
    };
    $rootScope.checkCompte();



    $rootScope.setLoadingPage = function(bool) {
        $rootScope.loadingPage = $rootScope.loadingPage && bool;
    };

    $rootScope.addDate = function (date,days) {
        var toDate = new Date(date);
        var toDays = days * 24*60*60*1000; 
        var newDate = new Date(toDate.getTime()+toDays);
        var month = newDate.getMonth()+1<10 ? '0'+(newDate.getMonth()+1) : newDate.getMonth()+1;
        return newDate.getFullYear()+'-'+month+'-'+newDate.getDate();
    }


    $rootScope.getAppParams = ParametresModel.get(function (parametres) {
        $rootScope.AppParams={
           // nomPrenom:parametres.parametres.nomPrenom,
            nbrAbsence : parametres.parametres.nbrAbsence,
            nbrJustifier:parametres.parametres.nbrJustifier,
            dateDebutSemestre1 : parametres.parametres.dateDebutSemestre1,
            dateDebutSemestre2 : parametres.parametres.dateDebutSemestre2,
            dateFinSemestre1 : parametres.parametres.dateFinSemestre1,
            dateFinSemestre2 : parametres.parametres.dateFinSemestre2
        };
        console.log($rootScope.AppParams);
    });
    $rootScope.getAllSpecialites = PedagogieModel.specialites().get(function (specialites) {
        angular.forEach(specialites, function  (spe) {
            $rootScope.getSpecialites.push(spe.specialite);
        });
    });
});

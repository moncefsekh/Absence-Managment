angular.module('responsableApp.PedagogieCtrl', [])
	.controller('PedagogieCtrl', function ($scope,$rootScope,$routeParams,PedagogieModel,EnseignantsModel) {
        var scope = $rootScope;
        var localLoading = 0;
        var maxLoading = 7;
        scope.setLoadingPage(true);

        var specialite = $routeParams.specialite;
        $scope.specialite = angular.uppercase(specialite);

        

        $scope.refreshSpecialites = function () {
            PedagogieModel.specialites().get(function (specialites) {
                $scope.specialites = specialites;
            }).$promise.then(function () {
                localLoading = localLoading+1;
                (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
            });
        };
        $scope.refreshSpecialites();

        




        $scope.refreshAnnees = function () {
            PedagogieModel.annees().get(function (annees) {
                $scope.annees = annees;
            }).$promise.then(function () {
                localLoading = localLoading+1;
                (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
            });
        };
        $scope.refreshAnnees();






        $scope.refreshModules = function () {
            PedagogieModel.modules().get({specialite:specialite},function (modules) {
                $scope.modules = modules;
                $scope.nombreModulesParAnnee = function (annee) {
                    var nombre = 0;
                    angular.forEach(modules, function (mod) {
                        var anneeDeModule = mod.module.annee;
                        if(angular.equals(annee, anneeDeModule)){
                            nombre++;
                        }
                    });
                    return nombre;
                };
            }).$promise.then(function () {
                localLoading = localLoading+1;
                (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
            });
        };
        $scope.refreshModules();






        $scope.refreshGroupes = function () {
            PedagogieModel.groupes().get({specialite:specialite},function (groupes) {
                $scope.groupes = groupes;
                $scope.nombreGroupesParAnnee = function (annee) {
                    var nombre = 0;
                    angular.forEach(groupes, function (gr) {
                        var anneeDeGroupe = gr.groupe.annee;
                        if(angular.equals(annee, anneeDeGroupe)){
                            nombre++;
                        }
                    });
                    return nombre;
                };
            }).$promise.then(function () {
                localLoading = localLoading+1;
                (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
            });
        };
        $scope.refreshGroupes();






        $scope.refreshSections = function () {
            PedagogieModel.sections().get({specialite:specialite},function (sections) {
                $scope.sections = sections;
                $scope.nombreSectionsParAnnee = function (annee) {
                    var nombre = 0;
                    angular.forEach(sections, function (sec) {
                        var anneeDeSection = sec.section.annne;
                        if(angular.equals(annee, anneeDeSection)){
                            nombre++;
                        }
                    });
                    return nombre;
                };
            }).$promise.then(function () {
               localLoading = localLoading+1;
                (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
            });
        };
        $scope.refreshSections();






        $scope.refreshSeances = function () {
            PedagogieModel.seances().get({specialite:specialite},function (seances) {
                $scope.seances = seances;
            }).$promise.then(function () {
                localLoading = localLoading+1;
                (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
            });
        };
        $scope.refreshSeances();




        EnseignantsModel.allEnseignants().get(function (enseignants) {
            $scope.allEnseignants = enseignants;
        }).$promise.then(function () {
            localLoading = localLoading+1;
            (localLoading == maxLoading)?scope.setLoadingPage(false) : scope.setLoadingPage(true);
        });		
	});
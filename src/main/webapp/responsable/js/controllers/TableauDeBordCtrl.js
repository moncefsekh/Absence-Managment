angular.module('responsableApp.TableauDeBordCtrl', [])
	.controller('TableauDeBordCtrl', function ($scope,$rootScope,$routeParams,StatistiquesModel,EtudiantsModel,EnseignantsModel,PedagogieModel){
		var scope = $rootScope;
		scope.setLoadingPage(true);

		scope.getAllSpecialites.$promise.then(function () {
	
				$scope.refreshStatistiques = function (specialite) {
					
					$scope.getStatistiques = StatistiquesModel.absencesSpecialite().get({specialite:specialite},function (stat) {
						console.log("specialite  "+stat);
						$scope.absencesSpecialite = [];
						angular.forEach(stat, function (abs) {
							$scope.absencesSpecialite.push(abs.absence);
						});
						$scope.typeChart = 'bar';
						$scope.argumentField = 'date_absence';
						$scope.typeSeries = [
							[
								{valueField:'nbr_absence', name:specialite}
							],
							[
								{valueField:'nbr_absence', name:specialite, type:'splineArea',color:'#6fcfa5'},
								{valueField:'nbr_absence', name:specialite, type:'spline',color:'#6fcfa5'}
							]
							]	
					});
				}
				$scope.refreshStatistiques(scope.getSpecialites[0].code_specialite);



				$scope.getNombreAbsencesJour = function (specialite) {
					EtudiantsModel.absencesJourSpecialite().get({specialite:specialite},function (nombreAbsencesJour) {
						$scope.nombreAbsencesJour = nombreAbsencesJour.absence.nbr_absence;
						$scope.getSpecialiteJour = angular.uppercase(specialite);
					}).$promise.then(function () {
						scope.setLoadingPage(false);
					});
				};

				$scope.getNombreAbsencesJour(scope.getSpecialites[0].code_specialite);

				$scope.getPourcentageAbsences = function (specialite) {
					EtudiantsModel.absencesJourSpecialite().get({specialite:specialite},function (nombreAbsencesJour) {
						EtudiantsModel.nombreTotalEtudiants().get({specialite:specialite},function (nombre) {
							$scope.pourcentageAbsences=(nombreAbsencesJour.absence.nbr_absence*100)/nombre.etudiant.nbrEtudiants;
							$scope.getSpecialitePourcentage = angular.uppercase(specialite);
						});
					}).$promise.then(function () {
						scope.setLoadingPage(false);
					});
				};
				$scope.getPourcentageAbsences(scope.getSpecialites[0].code_specialite);

				

				$scope.getNombreEnseingant= function (specialite) {
					EnseignantsModel.enseignants().get({specialite:specialite},function (enseignant) {
						$scope.nombreEnseingant = enseignant.length;
						$scope.getSpecialiteEnseignants = angular.uppercase(specialite);
					}).$promise.then(function () {
						scope.setLoadingPage(false);
					});
				}
				$scope.getNombreEnseingant(scope.getSpecialites[0].code_specialite);


		});



});
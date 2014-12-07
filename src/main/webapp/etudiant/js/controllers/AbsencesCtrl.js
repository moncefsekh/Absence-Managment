angular.module('etudiantApp.AbsencesCtrl', [])
	.controller('AbsencesCtrl',function ($scope,AbsencesModel) {
		$scope.datas="AbsencesCtrl";
		$scope.absences = AbsencesModel.absences().get({id:'1'});
		$scope.exclusions = AbsencesModel.etat_exclusion().get({id:'1'});

		$scope.set_reclamation = function(){
			AbsencesModel.reclamation().post({reclamation:$scope.reclamation})
		}
		$scope.getModule = function (code) {
			$scope.titreListe = 'Mes absences dans le module de'+code;
			$scope.absences = AbsencesModel.absences().get({id:'1'});
		}
	});
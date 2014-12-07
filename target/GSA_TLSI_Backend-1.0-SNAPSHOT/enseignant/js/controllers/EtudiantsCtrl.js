angular.module('enseignantApp.EtudiantsCtrl', [])
	.controller('EtudiantsCtrl',function ($scope,$routeParams,$timeout,EtudiantsModel,SeancesModel) {
		
		var etudiants ;
		var idsAbsences = [];
		$scope.annee = $routeParams.annee ;
		$scope.groupeCode = $routeParams.groupeCode;
		$scope.code_spec = $routeParams.specCode;
		$scope.code_sec = $routeParams.sec_code;
		$scope.nombreAbsence = 0;
		$scope.selectedSeaCode = $routeParams.seaCode;

		var seance = SeancesModel.seance().get({id:$routeParams.idDate});
		$scope.seance = seance;

		EtudiantsModel.etudiants()
		.get({code_spec:$routeParams.specCode,annee:$routeParams.annee,code_sec:$routeParams.sec_code,code_gr:$routeParams.groupeCode,idSeance:$routeParams.seaCode,idDate:$routeParams.idDate},function  (etudiant) {
		
			var etus = [];
			
		    EtudiantsModel.absents().get({idSeance:$routeParams.seaCode,idDate:$routeParams.idDate},function (abs) {
		    	angular.forEach(abs, function (d) {
		            idsAbsences.push(d.etudiant.idEtudiant);
		            //console.log(idsAbsences);
		        });

		        angular.forEach(etudiant, function (d) {
			        if(idsAbsences.indexOf(d.etudiant.num_Carte)>=0){
			        	//console.log(d.etudiant.num_Carte);
            			d.etudiant.absent = true;
            		}else{
            			d.etudiant.absent = false;
            		}
            		etus.push(d);
			    });
		    

		    var absences = [];
			
			for(var i = 0;i<idsAbsences.length;i++){
				//console.log("id"+id);
				absences[i] = {id:idsAbsences[i]};
				console.log(absences);
			}
			$scope.marqueAbsence = function (numEtudiant,present) {
			//console.log(absences);
			if(present){
				for (var i = 0; i < absences.length; i++) {
						if(absences[i].id == numEtudiant){
							absences.splice(i,1);
							$scope.nombreAbsence = absences.length;
							return;
						}
					};
				}else{
					absences.push({id:numEtudiant});
					$scope.nombreAbsence = absences.length;
				}

				
			};
			var setAbsences = function () {
				var idsEtudiants = "";
				angular.forEach(absences,function(idEtudiant){
					idsEtudiants += idEtudiant.id + '_';
					console.log(absences)
				});
				var dateAbsence = (seance.seance.date_changement)?seance.seance.date_changement:seance.seance.date_seance;
				var heureAbsence = (seance.seance.heure_changement)?seance.seance.heure_changement:seance.seance.heure_seance;
				//console.log(dateAbsence +" "+heureAbsence);
				EtudiantsModel.absences().post({idSeance :$scope.selectedSeaCode,dateSeance:(dateAbsence +" "+heureAbsence),ids:idsEtudiants,idDate:$routeParams.idDate})
			}

			$scope.envoiAbsences = function () {
				$scope.timeInS = 1;
				var count = function () {
					$scope.timeInS -= 1;
					$timeout(count,1000);
				};
				$timeout(count,1000);

				$timeout(function () {
					count = null;
					setAbsences();
					$scope.messageConfim = !$scope.messageConfim;
				},1000);
				$scope.messageConfim = !$scope.messageConfim;
				
			};



			});
		}).$promise.then(function (d) {
			$scope.etudiants = d;

		});


		
		
		
		



		


		
		

		
		

		
	});

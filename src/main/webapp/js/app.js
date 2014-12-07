var App = angular.module('App', [
	'ngResource',
    'App.AppFactory',
    'ui.bootstrap'
    
])
.controller('AppCtrl',function ($scope,AppModel,$location) {
  	AppModel.InfoEtudiant().get(function (etudiant) {	
		if(angular.isDefined(etudiant.infoEtudiant)){
			window.location = document.location.origin +'/GSA_TLSI_Backend/etudiant/';
        }else{
        	window.location = document.location.origin +'/GSA_TLSI_Backend/authentification.html';
        }
	});

	AppModel.InfoEnseignant().get(function (enseignant) {	
		if(angular.isDefined(enseignant.enseignant)){
			window.location = document.location.origin +'/GSA_TLSI_Backend/enseignant/';
        }else{
        	window.location = document.location.origin +'/GSA_TLSI_Backend/authentification.html';
        }
	});
	
	AppModel.InfoResponsable().get(function (responsable) {	
		if(angular.isDefined(responsable.absence) && responsable.absence.id_Compte==1){
			window.location = document.location.origin +'/GSA_TLSI_Backend/responsable/';
        }else{
        	window.location = document.location.origin +'/GSA_TLSI_Backend/authentification.html';
        }
	});
	
	
})
.controller('AuthCtrl',function ($scope,AppModel,$location) {
    $scope.user={};

    AppModel.InfoEtudiant().get(function (etudiant) {	
		if(angular.isDefined(etudiant.infoEtudiant)){
			window.location = document.location.origin +'/GSA_TLSI_Backend/etudiant/';
        }
	});

	AppModel.InfoEnseignant().get(function (enseignant) {	
		if(angular.isDefined(enseignant.enseignant)){
			window.location = document.location.origin +'/GSA_TLSI_Backend/enseignant/';
        }
	});
	
	AppModel.InfoResponsable().get(function (responsable) {	
		if(angular.isDefined(responsable.absence) && responsable.absence.id_Compte==1){
			window.location = document.location.origin +'/GSA_TLSI_Backend/responsable/';
        }
	});

    $scope.captcha = ['bxkofx','ea7az8','4rm09g','iar4bw','vwp3oe','qxum1y','hk35xm','lpjncw','2kyvwd','bjuc82','mtpvr3','qxbetk','sfhtmq']
	$scope.idCaptcha = Math.floor((Math.random() * 13));
   
    
    $scope.submit = function () {
    	var user = {
    		typeUser:$scope.user.type,
			nomUtilisateur:$scope.user.nomUtilisateur,
			motDePasse:$scope.user.motDePasse
		}
		var userCaptcha = angular.lowercase($scope.userCaptcha);
		
		if(angular.equals($scope.captcha[$scope.idCaptcha],userCaptcha)){
				AppModel.authentificate().post(user,function (u) {}).$promise.then(function () {

					if(angular.equals($scope.user.type,'authEtudiant')){
						AppModel.InfoEtudiant().get(function (etudiant) {
						if(angular.isDefined(etudiant.infoEtudiant)){
							window.location = document.location.origin +'/GSA_TLSI_Backend/etudiant/';
		                }else{
		                	$scope.idCaptcha = Math.floor((Math.random() * 13));
							$scope.openMessage('danger',"Erreur d'authentification","Le nom d'utilisateur ou le mot de passe est incorrect",'warning');
		                }
						})
					};


					if(angular.equals($scope.user.type,'authEnseignant')){
						AppModel.InfoEnseignant().get(function (enseignant) {
						if(angular.isDefined(enseignant.enseignant)){
							window.location = document.location.origin +'/GSA_TLSI_Backend/enseignant/';
		                }else{
		                	$scope.idCaptcha = Math.floor((Math.random() * 13));
							$scope.openMessage('danger',"Erreur d'authentification","Le nom d'utilisateur ou le mot de passe est incorrect",'warning');
		                }
						})
					};

					
					if(angular.equals($scope.user.type,'authResponsable')){
						AppModel.InfoResponsable().get(function (responsable) {
						console.log("info resp");
						console.log(responsable);
						if(angular.isDefined(responsable.absence) && responsable.absence.id_Compte==1){
							window.location = document.location.origin +'/GSA_TLSI_Backend/responsable/';
		                }else{
		                	$scope.idCaptcha = Math.floor((Math.random() * 13));
							$scope.openMessage('danger',"Erreur d'authentification","Le nom d'utilisateur ou le mot de passe est incorrect",'warning');
		                }
						})
					};
					


				});
			
		}else{
			$scope.idCaptcha = Math.floor((Math.random() * 13));
			$scope.openMessage('warning',"Erreur de saisi","Le code de captcha est incorrect",'warning');
		}
    }
}).
directive('wMessage', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/message.tmpl.html',
			link: function (scope,element,attrs) {
 			},
 			controller : function ($scope,$element,$modal) {

 				$scope.openMessage = function (typeModal,titre,texte,icone) {
 					console.log("fff");

				    var modalInstance = $modal.open({
				      	templateUrl: 'message.alert.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		modalParams:function  () {
				      			return {
				      				typeModal:typeModal,
				      				titre:titre,
				      				texte:texte,
				      				icone:icone
				      			}
				      		}
				      	}
				    });

				    modalInstance.result.then(function () {
					      
					});
			  	};

				var ModalInstanceCtrl = function ($scope, $modalInstance,modalParams) {
						$scope.modalParams = {
				      		typeModal:modalParams.typeModal,
				      		titre:modalParams.titre,
				      		texte:modalParams.texte,
				      		icone:modalParams.icone,
				      	};			      		
					  $scope.submit = function () {
					  	   $modalInstance.close();
					  	
					  };

					  $scope.cancel = function () {
					    $modalInstance.dismiss('cancel');
					  };
			  	
				};
			}
		};
	});

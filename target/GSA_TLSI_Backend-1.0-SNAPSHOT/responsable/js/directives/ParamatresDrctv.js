angular.module('responsableApp.ParametresDrctv', [])
	.directive('parametres', function () {
		return {
			restrict: 'EA',
			templateUrl : 'partials/formParametres.tmpl.html',
			link : function (scope,element,attrs) {
				
			}
		};
	}).directive('listeAgents', [function () {
		return {
			restrict: 'A',
			templateUrl:'partials/listeAgents.tmpl.html',
			controller : function ($scope,$element,$modal,AgentsModel) {
				var selectedAgent;
				$scope.selectAgent = function (event,id) {
					selectedAgent = id;
					$(event.target).parent().parent().find('tr').removeClass('active');
					$(event.target).parent().addClass('active');
					$scope.selectedAgent = true;
				}


			  	$scope.openAgent = function (typeModal) {

				    var modalInstance = $modal.open({
				      	templateUrl: 'agent.form.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		type:function () {
				      			return typeModal;
				      		},
				      		agent:function () {
				      			var agent = {
				      				idAgent : selectedAgent
				      			}
				      			return agent;
				      		},
				      		agents: function () {
				      			return $scope.agents;
				      		}
				      	}
				    });
				  	modalInstance.result.then(function () {
					      $scope.refreshAgents();
					});
			  	};


				var ModalInstanceCtrl = function ($scope, $modalInstance ,type,agents,agent) {
					  $scope.typeAgentModal = type;
					  $scope.getAgent = {};
					  var oldAgent={};
					  if(angular.equals(type,"Modifier")){
						  angular.forEach(agents, function  (ag) {
						  		if(ag.agent.idAgent == agent.idAgent){
						  			oldAgent = JSON.parse(JSON.stringify(ag.agent));
						  			$scope.getAgent = ag.agent;
						  		}
						  });
					  }
					  $scope.submit = function () {
					  	var newAgent = {
					  		nom:$scope.getAgent.nom,
					  		prenom:$scope.getAgent.prenom
					  	};
					  	if(angular.equals(type,"Modifier")){
					  		newAgent.idAgent = $scope.getAgent.idAgent;
					  		console.log(newAgent);
					  		AgentsModel.modifierAgent().put(newAgent).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else{
					  		console.log(newAgent);
					  		AgentsModel.creerAgent().post(newAgent).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}
					  	
					    
					  };

					  $scope.cancel = function () {
				  		$scope.getAgent.nom = oldAgent.nom;
				  		$scope.getAgent.prenom = oldAgent.prenom;
					    $modalInstance.dismiss('cancel');
					  };
				};


		}
		};
	}]);
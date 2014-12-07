angular.module('responsableApp.WidgetDrctv', [])
	.directive('widget', function ($timeout) {
		return {
			restrict: 'A',
			templateUrl: 'partials/widget.tmpl.html',
			scope:{
				wInfo:'@',
				wTitre:'@',
				wCur:'@',
				wIcon:'@',
				wDescription:'@'
			},
			transclude: true,
			link: function (scope,element,attrs) {
				
 			}
		};
	})
	.directive('menuSpecialites', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/menuSpecialites.tmpl.html',
			scope:{},
			link: function (scope,element,attrs) {
				scope.menuTitre       = attrs.menuTitre ;
 			},
 			controller:function ($scope,$routeParams,$location) {
 				$scope.specialite = angular.uppercase($routeParams.specialite);
 				var path = $location.path();
 				console.log(path);
 				$scope.path = "/"+path.split("/")[1];
 				console.log($scope.path);
 				
 			}
		};
	})
	.directive('menuSpecialitesAnnees', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/menuSpecialitesAnnees.tmpl.html',
			scope:{},
			link: function (scope,element,attrs) {
				scope.menuTitre       = attrs.menuTitre ;
 			},
 			controller:function ($scope,$routeParams) {
 				$scope.specialite = angular.uppercase($routeParams.specialite);
 			}
		};
	})
	.directive('menuEnseignements', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/menuEnseignements.tmpl.html',
			link: function (scope,element,attrs) {
				scope.menuTitre       = attrs.menuTitre
 			},
 			controller : function ($scope,$element,$modal) {

 				$scope.openSpecialite = function (typeModal,selectedSpecialite) {

				    var modalInstance = $modal.open({
				      	templateUrl: 'specialite.form.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		type:function () {
				      			return typeModal
				      		},
				      		selectedSpecialite:function () {
				      			return selectedSpecialite;
				      		},
				      		specialites: function () {
				      			return $scope.specialites;
				      		}
				      	}
				    });

				    modalInstance.result.then(function () {
					      $scope.refreshSpecialites();
					});
			  	};

				var ModalInstanceCtrl = function ($scope, $modalInstance ,type,specialites,PedagogieModel,selectedSpecialite) {
					  $scope.typeSpecialiteModal = type;
					  $scope.specialite = selectedSpecialite;
					  $scope.getSpecialite = {};
					  var oldSpecialite={};
					  if(angular.equals(type,"Modifier")){
						  angular.forEach(specialites, function  (spe) {
						  		if(spe.specialite.id_Specialite == selectedSpecialite){
						  			oldSpecialite = JSON.parse(JSON.stringify(spe.specialite));
						  			$scope.getSpecialite = spe.specialite;
						  		}
						  });
					  }
					  $scope.submit = function () {
					  	var newSpecialite = {
					  		codeSpecialite:$scope.getSpecialite.code_specialite,
					  		nomSpecialite:$scope.getSpecialite.nom_specialite
					  	};
					  	if(angular.equals(type,"Modifier")){
					  		newSpecialite.id_Specialite = $scope.getSpecialite.id_Specialite;
					  		console.log(newSpecialite);
					  		PedagogieModel.modifierSpecialite().put(newSpecialite).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}else{
					  		console.log(newSpecialite);
					  		PedagogieModel.creerSpecialite().post(newSpecialite).$promise.then(function () {
					  			$modalInstance.close();
					  		});
					  	}
					  	
					  };

					  $scope.cancel = function () {
				  		$scope.getSpecialite.code_specialite=oldSpecialite.code_specialite;
				  		$scope.getSpecialite.nom_specialite=oldSpecialite.nom_specialite;
					    $modalInstance.dismiss('cancel');
					  };
			  	
				};
			}
		};
	}).
	directive('wMessage', function () {
		return {
			restrict: 'A',
			templateUrl: 'partials/message.tmpl.html',
			link: function (scope,element,attrs) {
				scope.titreMessage = attrs.titre;
				scope.texte = attrs.texte;
				scope.icone = attrs.icone;

 			},
 			controller : function ($scope,$element,$modal) {

 				$scope.openMessage = function (typeModal) {

				    var modalInstance = $modal.open({
				      	templateUrl: 'message.alert.html',
				      	controller: ModalInstanceCtrl,
				      	resolve:{
				      		modalParams:function  () {
				      			return {
				      				typeModal:typeModal,
				      				titre:$scope.titreMessage,
				      				texte:$scope.texte,
				      				icone:$scope.icone,
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
	})
	.directive('printDiv', function () {
		  return {
		    restrict: 'A',
		    link: function(scope, element, attrs) {
		      element.bind('click', function(evt){    
		        evt.preventDefault();    
		        PrintElem(attrs.printDiv);
		      });

		      function PrintElem(elem)
		      {
		        PrintWithIframe($(elem).html());
		      }

		      function PrintWithIframe(data) 
		      {
		        if ($('iframe#printf').size() == 0) {
		          $('html').append('<iframe id="printf" name="printf"></iframe>');  // an iFrame is added to the html content, then your div's contents are added to it and the iFrame's content is printed

		          var mywindow = window.frames["printf"];
		          mywindow.document.write('<html><head><title></title><style>body{margin: 30mm 25mm}</style>'  // Your styles here, I needed the margins set up like this
		                          +'<script type="text/javascript" src="lib/angularjs/angular.min.js"></script>'
		                          +'<link rel="stylesheet" media="print" href="lib/font-awesome/css/font-awesome.min.css">'
		                          +'<link rel="stylesheet" media="print" href="lib/bootstrap/css/bootstrap.css">'
		                          +'<link rel="stylesheet" media="print" type="text/css" href="css/print-style.css"/>'
		                          + '</head><body>'
		                          + '<img src="images/entete.jpg" />'
		                          + data
		                          + '</body></html>');

		          $(mywindow.document).ready(function(){
		            mywindow.print();
		            setTimeout(function(){
		              $('iframe#printf').remove();
		            },
		            1000); 
		          });
		        }

		        return true;
		      }
		    }
		  };
	});

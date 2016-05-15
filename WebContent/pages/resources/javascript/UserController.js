(function () {
    'use strict';

    angular
        .module('app')
        .controller('UserController', UserController);

UserController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
function UserController($scope, $http){
  $scope.user = {};
 
  $scope.createUser = function() {
	user.dataLoading = true;  
    $http({
      method: 'POST',
      url: 'http://localhost:8080/TimeTracker/registroUsuario',
      headers: {'Content-Type': 'application/json'},
      data:  $scope.user
    }).success(function (response){
    	callback(response);
    	$location.path('/');
      });
  };
  
  $scope.loginUser = function(){
	  $http({
	      method: 'POST',
	      url: 'http://localhost:8080/TimeTracker/loginUsuario',
	      headers: {'Content-Type': 'application/json'},
	      data:  $scope.user
	    }).success(function (data){
	    	$scope.status=data;
	      });
  };
}

})();
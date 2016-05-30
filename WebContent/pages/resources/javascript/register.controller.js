(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService','$http','$route'];
    function RegisterController(UserService, $location, $rootScope, FlashService, $http, $route) {
        var vm = this;

        vm.register = register;

        function register() {
            vm.dataLoading = true;
//            CreateUser(vm)
//                .then(function (response) {
//                    if (response.success) {
//                        FlashService.Success('Registro completo', true);
//                        $location.path('/login');
//                    } else {
//                        FlashService.Error(response.message);
//                        vm.dataLoading = false;
//                    }
//                });
            if(vm.email.indexOf('@') == -1){
              alert("Correo Invalido");
              $route.reload();
            }
            CreateUser(vm, function (response) {
                if (response.success) {
                	FlashService.Success('Registro completo', true);
                    $location.path('/login');
                } else {
                    FlashService.Error("Registro Invalido");
                    vm.dataLoading = false;
                }
            });
        }
        
        function CreateUser(vm, callback){
        	$http({
      	      method: 'POST',
      	      url: 'http://localhost:8080/TimeTracker/registroUsuario',
      	      headers: {'Content-Type': 'application/json'},
      	      data:  { name: vm.name, lastName: vm.lastName, email: vm.email, password: vm.password }
      	    }).success(function (response){
      	    	//response = { success: true };
      	    	callback(response);
      	    });
        }
    }

})();
(function () {
    'use strict';

    angular
        .module('app')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(UserService, $location, $rootScope, FlashService) {
        var vm = this;

        vm.register = register;

        function register() {
            vm.dataLoading = true;
            Create(vm)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registro completo', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }
    }
    
    function CreateUser(vm){
    	$http({
  	      method: 'POST',
  	      url: 'http://localhost:8080/TimeTracker/registroUsuario',
  	      headers: {'Content-Type': 'application/json'},
  	      data:  { usermail: userid, userpassw: password }
  	    }).success(function (response){
  	    	//response = { success: true };
  	    	callback(response);
  	    });
    }

})();
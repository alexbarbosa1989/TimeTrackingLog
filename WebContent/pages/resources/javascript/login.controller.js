(function () {
    'use strict';

    angular
        .module('app')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            AuthenticationService.Login(vm.userid, vm.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials(response, vm.password);
                    $location.path('/');
                } else {
                    FlashService.Error("Error, por favor validar sus datos de acceso");
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
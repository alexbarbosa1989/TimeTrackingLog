(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);


    HomeController.$inject = ['UserService', '$rootScope','$scope'];
    function HomeController(UserService, $rootScope, $scope) {

        var vm = this;
        vm.user = null;
        vm.username = $rootScope.globals.currentUser.username;
        
        
        kendo.culture("es-ES");
        $scope.schedulerOptions = {
        		date: new Date(Date.now()),
                startTime: new Date("2016/04/19 07:00 AM"),
                height: 600,
                views: [
                    "day",
                    { type: "workWeek", selected: true },
                    "week",
                    "month",
                    "agenda"
                ],
                timezone: "Etc/UTC",
                dataSource: {
                    batch: true, // Enable batch updates
                    transport: {
                        read: {
                            url: "http://demos.telerik.com/kendo-ui/service/tasks",
                            dataType: "jsonp"
                        },
                        update: {
                            url: "http://demos.telerik.com/kendo-ui/service/tasks/update",
                            dataType: "jsonp"
                        },
                        create: {
                            url: "http://demos.telerik.com/kendo-ui/service/tasks/create",
                            dataType: "jsonp"
                        },
                        destroy: {
                            url: "http://demos.telerik.com/kendo-ui/service/tasks/destroy",
                            dataType: "jsonp"
                        },
                        parameterMap: function(options, operation) {
                            if (operation !== "read" && options.models) {
                            	if (operation == "create"){
                            		createTask(options.models);
                            	}else{
                                    return {models: kendo.stringify(options.models)};
                            	}
                            }
                        }
                    },
                    schema: {
                        model: {
                            id: "taskId", // The "id" of the event is the "taskId" field
                            fields: {
                                // Describe the scheduler event fields and map them to the fields returned by the remote service
                                taskId: {
                                    from: "TaskID", // The 'TaskID' server-side field is mapped to the 'taskId' client-side field
                                    type: "number"
                                },
                                title: { from: "Title", defaultValue: "No title", validation: { required: true } },
                                start: { type: "date", from: "Start" },
                                end: { type: "date", from: "End" },
                                description: { from: "Description" },
                                recurrenceId: { from: "RecurrenceID" },
                                recurrenceRule: { from: "RecurrenceRule" },
                                recurrenceException: { from: "RecurrenceException" },
                                isAllDay: { type: "boolean", from: "IsAllDay" }
                            }
                        }
                    }
                }
        };
    }
    
    //CRUD TASKS
    
    function createTask(data){
  	  return data;
    }

})();
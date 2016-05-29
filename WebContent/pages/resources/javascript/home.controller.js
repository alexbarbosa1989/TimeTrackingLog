(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);


    HomeController.$inject = ['UserService', '$rootScope', '$http'];
    function HomeController(UserService, $rootScope, $http) {

        var vm = this;
        var jsonRead;
        vm.user = null;
        vm.username = $rootScope.globals.currentUser.username;
        vm.usermail = $rootScope.globals.currentUser.usermail;
        
        
        
    	kendo.culture("es-ES");
//    	readCalendar(vm.usermail, function (response) {
//            if (response != null) {
//            	jsonRead = response;	
//            	
//            } else {
//                FlashService.Error(response.message);
//                vm.dataLoading = false;
//            }
//        });
    	$rootScope.schedulerOptions = {
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
                dataSource: {
                    batch: true, // Enable batch updates
                    transport: {
                        read: {
                            url: "http://localhost:8080/TimeTracker/rest/calendarReadServ/getCalendar",
                            dataType: "json",
                            type: "GET"
                        },
                        update: {
                            url: "http://localhost:8080/TimeTracker/rest/calendarReadServ/setModify",
                            dataType: "jsonp"
                        },
                        create: {
                            url: "http://localhost:8080/TimeTracker/rest/calendarReadServ/setModify",
                            dataType: "jsonp"
                        },
                        destroy: {
                            url: "http://localhost:8080/TimeTracker/rest/calendarReadServ/setModify",
                            dataType: "jsonp"
                        },
                        parameterMap: function(options, operation) {
                            if (operation !== "read" && options.models) {
                            	if (operation == "create"){
                            		createTask(options.models);
                            	}else if(operation == "update"){
                            		updateTask(options.models);
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
                                    from: "taskId", // The 'TaskID' server-side field is mapped to the 'taskId' client-side field
                                    type: "number"
                                },
                                title: { from: "title", defaultValue: "No title", validation: { required: true } },
                                start: { type: "date", from: "start" },
                                end: { type: "date", from: "end" },
                                description: { from: "description" },
                                recurrenceId: { from: "recurrenceId" },
                                recurrenceRule: { from: "recurrenceRule" },
                                recurrenceException: { from: "recurrenceException" },
                                isAllDay: { type: "boolean", from: "isAllDay" }
                            }
                        }
                    }
                }
        };
        
        
//        function readCalendar(usermail, callback){
//        	$http({
//        	      method: 'POST',
//        	      url: 'http://localhost:8080/TimeTracker/CRUDCalendar',
//        	      headers: {'Content-Type': 'application/json'},
//        	      data:  { usermail: usermail, action: "read" }
//        	    }).success(function (response){
//        	    	//response = { success: true };
//        	    	callback(response);
//        	    });
//        }
    
    	//CRUD TASKS
        
        function createTask(data){
        	data[0].start =	Date.parse(data[0].start);
        	data[0].end =	Date.parse(data[0].end);
      	  	return data;
        }
        
        
        function updateTask(data){
        	data[0].start =	Date.parse(data[0].start);
        	data[0].end =	Date.parse(data[0].end);
      	  	return data;
        }
    }
    
    
    

})();
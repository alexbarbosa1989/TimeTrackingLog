(function () {
    'use strict';

    angular
        .module('app')
        .controller('HomeController', HomeController);


    HomeController.$inject = ['UserService', '$rootScope', '$http', '$route','$scope'];
    function HomeController($UserService, $rootScope, $http, $route, $scope) {

        var vm = this;
        var jsonRead;
        vm.user = null;
        vm.username = $rootScope.globals.currentUser.username;
        vm.usermail = $rootScope.globals.currentUser.usermail;
        
        if(vm.usermail == 'admin@gmail.com'){
        	$scope.adminButton = true;
        }
        
        
    	kendo.culture("es-ES");
    	var url = "http://localhost:8080/TimeTracker/rest/calendarReadServ/getCalendarByUsr/"+vm.usermail;
    	$rootScope.schedulerOptions = {
        		date: new Date(Date.now()),
                startTime: new Date("2016/05/19 06:00 AM"),
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
                            url: url,
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
                            	}else if(operation == "destroy"){
                            		destroyTask(options.models);
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
                                    from: "taskID", // The 'TaskID' server-side field is mapped to the 'taskId' client-side field
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
        
        

    
    	//CRUD TASKS
        
        function createTask(data){
        	var start;
        	var end;
        	var recurrence;
        	var recurrenceException;
        	data[0].start =	Date.parse(data[0].start);
        	data[0].end =	Date.parse(data[0].end);
        	start = data[0].start + '';
        	end = data[0].end + '';
        	if (data[0].recurrenceRule == null){
        		recurrence = "null";
        	}else{
        		recurrence = data[0].recurrenceRule;
        	}
        	if(data[0].recurrenceException == null){
        		recurrenceException = "null";
        	}else{
        		recurrenceException = data[0].recurrenceException;
        	}
      	    $http({
      	    	method: 'POST',
	      	    url: 'http://localhost:8080/TimeTracker/CRUDCalendar',
	      	    headers: {'Content-Type': 'application/json'},
	      	    data:  { usermail: vm.usermail,
	      	    	     description: data[0].description,
	      	    		 title: data[0].title,
	      	    		 start: start,
	      	    		 end: end,
	      	    		 recurrenceRule: recurrence,
	      	    		 recurrenceException: recurrenceException,
	      	    		 isAllDay: data[0].isAllDay,
	      	    		 action: "create" }
	      	  }).success(function (response){
	      	      //response = { success: true };
	      		  $route.reload();
	      	      //return true;
	      	  });
        } 
        
        
        function updateTask(data){
        	var start;
        	var end;
        	var taskId;
        	var recurrence;
        	var recurrenceException;
        	data[0].start =	Date.parse(data[0].start);
        	data[0].end =	Date.parse(data[0].end);
        	start = data[0].start + '';
        	end = data[0].end + '';
        	taskId = data[0].taskID + '';
        	if (data[0].recurrenceRule == null){
        		recurrence = "null";
        	}else{
        		recurrence = data[0].recurrenceRule;
        	}
        	if(data[0].recurrenceException == null){
        		recurrenceException = "null";
        	}else{
        		recurrenceException = data[0].recurrenceException;
        	}
      	    $http({
      	    	method: 'POST',
	      	    url: 'http://localhost:8080/TimeTracker/CRUDCalendar',
	      	    headers: {'Content-Type': 'application/json'},
	      	    data:  { usermail: vm.usermail,
	      	    		 taskId : taskId,
	      	    	     description: data[0].description,
	      	    		 title: data[0].title,
	      	    		 start: start,
	      	    		 end: end,
	      	    		 isAllDay: data[0].isAllDay,
	      	    		 recurrenceRule: recurrence,
	      	    		 recurrenceException: recurrenceException,
	      	    		 action: "update" }
	      	  }).success(function (response){
	      	      //response = { success: true };
	      		  $route.reload();
	      	      //return true;
	      	  });
        }
        
        function destroyTask(data){
        	var start;
        	var end;
        	var taskId;
        	var recurrence;
        	var recurrenceException;
        	data[0].start =	Date.parse(data[0].start);
        	data[0].end =	Date.parse(data[0].end);
        	start = data[0].start + '';
        	end = data[0].end + '';
        	taskId = data[0].taskID + '';
        	if (data[0].recurrenceRule == null){
        		recurrence = "null";
        	}else{
        		recurrence = data[0].recurrenceRule;
        	}
        	if(data[0].recurrenceException == null){
        		recurrenceException = "null";
        	}else{
        		recurrenceException = data[0].recurrenceException;
        	}
      	    $http({
      	    	method: 'POST',
	      	    url: 'http://localhost:8080/TimeTracker/CRUDCalendar',
	      	    headers: {'Content-Type': 'application/json'},
	      	    data:  { usermail: vm.usermail,
	      	    		 taskId : taskId,
	      	    	     description: data[0].description,
	      	    		 title: data[0].title,
	      	    		 start: start,
	      	    		 end: end,
	      	    		 isAllDay: data[0].isAllDay,
	      	    		 recurrenceRule: recurrence,
	      	    		 recurrenceException: recurrenceException,
	      	    		 action: "destroy" }
	      	  }).success(function (response){
	      	      //response = { success: true };
	      		  $route.reload();
	      	      //return true;
	      	  });
        }
    }
    
    
    

})();
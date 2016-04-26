$(document).ready(function() {
	kendo.culture("es-ES");
	var app = new kendo.mobile.Application(document.body, { skin: "nova" });
    $("#language").kendoDropDownList({
        change: changeLanguage,
        dataTextField: "text",
        dataValueField: "value",
        dataSource: [
            {text: "es-ES"},
        ]
    });

    $("#language").data("kendoDropDownList").trigger("change");
});

function changeLanguage() {
    kendo.ui.progress($("#scheduler"), true);
    var baseUrl = 'http://kendo.cdn.telerik.com/2016.1.412/js/messages/kendo.messages.';
    $.getScript(baseUrl + this.value() + ".min.js", function () {
        kendo.ui.progress($("#scheduler"), false);
        createScheduler();
    });
}

function createScheduler() {
    var element = $("#scheduler");

    if (element.data("kendoScheduler")) {
        element.data("kendoScheduler").destroy();
        element.empty();
    }

    element.kendoScheduler({
        date: new Date("2016/04/19"),
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
        	data: [ {
        		"TaskID": 4,
        		"Title": "Bowling tournament",
        		"Description": "",
        		"StartTimezone": null,
        		"Start": "\/Date(1461448800000)\/",
        		"End": "\/Date(1461459600000)\/",
        		"EndTimezone": null,
        		"RecurrenceRule": null,
        		"RecurrenceID": null,
        		"RecurrenceException": null,
        		"IsAllDay": false
        	} ],
            schema: {
            	model: {
                    id: "taskId",
                    fields: {
                        taskId: { from: "TaskID", type: "number" },
                        title: { from: "Title", defaultValue: "No title", validation: { required: true } },
                        start: { type: "date", from: "Start" },
                        end: { type: "date", from: "End" },
                        startTimezone: { from: "StartTimezone" },
                        endTimezone: { from: "EndTimezone" },
                        description: { from: "Description" },
                        recurrenceId: { from: "RecurrenceID" },
                        recurrenceRule: { from: "RecurrenceRule" },
                        recurrenceException: { from: "RecurrenceException" },
                        ownerId: { from: "OwnerID", defaultValue: 1 },
                        isAllDay: { type: "boolean", from: "IsAllDay" }
                    }
                }
            }
        }

    });
}
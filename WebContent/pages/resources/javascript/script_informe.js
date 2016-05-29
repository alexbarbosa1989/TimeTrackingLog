var dataJson;
	
$(function(){
	
	obtenerJSON();
   //var json = JSON.parse(obtenerJSON());
   /*
	var  json = dataJson;
   console.log(json);
   
    //console.log(json);
    //console.log(json.personas_tarea);
    json = json[0];
    var objPerson=null;
    for (var key in json.personas_tarea) {
        console.log("valor del key "+key);
        console.log("valor del json "+json);
        objPerson = json.personas_tarea[key];
        //console.log(objPerson);
        //console.log(objPerson.nombres_persona +" "+  objPerson.apellidos_persona);
        $( '#combo_personas' ).append('<option value='+objPerson.id_persona+'>'+objPerson.nombres_persona.toUpperCase()+" "+  objPerson.apellidos_persona.toUpperCase()+'</option>');
    }
    
    $('#combo_personas').change(function(){ 
        var value = $(this).val();
       // console.log("El nombre seleccionado es: "+value);
        for (var key in json.personas_tarea) {
            objPerson = json.personas_tarea[key];      
            if(objPerson.id_persona==value){
                break;
            }
        }
        dibujarTabla(objPerson);

    });*/

});

function dibujarTabla(p){
    console.log("Esta es la persona a dibujar:");
    console.log("Nombres Persona: "+p.nombres_persona);
    console.log("Apellidos Persona: "+p.apellidos_persona);
    
    console.log("Las tareas son: ");
    tarea = null;
    cont_urg_imp = 0;
    cont_urg_no_imp = 0;
    cont_no_urg_imp = 0;
    cont_no_urg_no_imp = 0;
    cont_all=0;
    for (var key in p.tareas) {
        console.log("*********");
        tarea = p.tareas[key]; 
        console.log("Nombre Tarea: "+tarea.nombre_tarea);
       // console.log(tarea.urgente);
        //console.log(tarea.importante);
        if(tarea.urgente=="y" && tarea.importante=="y"){
            console.log("Tarea Urgente e Importante");
            cont_urg_imp +=1;
        }else if(tarea.urgente=="y" && tarea.importante=="n"){
            console.log("Tarea Urgente y No Importante");
            cont_urg_no_imp +=1;
        }else if(tarea.urgente=="n" && tarea.importante=="y"){
            console.log("Tarea No Urgente e Importante");
            cont_no_urg_imp +=1;
        }else if(tarea.urgente=="n" && tarea.importante=="n"){
            console.log("Tarea No Urgente y No Importante");
            cont_no_urg_no_imp +=1;
        }else{
            console.log("Algo no cuadra");
        }
        cont_all += 1;
    }
    console.log("El total es: "+cont_all);
    console.log("# Urgente e Importante: "+cont_urg_imp);
    console.log("# Urgente y No Importante: "+cont_urg_no_imp);
    console.log("# No Urgente e Importante: "+cont_no_urg_imp);
    console.log("# No Urgente y No Importante: "+cont_no_urg_no_imp); 
    
    var por_urg_imp = (cont_urg_imp/cont_all*100).toFixed(2).concat('%'),
        por_urg_no_imp = (cont_urg_no_imp/cont_all*100).toFixed(2).concat('%'),
        por_no_urg_imp = (cont_no_urg_imp/cont_all*100).toFixed(2).concat('%'),
        por_no_urg_no_imp = (cont_no_urg_no_imp/cont_all*100).toFixed(2).concat('%');
    
    console.log("% Urgente e Importante: "+por_urg_imp);
    console.log("% Urgente y No Importante: "+por_urg_no_imp);
    console.log("% No Urgente e Importante: "+por_no_urg_imp);
    console.log("% No Urgente y No Importante: "+por_no_urg_no_imp);
    
    $('#urgente p').html(por_urg_imp);
    $('#no_urgente p').html(por_urg_no_imp);
    $('#importante p').html(por_no_urg_imp);
    $('#no_importante p').html(por_no_urg_no_imp);
    
}

function obtenerJSON(){
	var path = "http://localhost:8080/TimeTracker/rest/calendarReadServ/getCalEmployees";
	//var json = "";
	$.get( path, function( data ) {
		 var  json = data;
		   console.log(json);
		   
		    //console.log(json);
		    //console.log(json.personas_tarea);
		    json = json[0];
		    var objPerson=null;
		    for (var key in json.personas_tarea) {
		        console.log("valor del key "+key);
		        console.log("valor del json "+json);
		        objPerson = json.personas_tarea[key];
		        //console.log(objPerson);
		        //console.log(objPerson.nombres_persona +" "+  objPerson.apellidos_persona);
		        $( '#combo_personas' ).append('<option value='+objPerson.id_persona+'>'+objPerson.nombres_persona.toUpperCase()+" "+  objPerson.apellidos_persona.toUpperCase()+'</option>');
		    }
		    
		    $('#combo_personas').change(function(){ 
		        var value = $(this).val();
		       // console.log("El nombre seleccionado es: "+value);
		        for (var key in json.personas_tarea) {
		            objPerson = json.personas_tarea[key];      
		            if(objPerson.id_persona==value){
		                break;
		            }
		        }
		        dibujarTabla(objPerson);

		    });
		  
		});
	//console.log(dataJson);
	    
}

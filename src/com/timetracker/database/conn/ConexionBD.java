package com.timetracker.database.conn;

import java.sql.*;

/**
 * Configura conexion a la base de datos
 * @author alexanderbarbosaayala
 *
 */
public class ConexionBD {
	static String bd = "timetracker";
    static String login = "admin";
    static String passw = "admin01";
    static String url = "jdbc:mysql://localhost:3306/"+bd;
    
    Connection connection = null;
    
    /** Constructor de DbConnection */
   public ConexionBD() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         connection = DriverManager.getConnection(url,login,passw);
 
         if (connection!=null){
            System.out.println("Conexión a base de datos "+bd+" OK\n");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexión*/
   public Connection getConnection(){
      return connection;
   }
 
   public void desconectar(){
      connection = null;
   }
   /*DATABASE CONFIG
    * 
    create table usuario(nombre varchar(50), 
   						apellido varchar(50),usuario_id varchar(50),
   						usuario_pass varchar(40),
   						rol varchar(2));
    alter table persona add primary key (id);

    CREATE TABLE roles( rol_id VARCHAR(2)NOT NULL, 
   						rol_descripcion VARCHAR(40) NOT NULL, PRIMARY KEY (rol_id) );

	insert into roles (rol_id, rol_descripcion) values ('01','administrador');
	insert into roles (rol_id, rol_descripcion) values ('02','empleado’);

	alter table usuario add constraint fk_rol_id foreign key (rol) references roles(rol_id);
    */

}

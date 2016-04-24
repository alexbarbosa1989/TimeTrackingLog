package com.timetracker.database.conn;

import java.sql.*;

/**
 * 
 * @author alexanderbarbosaayala
 *
 */
public class ConexionBD {
	static String bd = "timetracker";
    static String login = "admin";
    static String passw = "admin01";
    static String url = "jdbc:mysql://localhost/"+bd;
    
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

}

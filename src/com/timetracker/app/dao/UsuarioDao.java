package com.timetracker.app.dao;

import com.timetracker.app.dto.UsuarioDto;
import com.timetracker.database.conn.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author alexanderbarbosaayala
 *
 */
public class UsuarioDao {
	
	public UsuarioDto getUsuarioSesion(String idUsuario, String passwUsuario){
        UsuarioDto usuarioDto = new UsuarioDto();
        
        ConexionBD conex= new ConexionBD();
     
        try {
            int usuarioInt = Integer.parseInt(idUsuario);
            String consulta = "SELECT * FROM usuario where usuario_id = "+usuarioInt+
                    " and password = SHA1('"+passwUsuario+"');";
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(consulta);
            
            while(rs.next()){
            	usuarioDto.setIdUsuario(rs.getString("usuario_id"));
            	usuarioDto.setNombre(rs.getString("nombre"));
            	usuarioDto.setApellido(rs.getString("apellido"));
            	usuarioDto.setRolUsuario(rs.getString("rol"));
            }
        }catch(NumberFormatException e){
            System.out.println("Error de numero "+e.getMessage());
        }catch(SQLException e){
            System.out.println("Error en la conexion "+e.getMessage());
        }
        
        return usuarioDto;
    }
}

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
	
	/**
	 * selecciona registro de la base de datos
	 * @param idUsuario
	 * @param passwUsuario
	 * @return
	 */
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
	
	/**
	 * Inserta registro en base de datos
	 * @param usuario
	 * @return
	 */
	public Boolean registroUsuario(UsuarioDto usuario){
		ConexionBD conex= new ConexionBD();
		
		try{
            String consulta = "INSERT INTO usuario (nombre,apellido,usuario_id,usuario_pass,rol) values ('"+
                    usuario.getNombre()+"','"+usuario.getApellido()+"','"+usuario.getIdUsuario()+"',SHA1('"+usuario.getPassw()+"'),'"+
            		usuario.getRolUsuario()+"');";
            Statement st = conex.getConnection().createStatement();
            st.executeUpdate(consulta);
		}catch(SQLException e){
            System.out.println("Error en la conexion "+e.getMessage());
            return false;
        }
		return true;
	}
}

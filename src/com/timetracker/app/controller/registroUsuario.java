package com.timetracker.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.timetracker.app.dto.UsuarioDto;
import com.timetracker.app.dao.UsuarioDao;

/**
 * Servlet implementation class registroUsuario
 * @author alexanderbarbosaayala
 *
 */
@WebServlet("/registroUsuario")
public class RegistroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistroUsuario() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferReader = request.getReader();
        String linea = null;
        String respuesta = null;
        while ((linea = bufferReader.readLine()) != null) {
        	stringBuilder.append(linea);
        }
        JSONObject jObj;
		try {
			jObj = new JSONObject(stringBuilder.toString());
			
			respuesta = setVariablesRegistro(jObj);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(respuesta);
        
	}
	
	/**
	 * Setea valores traidos desde objeto Json del formulario en Objeto DTO y lanza consulta al DAO 
	 * @param obj
	 */
	private String setVariablesRegistro(JSONObject obj) {
		JSONObject rta = new JSONObject();
		try {
			String nombre = obj.getString("name");
			String apellido = obj.getString("lastName");
			String correo = obj.getString("email");
			String passw = obj.getString("password");
			
			UsuarioDto usuario = new UsuarioDto();
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setIdUsuario(correo);
			usuario.setPassw(passw);
			usuario.setRolUsuario("02");
			
			UsuarioDao usuarioDao = new UsuarioDao();
			Boolean result = false;
			result = usuarioDao.registroUsuario(usuario);
//			System.out.println("Resultado:"+result.toString());
			rta.put("success", true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return rta.toString();
		
	}

}

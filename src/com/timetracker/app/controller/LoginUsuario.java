package com.timetracker.app.controller;

import java.io.BufferedReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.timetracker.app.dao.UsuarioDao;
import com.timetracker.app.dto.UsuarioDto;

/**
 * Servlet implementation class LoginUsuario
 */
@WebServlet("/loginUsuario")
public class LoginUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginUsuario() {
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
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferReader = request.getReader();
        String linea = null;
        while ((linea = bufferReader.readLine()) != null) {
        	stringBuilder.append(linea);
        }
        JSONObject jObj;
		try {
			jObj = new JSONObject(stringBuilder.toString());
			
			setVariablesLogin(jObj);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * loeguea el usuario
	 * @param obj
	 */
	private void setVariablesLogin(JSONObject obj) {
		try{
			String idUsuario = obj.getString("usermail");
			String passw = obj.getString("userpassw");
			
			UsuarioDto usuario = new UsuarioDto();
			usuario.setIdUsuario(idUsuario);
			usuario.setPassw(passw);
			
			UsuarioDao usuarioDao = new UsuarioDao();
			usuario = usuarioDao.getUsuarioSesion(usuario);
			System.out.println("Nombre usuario: "+usuario.getNombre());
		} catch (JSONException e){
			e.printStackTrace();
		}
		
	}

}

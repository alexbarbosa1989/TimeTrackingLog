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
		PrintWriter out = response.getWriter();
		StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferReader = request.getReader();
        String linea = null;
        String usuarioLogin = null;
        JSONObject jObj;
        
        while ((linea = bufferReader.readLine()) != null) {
        	stringBuilder.append(linea);
        }
        
		try {
			jObj = new JSONObject(stringBuilder.toString());
			
			usuarioLogin = setVariablesLogin(jObj);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		out.print(usuarioLogin);
	}
	
	/**
	 * loeguea el usuario
	 * @param obj
	 */
	private String setVariablesLogin(JSONObject obj) {
		UsuarioDto usuario = new UsuarioDto();
		String rtaUsuario = new String();
		try{
			String idUsuario = obj.getString("usermail");
			String passw = obj.getString("userpassw");
			
			
			usuario.setIdUsuario(idUsuario);
			usuario.setPassw(passw);
			
			UsuarioDao usuarioDao = new UsuarioDao();
			usuario = usuarioDao.getUsuarioSesion(usuario);
//			System.out.println("Nombre usuario: "+usuario.getNombre());
			//create response Object
			rtaUsuario = setRespuesta(usuario).toString();
		} catch (JSONException e){
			e.printStackTrace();
		}
		return rtaUsuario;
		
	}
	
	/**
	 * genera el objeto JSON de respuesta
	 * @param usr
	 * @return
	 * @throws JSONException
	 */
	private JSONObject setRespuesta(UsuarioDto usr) throws JSONException{
		JSONObject obj = new JSONObject();
		if (usr.getNombre() == null){
			obj.put("success", false);
		}else{
			obj.put("username", usr.getNombre());
			obj.put("userlastname", usr.getApellido());
			obj.put("usermail", usr.getIdUsuario());
			obj.put("success", true);
		}
		
		return obj;
	}

}

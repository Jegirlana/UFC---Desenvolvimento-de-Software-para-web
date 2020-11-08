package controller;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.UserDAO;
import model.User;
 
@WebServlet("/api/users/*")
public class UserService extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public UserService() {
        super();
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // GET BY ID
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                User user = UserDAO.getUser(Integer.parseInt(params[1]));
 
                if (user != null) {
                    JSONObject jsonObject = new JSONObject();
 
                    jsonObject.put("id", user.getId());
                    jsonObject.put("nome", user.getNome());
                    jsonObject.put("cargo", user.getCargo());
                    jsonObject.put("datanascimento", user.getDatanascimento());
                    jsonObject.put("dataentrada", user.getDataentrada());
 
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().print(jsonObject.toString());
                    response.getWriter().flush();
                }
                return;
            }
        }
 
        // GET BY NAME
        if (request.getParameter("nome") != null) {
            User user = UserDAO.getUserBylogin(request.getParameter("nome"));
 
            if (user != null) {
 
                JSONObject jsonObject = new JSONObject();
 
                jsonObject.put("id", user.getId());
                jsonObject.put("nome", user.getNome());
                jsonObject.put("cargo", user.getCargo());
                jsonObject.put("datanascimento", user.getDatanascimento());
                jsonObject.put("dataentrada", user.getDataentrada());
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonObject.toString());
                response.getWriter().flush();
 
            }
            return;
        }
 
        //GET BY CARGO  	
        if (request.getParameter("cargo") != null) {
            List<User> lista = UserDAO.getUserByCargo(request.getParameter("cargo"));
            JSONArray jArray = new JSONArray();

           if (lista != null) {
        	   for (User user : lista) {
        		   
        		   JSONObject jsonObject = new JSONObject();
        		   
        		   jsonObject.put("id", user.getId());
        		   jsonObject.put("nome", user.getNome());
        		   jsonObject.put("cargo", user.getCargo());
        		   jsonObject.put("datanascimento", user.getDatanascimento());
        		   jsonObject.put("dataentrada", user.getDataentrada());
        		   jArray.put(jsonObject);
        	   }
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jArray.toString());
                response.getWriter().flush();
 
            }
            return;
        }
        
      //GET BY QUANTIDADE  	
        if (request.getParameter("quantidade") != null) {
	            List<User> lista = UserDAO.getUserByQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            JSONArray jArray = new JSONArray();

           if (lista != null) {
        	   for (User user : lista) {
        		   
        		   JSONObject jsonObject = new JSONObject();
        		   
        		   jsonObject.put("id", user.getId());
        		   jsonObject.put("nome", user.getNome());
        		   jsonObject.put("cargo", user.getCargo());
        		   jsonObject.put("datanascimento", user.getDatanascimento());
        		   jsonObject.put("dataentrada", user.getDataentrada());
        		   jArray.put(jsonObject);
        	   }
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jArray.toString());
                response.getWriter().flush();
 
            }
            return;
        }
           
        // GET ALL
        List<User> list = UserDAO.getAllUsers();
 
        try {
            JSONArray jArray = new JSONArray();
 
            for (User user : list) {
                JSONObject jsonObject = new JSONObject();
 
                jsonObject.put("id", user.getId());
                jsonObject.put("nome", user.getNome());
                jsonObject.put("cargo", user.getCargo());
                jsonObject.put("datanascimento", user.getDatanascimento());
                jsonObject.put("dataentrada", user.getDataentrada());
 
                jArray.put(jsonObject);
            }
 
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jArray.toString());
            response.getWriter().flush();
        } catch (Exception e) {
 
        }
       
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
        }
 
        User user = null;
        JSONObject jsonObject = null;
 
        try {
        	jsonObject = new JSONObject(jb.toString());
            user = UserDAO.addUser(jsonObject.getString("nome"), jsonObject.getString("cargo"), jsonObject.getString("datanascimento"), jsonObject.getString("dataentrada"));
 
            // Response
            jsonObject = new JSONObject();
            jsonObject.put("id", user.getId());
            jsonObject.put("nome", user.getNome());
            jsonObject.put("cargo", user.getCargo());
            jsonObject.put("datanascimento", user.getDatanascimento());
            jsonObject.put("dataentrada", user.getDataentrada());
 
        } catch (JSONException e) {
 
        }
 
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonObject.toString());
            response.getWriter().flush();
    }
 
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
        // UPDATE BY ID
    	
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                StringBuffer jb = new StringBuffer();
                String line = null;
                try {
                    BufferedReader reader = request.getReader();
                    while ((line = reader.readLine()) != null)
                        jb.append(line);
                } catch (Exception e) {
                }
 
                User user = null;
                JSONObject jsonObject = null;
 
                try {
                	jsonObject = new JSONObject(jb.toString());
                    user = UserDAO.updateUser(Integer.parseInt(params[1]), jsonObject.getString("nome"),
                            jsonObject.getString("cargo"), jsonObject.getString("datanascimento"),jsonObject.getString("dataentrada"));
 
                    // Response
                    jsonObject = new JSONObject();
                    jsonObject.put("id", user.getId());
                    jsonObject.put("nome", user.getNome());
                    jsonObject.put("cargo", user.getCargo());
                    jsonObject.put("datanascimento", user.getDatanascimento());
                    jsonObject.put("dataentrada", user.getDataentrada());
 
                } catch (JSONException e) {
                }
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonObject.toString());
                response.getWriter().flush();
            }
        }
    }
 
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // DELETE BY ID
        String pathInfo = request.getPathInfo();
 
        if (pathInfo != null) {
            String[] params = pathInfo.split("/");
 
            if (params.length > 0) {
                UserDAO.deleteUser(Integer.parseInt(params[1]));
 
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().flush();
            }
        }
    }
}
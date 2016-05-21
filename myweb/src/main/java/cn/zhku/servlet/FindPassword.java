package cn.zhku.servlet;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.User;




public class FindPassword extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public FindPassword() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String code = request.getParameter("code");
		String sql = "select x from User x where x.code=:code";
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("code", code);
		
		Boolean isNotExists = q.getResultList().isEmpty();
		if(isNotExists)
		{
			String msg = "该用户尚未注册";
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
			return;
		}
		else
		{
			User u = (User) q.getResultList().get(0);
			String password = u.getPassword();
			String msg = "你好"+ u.getName() +"请记住你的密码： "+ password;
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
		}
	}
}

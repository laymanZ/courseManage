package cn.zhku.servlet;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.Teacher;




public class EditUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public EditUser() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		int age = Integer.parseInt(request.getParameter("age"));
		
		String type = request.getParameter("type");		
		String password = request.getParameter("password");
		
		String sql = "select x from User x where x.code=:code";
		
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("code", code);
		Teacher t = (Teacher) q.getResultList().get(0);
		t.setCode(code);
		t.setName(name);
		t.setSex(sex);
		t.setAge(age);
		t.setType(type);
		t.setPassword(password);
		DataAccessor.update(t);
		
		
		String msg = "chenggong";
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		String ms = obj.toString();
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ms);
	}
}

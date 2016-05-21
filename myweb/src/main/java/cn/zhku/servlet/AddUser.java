package cn.zhku.servlet;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.Student;
import cn.zhku.modal.Teacher;
import cn.zhku.modal.User;




public class AddUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public AddUser() 
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
		String info = request.getParameter("info");
		String type = request.getParameter("type");		
		String password = request.getParameter("password");
		
		
		String sql = "select x from User x where x.code=:code";
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("code", code);
		Boolean isNotExist = q.getResultList().isEmpty();
		
		
		
		if(isNotExist)
		{
			User u = null;
			if(type.equals("老师"))
			{
				u = new Teacher( code,  name,  sex,  age,  password, info);
				HttpSession hs = request.getSession(); 
				hs.setAttribute("tCode", u.getCode());
				hs.setAttribute("tName", u.getName());
			}
			else
			{
				u = new Student( code,  name,  sex,  age,  password, info);
			}
			
			// 插入一条记录
			DataAccessor.saveNew(u);
			// 返回数据给ajax回调函数
			String msg = "恭喜你"+u.getName()+"注册成功";
			
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			obj.put("type2",type);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
		}
		else
		{
			String msg = "该工号或学号已存在";
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
			return;
		}
	}
}

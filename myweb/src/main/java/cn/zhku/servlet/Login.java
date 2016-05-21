package cn.zhku.servlet;

import java.io.IOException;

import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.Student;
import cn.zhku.modal.Teacher;
import cn.zhku.modal.User;


public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	public Login()
	{
		super();
		
	}

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	static boolean writeonce = false; 
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		if(!writeonce)
		{// 第一次运行的时候写入，如果多次重启服务器.需注释本代码或者删除数据库user表记录。
//			DataAccessor.saveNew(new Teacher("t1","女",30,"123","教授"));
//			DataAccessor.saveNew(new Student("s1","女",19,"123","信计134班"));
			writeonce = true;
		}
		
		// 取出登录页面传来的用户和密码
		String code = request.getParameter("username");
		String password = request.getParameter("password");
		// 到数据库查询用户名等于code的唯一用户
		TypedQuery<User> q = DataAccessor.getManager().createQuery(
				"select x from User x where x.code=:code ", User.class);
		String nextPage = "index.html";
		q.setParameter("code", code);
		try 
		{
			User user = q.getSingleResult();
			// 对用户的登录情况进行判断，登录不成功，跳转回到登录页面
			if (password == null || !user.getPassword().equals(password)) 
			{//
				throw new RuntimeException("login fail");
			}
			if(user instanceof Teacher){
				HttpSession hs = request.getSession(); 
				hs.setAttribute("tCode", user.getCode());
				hs.setAttribute("tName", user.getName());
				nextPage = "teacher_main.html";
			}else if(user instanceof Student)
			{
				HttpSession hs2 = request.getSession(); 
				hs2.setAttribute("sCode", user.getCode());
				hs2.setAttribute("sName", user.getName());
				nextPage = "student_main.html";
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);    // 使用req对象获取RequestDispatcher对象
	        dispatcher.forward(request, response);      
		}

		
	}

}

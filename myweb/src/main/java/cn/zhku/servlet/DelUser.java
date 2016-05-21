package cn.zhku.servlet;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.zhku.dao.DataAccessor;


public class DelUser extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
    public DelUser() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String courseCode = request.getParameter("courseCode");
		String sql = "delete from Course x where x.courseCode=:courseCode";
		
		DataAccessor.getManager().getTransaction().begin();// 开启事务
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("courseCode", courseCode);
		q.executeUpdate();
		DataAccessor.getManager().getTransaction().commit();// 提交事务
		
		
		String sql2 = "delete from CourseOfStudent x where x.courseCode=:courseCode";
		DataAccessor.getManager().getTransaction().begin();// 开启事务
		Query q2 = DataAccessor.getManager().createQuery(sql2);
		q2.setParameter("courseCode", courseCode);
		q2.executeUpdate();
		DataAccessor.getManager().getTransaction().commit();// 提交事务
		
		
		String msg = "删除成功！";
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		String ms = obj.toString();
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(ms);
	}

}

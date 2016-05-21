package cn.zhku.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.CourseOfStudent;

/**
 * 从数据库加载课程
 */
public class LoadStudentCourses extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
    public LoadStudentCourses() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//List<?> us = DataAccessor.findAll(CourseOfStudent.class);
		//System.out.println(us.toString());
		HttpSession session= request.getSession();
        String code = (String) session.getAttribute("sCode");
		
		
		String sql = "select x from CourseOfStudent x where x.code=:code";
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("code", code);
		List<?> us = q.getResultList();
		
		JSONArray jsonArray = new JSONArray();   
		//us.remove(0);
		jsonArray.put(us);
		response.setCharacterEncoding("UTF-8"); 
		jsonArray.write(response.getWriter());
		
	}

}

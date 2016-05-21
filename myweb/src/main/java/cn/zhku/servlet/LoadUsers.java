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

/**
 * 从数据库加载用户
 */
public class LoadUsers extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
    public LoadUsers() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session= request.getSession();
        String teacherCode = (String) session.getAttribute("tCode");
		
		
		String sql = "select x from Course x where x.teacherCode=:teacherCode";
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("teacherCode", teacherCode);
		
		
//		Course c = (Course) q.getResultList().get(0);
//		System.out.println(c.getCourseCode()+c.getCourseName() +c.getTeacherCode());
		
		List<?> us = q.getResultList();
		
		JSONArray jsonArray = new JSONArray();   
		//us.remove(0);
		jsonArray.put(us);
		response.setCharacterEncoding("UTF-8"); 
		jsonArray.write(response.getWriter());
	}

}

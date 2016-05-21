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
import cn.zhku.modal.Course;
import cn.zhku.modal.CourseOfStudent;


public class SelectCourse extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
    public SelectCourse() 
    {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String courseCode = request.getParameter("courseCode");
		
		
		String sql = "select x from Course x where x.courseCode=:courseCode";
		
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("courseCode", courseCode);
		Course c = (Course) q.getResultList().get(0);
		response.setCharacterEncoding("UTF-8"); 
		
		
		
		if(c.getHadSNum() >= c.getMostSNum())
		{
			String msg = "人数已经达到上限";
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.getWriter().print(ms);
			return;
		}
		HttpSession session= request.getSession();
	    String studentCode = (String) session.getAttribute("sCode");
		String studentName = (String)session.getAttribute("sName");	
		
		String sql2 = "select x from CourseOfStudent x where x.courseCode=:courseCode and x.code=:code";
		Query q2 = DataAccessor.getManager().createQuery(sql2);
		q2.setParameter("courseCode", courseCode);
		q2.setParameter("code", studentCode);
		
		
		
		
		Boolean isNotExist = q2.getResultList().isEmpty();
		if(isNotExist)
		{
			String courseName = c.getCourseName();
			Long xueFen = c.getXueFen();
			String teacherCode = c.getTeacherCode();
			CourseOfStudent cos = new CourseOfStudent(studentCode, studentName, courseName, courseCode, xueFen, teacherCode);
			DataAccessor.update(cos);
		}
		else
		{
			String msg = "不能重复选课";
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.getWriter().print(ms);
			return;
		}	
			
		c.setHadSNum(c.getHadSNum() + 1);
		DataAccessor.update(c);
		
		
		String msg = "选课成功！";
		JSONObject obj = new JSONObject();
		obj.put("msg", msg);
		String ms = obj.toString();
		
		response.getWriter().print(ms);
	}

}

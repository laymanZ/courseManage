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




public class AddCourse extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public AddCourse() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String courseCode = request.getParameter("courseCode");
		String courseName = request.getParameter("courseName");
		long credit = Integer.parseInt(request.getParameter("credit"));
		long mostNum = Integer.parseInt(request.getParameter("mostNum"));
		
		//System.out.println(courseName + courseCode + credit + mostNum);
		
		  HttpSession session= request.getSession();
        String teacherCode = (String) session.getAttribute("tCode");
		String teacherName = (String)session.getAttribute("tName");
		Course course = new Course(courseName, courseCode, credit, (long) 0, mostNum,teacherCode);
		
		
		String sql = "select x from Course x where x.courseCode=:courseCode";
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("courseCode", courseCode);
		Boolean isNotExist = q.getResultList().isEmpty();
		if(isNotExist)
		{
			// 插入一条记录
			DataAccessor.saveNew(course);
			// 返回数据给ajax回调函数
			String msg = "恭喜你"+teacherName+"开课成功";
			
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
		}
		else
		{
			// 返回数据给ajax回调函数
			String msg = "该课程号已存在";
			
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
			return;
		}
	
	}
}

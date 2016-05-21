package cn.zhku.servlet;

import java.io.IOException;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.Course;
import cn.zhku.modal.CourseOfStudent;




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
		String courseCode = request.getParameter("courseCode");
		String courseName = request.getParameter("courseName");
		Long xueFen = Long.parseLong(request.getParameter("xueFen"));
		Long mostSNum = Long.parseLong(request.getParameter("mostSNum"));
		
		String sql = "select x from Course x where x.courseCode=:courseCode";
		
		Query q = DataAccessor.getManager().createQuery(sql);
		q.setParameter("courseCode", courseCode);
		Boolean isNotExist = q.getResultList().isEmpty();
		if(isNotExist)
		{
			String msg = "不存在该课程号";
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
			return;
		}
		else
		{
			Course c = (Course) q.getResultList().get(0);
			c.setCourseName(courseName);
			c.setXueFen(xueFen);
			c.setMostSNum(mostSNum);
			DataAccessor.update(c);
		
			String sql2 = "select x from CourseOfStudent x where x.courseCode=:courseCode";
			Query q2 = DataAccessor.getManager().createQuery(sql2);
			q2.setParameter("courseCode", courseCode);
			int length = q2.getResultList().size();//教师修改完课程的内容后需要修改选课记录里面内容
			if(length > 0)
			{
				System.out.println(length);
				for(int i=0;i<length;i++)
				{
					CourseOfStudent cos = (CourseOfStudent) q2.getResultList().get(i);
					cos.setCourseName(courseName);
					cos.setXueFen(xueFen);
					DataAccessor.update(cos);
				}
			}
			
			
			String msg = "修改课程成功";
			JSONObject obj = new JSONObject();
			obj.put("msg", msg);
			String ms = obj.toString();
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(ms);
		}
		
	}
}

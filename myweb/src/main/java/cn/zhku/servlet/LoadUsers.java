package cn.zhku.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import cn.zhku.dao.DataAccessor;
import cn.zhku.modal.User;

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
		List<?> us = DataAccessor.findAll(User.class);
		for(Object o : us)
		{
			User u = (User) o;
			u.setType(u.getClass().getSimpleName());
		}
		JSONArray jsonArray = new JSONArray();   
		//us.remove(0);
		jsonArray.put(us);
		response.setCharacterEncoding("UTF-8"); 
		jsonArray.write(response.getWriter());
	}

}

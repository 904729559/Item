package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Userinformation;
import com.pojo.Userkey;
import com.service.SUserinformation;
import com.service.SUserkey;


@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet {
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
//处理并获取请求内容
			String key_name=request.getParameter("key_name");
			String key_password=request.getParameter("key_password");
			String memory[]=request.getParameterValues("memory");
			System.out.println("用户："+key_name);
			System.out.println("密码："+key_password);			
//是否记住密码
			Cookie co1=new Cookie("key_name", key_name);
			Cookie co2=new Cookie("key_password", key_password);
			if(memory!=null){				
				co1.setMaxAge(60*60*24*15);
				co2.setMaxAge(60*60*24*15);
			}else{			
				co1.setMaxAge(0);
				co2.setMaxAge(0);
			}
			response.addCookie(co1);
			response.addCookie(co2);
//查询数据库
			SUserkey suk=new SUserkey();
			boolean judge=suk.isPass(key_name, key_password);
			Userkey uk=suk.returnUserkey(key_name, key_password);
			
			
//控制跳转
			if(judge){
				SUserinformation sui=new SUserinformation();
				Userinformation ui=sui.returnUserinformation(uk.getKey_id());
				request.getSession().setAttribute("userkey", uk);
				request.getSession().setAttribute("userinformation", ui);
				response.sendRedirect("jsp/Menu.jsp");
			}else{
				request.getSession().setAttribute("flag", "用户或密码错误！");//设置标志
				response.sendRedirect("Login.jsp?result=Your username or password is wrong!");
//				request.setAttribute("result","<script>alert('用户或密码错误')</script>");//一次性参数使用request储存
//				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

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
			
//������ȡ��������
			String key_name=request.getParameter("key_name");
			String key_password=request.getParameter("key_password");
			String memory[]=request.getParameterValues("memory");
			System.out.println("�û���"+key_name);
			System.out.println("���룺"+key_password);			
//�Ƿ��ס����
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
//��ѯ���ݿ�
			SUserkey suk=new SUserkey();
			boolean judge=suk.isPass(key_name, key_password);
			Userkey uk=suk.returnUserkey(key_name, key_password);
			
			
//������ת
			if(judge){
				SUserinformation sui=new SUserinformation();
				Userinformation ui=sui.returnUserinformation(uk.getKey_id());
				request.getSession().setAttribute("userkey", uk);
				request.getSession().setAttribute("userinformation", ui);
				response.sendRedirect("jsp/Menu.jsp");
			}else{
				request.getSession().setAttribute("flag", "�û����������");//���ñ�־
				response.sendRedirect("Login.jsp?result=Your username or password is wrong!");
//				request.setAttribute("result","<script>alert('�û����������')</script>");//һ���Բ���ʹ��request����
//				request.getRequestDispatcher("Login.jsp").forward(request, response);
			}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}

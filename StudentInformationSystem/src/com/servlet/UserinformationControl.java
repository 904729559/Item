package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Userinformation;
import com.service.SUserinformation;

@WebServlet("/UserinformationControl")
public class UserinformationControl extends HttpServlet {
	SUserinformation sui=new SUserinformation();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag=request.getParameter("flag");
		Userinformation ui=new Userinformation();
		if("show".equals(flag)){
			ArrayList<Userinformation> al=sui.checkUserinformation(ui);
			request.getSession().setAttribute("al_ui", al);
			PrintWriter pw=response.getWriter();
			pw.write("1");
			pw.flush();
			pw.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

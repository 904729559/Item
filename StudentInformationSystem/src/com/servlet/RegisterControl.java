package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Userinformation;
import com.pojo.Userkey;
import com.service.SUserinformation;
import com.service.SUserkey;

@WebServlet("/Register")
public class RegisterControl extends HttpServlet {
	SUserkey suk=new SUserkey();
	SUserinformation sui=new SUserinformation();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//��ȡuserkey��Ϣ
		String key_name=request.getParameter("key_name");
		String key_password=request.getParameter("password");
	//��ȡ����userinformation��Ϣ
		String use_idcard=request.getParameter("idcard");
		String use_name=request.getParameter("use_name");
		String use_sex=request.getParameter("sex");
		String use_Birthday=request.getParameter("birthday");		
			String use_province=request.getParameter("province");
			String use_city=request.getParameter("city");		
		String use_hometown=use_province+"ʡ"+use_city+"��";
		
	//��װuserkey	
		Userkey uk=new Userkey();
		uk.setKey_name(key_name);
		uk.setKey_password(key_password);
	//��װuserinformation	
		Userinformation ui=new Userinformation();
		ui.setUse_idcard(use_idcard);
		ui.setUse_name(use_name);
		ui.setUse_sex(use_sex);
			Date use_birthday = null;	
			try {
				if("".equals(use_Birthday)){//�ӱ���ȡ��DateĬ��ֵΪ""
					use_birthday = new Date(0);
				}else{
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					use_birthday = sdf.parse(use_Birthday);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		ui.setUse_birthday(use_birthday);
		ui.setUse_hometown(use_hometown);
		ui.setUse_schoolstart(new Date(0));
		ui.setUse_schoolend(new Date(0));
		
	//����
		boolean b=suk.isUser(key_name);		
		if(b){
			request.getSession().setAttribute("temp_uk", uk);
			request.getSession().setAttribute("temp_ui", ui);
			request.getSession().setAttribute("flag2", "�û����Ѵ��ڣ�");
			response.sendRedirect("Register.jsp?result=User name already exists!");
			return;
		}
			
	//ע��ɹ�	
		boolean result1=suk.addUserkey(uk);
			Userkey Uk=suk.returnUserkey(key_name, key_password);
		ui.setKey_id(Uk.getKey_id());
		boolean result2=sui.addUserinformation(ui);
		if(result1&&result2){
			request.getSession().setAttribute("flag", "ע��ɹ���");
			response.sendRedirect("Login.jsp?result=Registered successfully!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}

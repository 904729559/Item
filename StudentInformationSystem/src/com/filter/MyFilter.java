package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse res=(HttpServletResponse) arg1;
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
//		String uri=req.getRequestURI();
//		if("".equals(uri)){
//			arg2.doFilter(req, res);
//		}
		
		if(req.getSession().getAttribute("userkey")!=null){
			System.out.println("获得用户登陆信息");
			arg2.doFilter(req, res);
			return;
		}
		System.out.println("被拦截");
		req.getSession().setAttribute("flag", "非法访问！");
		res.sendRedirect("../Login.jsp？result=Unauthorized access!");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

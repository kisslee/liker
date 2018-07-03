package com.liker.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liker.service.WeiXinService;

public class WeiXinController extends HttpServlet{

	/**
	 * wx
	 */
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger. getLogger("WeiXinController");

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter out = resp.getWriter();
		out.print(req.getParameter("echostr")); 

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 接收XML数据
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)req.getInputStream()));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while((line = br.readLine())!=null){
			sb.append(line);
		}
		String xmlText = sb.toString();
		
//		logger.log(Level.INFO, "getXmlText=" + xmlText);

		WeiXinService wxService = new WeiXinService();
		String result = wxService.sendMessage(xmlText);
		
//		logger.log(Level.INFO, "sendXmlText=" + result);
		
		PrintWriter out = resp.getWriter();
		out.write(result);
		
		out.close();
	}

}

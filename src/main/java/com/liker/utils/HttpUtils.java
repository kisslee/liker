package com.liker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtils {

	public static String sendPost(String urlPath, String content, String charset){

		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		System.out.println("请求的地址："+urlPath);
		try {
			URL url = new URL(urlPath.toString());  //URL为本地BICE服务端发送行程单数据服务的IP:PORT
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			String strMessage ="";
			
			//发送请求数据
			OutputStream output = conn.getOutputStream();
			OutputStreamWriter out = new OutputStreamWriter(output,charset);
			System.out.println("发送的请求数据:"+content.toString());
			out.write(content);
			out.flush();
			out.close();
			
			
			//接收返回数据
			InputStream inputStream = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream,charset));
			while ((strMessage = reader.readLine()) != null) {
				buffer.append(strMessage);
			}

			System.out.println("返回响应的结果:" + buffer.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return buffer.toString();
	}
	
	public static void main(String[] args) {
		
		//向微信发送自定义菜单请求，目前是订阅号，享受不到这项功能
//		String urlPath = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
		
//		String content = "{'button':[{'type':'click','name':'今日名言','key':'V1001_TODAY_SAYING'}]}";
		
		
		//模拟微信发送消息请求
//		String urlPath = "http://localhost:8080/firstAWS/wx";
		String urlPath = "http://127.0.0.1:9001/mysunday/api/wx";
		
		//文本消息测试
		String content = "<xml>" +
							"<ToUserName><![CDATA[toUser]]></ToUserName>" +
							"<FromUserName><![CDATA[fromUser]]></FromUserName>" + 
							"<CreateTime>1348831860</CreateTime>" +
							"<MsgType><![CDATA[text]]></MsgType>" +
							"<Content><![CDATA[sf770131978305]]></Content>" +
							"<MsgId>1234567890123456</MsgId>" +
						 "</xml>";
		
		//事件消息测试
//		String content = "<xml>" +
//				"<ToUserName><![CDATA[toUser]]></ToUserName>" +
//				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + 
//				"<CreateTime>1348831860</CreateTime>" +
//				"<MsgType><![CDATA[event]]></MsgType>" +
//				"<Event><![CDATA[subscribe]]></Event>" +
//			 "</xml>";
		
		sendPost(urlPath, content, "UTF-8");
		
	}
}

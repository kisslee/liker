package com.liker.service;

import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.liker.domain.Message;
import com.liker.utils.HttpRequest;

public class MsgService {

	Logger logger = Logger. getLogger("MsgService");
	
	//处理文本消息
	public void doText(Message message){
		String msg = "亲，请输入有效的回复哦!\n" +
					 "---------------\n" +
					 "回复:\"0\"，回到主菜单";
		
		//接收到的消息内容
		String getMsg = message.getContent();
		
		System.out.println("receive msg : " + getMsg);
		
		//主菜单
		if("0".equals(getMsg)){
			msg = "回复每个菜单前面的数字进入功能\n" +
				  "---------------\n" +
				  "1:快递查询\n" +
				  "2:微测试\n" +
				  "---------------\n" +
				  "回复:\"0\"，回到主菜单";
		}
		
		//快递查询功能菜单
		if("1".equals(getMsg)){
			msg = "请回复\"快递公司代号+快递单号\"\n---------------\n" +
					"目前支持的快递公司有：\n" +
					"顺丰：sf\n" +
					"申通：st\n" +
					"圆通：yt\n" +
					"中通：zt\n" +
					"韵达：yd\n" +
					"德邦：db\n" +
					"ems快递：em\n" +
					"宅急送：zj\n" +
					"---------------\n" +
					"如顺丰回复\"sf123456789012\"" +
					"\n---------------\n" +
					"回复:\"0\"，回到主菜单";
		}
		
		//快递查询功能
		if(getMsg.length() >= 2){
			String kuaidi = "";
			String head = "";
			boolean flag = true;
			
			if("sf".equals(getMsg.substring(0,2))){
				kuaidi = "shunfeng";
				head = "顺丰速运单号:";
			}else if("st".equals(getMsg.substring(0,2))){
				kuaidi = "shentong";
				head = "申通快运单号:";
			}else if("yt".equals(getMsg.substring(0,2))){
				kuaidi = "yuantong";
				head = "圆通快运单号:";
			}else if("zt".equals(getMsg.substring(0,2))){
				kuaidi = "zhongtong";
				head = "中通快运单号:";
			}else if("yd".equals(getMsg.substring(0,2))){
				kuaidi = "yunda";
				head = "韵达快运单号:";
			}else if("db".equals(getMsg.substring(0,2))){
				kuaidi = "debangwuliu";
				head = "德邦物流单号:";
			}else if("em".equals(getMsg.substring(0,2))){
				kuaidi = "ems";
				head = "ems快递单号:";
			}else if("zj".equals(getMsg.substring(0,2))){
				kuaidi = "zhaijisong";
				head = "宅急送单号:";
			}else{
				flag = false;
			}
			
			head = head + getMsg.substring(2) + "\n";
			
			if(flag){
				String url = "http://wap.kuaidi100.com/wap_result.jsp";
		    	String param = "rand=20131109&id=" + kuaidi + "&fromWeb=null&&postid=" + getMsg.substring(2);
		    	
				String result = HttpRequest.sendGet(url, param);
				
//				logger.log(Level.INFO, "result=" + result);
//				System.out.println("---result----->"+result);
				
				if(result.indexOf("查询结果") != -1){
					result = result.substring(result.indexOf("查询结果"));
					
					result = result.substring(result.indexOf("<p>"));
					
					result = result.substring(0,result.indexOf("<div"));
					
					Document doc = Jsoup.parse(result);
					Elements ps = doc.getElementsByTag("p");
					
					StringBuffer sb = new StringBuffer();
					
					for (Element p : ps) { 
						sb.append(p.ownText().substring(0,20) + "\n ");
						sb.append(p.ownText().substring(21) + "\n");
					}
					
					msg = head + sb.toString() + 
						  "---------------\n" +
						  "回复:\"0\"，回到主菜单";
				}else{
					msg = head + "此单号暂无物流信息，请确认单号是否正确或稍后再查。\n" +
						  "---------------\n" +
						  "回复:\"0\"，回到主菜单";
				}
			}
		}
		
		if("2".equals(getMsg)){
		  
		}
		
		message.setContent(msg);
	}
	
	//处理事件
	public void doEvent(Message message){
		String msg = "欢迎光临";
		
		if("subscribe".equals(message.getEvent())){ //新用户订阅处理
			msg = "欢迎光临爱好者，这里将带来你所感兴趣的很多东西哦！\n---------------\n回复:\"0\"，将看到所有功能菜单";
		}
		
		message.setContent(msg);
	}
	
}

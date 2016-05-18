package com.liker.service;

import java.util.Date;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liker.domain.Message;

public class WeiXinService {

	MsgService msgService = new MsgService();
	
	/**
	 * 读取xml字符串，返回xml文档
	 * @param xmlText
	 * @return
	 */
	public Document readMsg(String xmlText){
		Document document = null;
		try {
			//字符串转XML文档
			document = DocumentHelper.parseText(xmlText);

		} catch (DocumentException e) {
			e.printStackTrace();
		}

		return document;
	}

	public Document createDocument(Message message) {

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("xml");

		if("text".equals(message.getMsgType())){ //回复文本消息

			msgService.doText(message);
			
			root.addElement("ToUserName").addText(message.getFromUserName());
			root.addElement("FromUserName").addText(message.getToUserName());
			root.addElement("CreateTime").addText(String.valueOf(new Date().getTime()));
			root.addElement("MsgType").addText("text");
			root.addElement("Content").addText(message.getContent());
			
		}else if("image".equals(message.getMsgType())){ //图片消息
			
		}else if("voice".equals(message.getMsgType())){ //语音消息
			
		}else if("video".equals(message.getMsgType())){ //视频消息
			
		}else if("location".equals(message.getMsgType())){ //地理位置消息
			
		}else if("link".equals(message.getMsgType())){ //链接消息
			
		}else if("event".equals(message.getMsgType())){ //链接消息
			msgService.doEvent(message);
			
			root.addElement("ToUserName").addText(message.getFromUserName());
			root.addElement("FromUserName").addText(message.getToUserName());
			root.addElement("CreateTime").addText(String.valueOf(new Date().getTime()));
			root.addElement("MsgType").addText("text");
			root.addElement("Content").addText(message.getContent());
		}
		
		
		return document;
	}


	public Message parseXml(Document document){
		//获取根目录
		Element xml = document.getRootElement();
		Message message = new Message();

		//遍历第一层节点
		for(Iterator<?> i = xml.elementIterator(); i.hasNext();){   
			Element element = (Element) i.next();   

			//将所有类型的消息要素全部封装在一个message类里
			if("ToUserName".equals(element.getName())){
				message.setToUserName(element.getText());
			}else if("FromUserName".equals(element.getName())){
				message.setFromUserName(element.getText());
			}else if("CreateTime".equals(element.getName())){
				message.setCreateTime(element.getText());
			}else if("MsgType".equals(element.getName())){
				message.setMsgType(element.getText());
			}else if("MsgId".equals(element.getName())){
				message.setMsgId(element.getText());
			}else if("Content".equals(element.getName())){ //消息类型
				message.setContent(element.getText());
			}else if("MediaId".equals(element.getName())){
				message.setMediaId(element.getText());
			}else if("PicUrl".equals(element.getName())){
				message.setPicUrl(element.getText());
			}else if("Format".equals(element.getName())){
				message.setFormat(element.getText());
			}else if("ThumbMediaId".equals(element.getName())){
				message.setThumbMediaId(element.getText());
			}else if("Location_X".equals(element.getName())){
				message.setLocationX(element.getText());
			}else if("Location_Y".equals(element.getName())){
				message.setLocationY(element.getText());
			}else if("Scale".equals(element.getName())){
				message.setScale(element.getText());
			}else if("Label".equals(element.getName())){
				message.setLabel(element.getText());
			}else if("Title".equals(element.getName())){
				message.setTitle(element.getText());
			}else if("Description".equals(element.getName())){
				message.setDescription(element.getText());
			}else if("Url".equals(element.getName())){
				message.setUrl(element.getText());
			}else if("Event".equals(element.getName())){
				message.setEvent(element.getText());
			}

		}   
		
//		System.out.println(message.toString());

		return message;
	}

	public String sendMessage(String msg){

		Message message = parseXml(readMsg(msg));

		Document document = createDocument(message);

		//XML文档转换成字符串
		String text = document.asXML();

		return text;
	}

	//test
	public static void main(String[] args){
		WeiXinService wxService = new WeiXinService();

		String xmlText = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";

		Document document = wxService.readMsg(xmlText);
		Message message = wxService.parseXml(document);
		System.out.println(message.toString());

		String result = wxService.sendMessage(xmlText);
		System.out.println(result);
	}
}

package com.liker.domain;


public class Message {

	private String toUserName; //开发者微信号
	private String fromUserName; //发送方帐号（一个OpenID）
	private String createTime; //消息创建时间 （整型）
	private String msgType; //消息类型
	private String msgId; //消息id，64位整型
	
	private String content; //文本消息内容
	
	private String mediaId; //消息媒体id，可以调用多媒体文件下载接口拉取数据
	
	private String picUrl; //图片链接
	
	private String format; //语音格式，如amr，speex等
	
	private String thumbMediaId; //视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	
	private String locationX; //地理位置纬度
	private String locationY; //地理位置经度
	private String scale; //地图缩放大小
	private String label; //地理位置信息
	
	private String title; //链接消息标题
	private String description; //链接消息描述
	private String url; //链接地址
	
	private String event; //事件类型，subscribe(订阅)、unsubscribe(取消订阅)
	
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public String getLocationX() {
		return locationX;
	}
	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}
	public String getLocationY() {
		return locationY;
	}
	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	public String toString() {
		return "Message [toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + ", msgId=" + msgId + ", content=" + content
				+ ", mediaId=" + mediaId + ", picUrl=" + picUrl + ", format="
				+ format + ", thumbMediaId=" + thumbMediaId + ", locationX="
				+ locationX + ", locationY=" + locationY + ", scale=" + scale
				+ ", label=" + label + ", title=" + title + ", description="
				+ description + ", url=" + url + ", event=" + event + "]";
	}

}

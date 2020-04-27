package com.syq.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器处理结果类
 * @author THINKPAD
 *
 */
public class Msg {
	
	private int code;//状态码，100-成功，200-失败
	private String message;//处理结果信息
	private Map<String,Object> extend = new HashMap<String,Object>();//用户返回给浏览器的数据
	
	//处理成功返回信息
	public static Msg success(){
		Msg msg = new Msg();
		msg.setCode(100);
		msg.setMessage("success!");
		return msg;
	}
	
	//处理失败返回信息
	public static Msg fail(){
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setMessage("failed!");
		return msg;
	}
	
	//定义快捷的链式操作
	public Msg add(String key,Object value){
		this.extend.put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}

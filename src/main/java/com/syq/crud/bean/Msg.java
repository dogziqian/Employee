package com.syq.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ��������������
 * @author THINKPAD
 *
 */
public class Msg {
	
	private int code;//״̬�룬100-�ɹ���200-ʧ��
	private String message;//��������Ϣ
	private Map<String,Object> extend = new HashMap<String,Object>();//�û����ظ������������
	
	//����ɹ�������Ϣ
	public static Msg success(){
		Msg msg = new Msg();
		msg.setCode(100);
		msg.setMessage("success!");
		return msg;
	}
	
	//����ʧ�ܷ�����Ϣ
	public static Msg fail(){
		Msg msg = new Msg();
		msg.setCode(200);
		msg.setMessage("failed!");
		return msg;
	}
	
	//�����ݵ���ʽ����
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

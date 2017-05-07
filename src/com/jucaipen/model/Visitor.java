package com.jucaipen.model;

/**
 * @author ÑîÀÊ·É
 *   ·Ã¿Í¼ÇÂ¼
 */
public class Visitor {
	private int id;
	private int userId;
	private String ip;
	private int devType;
	private String head;
	private String insertDate;
	private String param;
	private String url;
	private String host;
	private String hostAddress;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHostAddress() {
		return hostAddress;
	}
	public void setHostAddress(String hostAddress) {
		this.hostAddress = hostAddress;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getDevType() {
		return devType;
	}
	public void setDevType(int devType) {
		this.devType = devType;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
}

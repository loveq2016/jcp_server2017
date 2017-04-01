package com.jucaipen.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LevBean implements Serializable{
	private int id;
	private String uid;
	private String vid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
}

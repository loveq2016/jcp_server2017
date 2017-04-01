package com.jucaipen.model;

import java.io.Serializable;

/**
 * @author Administrator
 *
 *  …Û∫ÀΩ≤ ¶œÍœ∏–≈œ¢
 */
public class ApplyDetails implements Serializable{
	private static final long serialVersionUID = 2484411573194481041L;
	
	private int id;
	
	private int fk_applyId;
	
	private String cause;
	
	private String passDate;
	
	private String auditor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFk_applyId() {
		return fk_applyId;
	}

	public void setFk_applyId(int fk_applyId) {
		this.fk_applyId = fk_applyId;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getPassDate() {
		return passDate;
	}

	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

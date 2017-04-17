package com.metric.domain;


/**
 * An entity Info composed by 2 fields (code, description).
 *
 * @author Kenny
 */
public class Info {
	private String code = null;
	private String description = null;
	public Info() {
	
	}
	public Info(String code, String description){
		super();
		this.code = code;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

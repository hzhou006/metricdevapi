package com.metric.response;

import com.metric.domain.Info;

/**
 * Response to get the min value from metric Info
 *
 * @author Kenny
 */
public class MinValueResponse {
	private String id;
	private Double minValue;
	private Info info = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
}

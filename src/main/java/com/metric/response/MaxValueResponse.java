package com.metric.response;

import com.metric.domain.Info;

/**
 * Response to get the max value from metric Info
 *
 * @author Kenny
 */
public class MaxValueResponse {
	private String id;
	private Double maxValue;
	private Info info = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
}

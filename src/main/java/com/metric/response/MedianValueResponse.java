package com.metric.response;

import com.metric.domain.Info;

/**
 * Response to get the median value from metric Info
 *
 * @author Kenny
 */
public class MedianValueResponse {
	private String id;
	private Double medianValue;
	private Info info = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMedianValue() {
		return medianValue;
	}
	public void setMedianValue(Double medianValue) {
		this.medianValue = medianValue;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
}

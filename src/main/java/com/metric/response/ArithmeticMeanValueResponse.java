package com.metric.response;

import com.metric.domain.Info;

/**
 * Response to get the Arithmetic Mean Value Info
 *
 * @author Kenny
 */
public class ArithmeticMeanValueResponse {
	private String id;
	private Double meanValue;
	private Info info = null;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMean() {
		return meanValue;
	}
	public void setMean(Double meanValue) {
		this.meanValue = meanValue;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
}

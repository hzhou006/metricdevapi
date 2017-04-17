package com.metric.response;

import com.metric.domain.Info;
import com.metric.domain.MetricBean;

/**
 * Response to add elements to the Metric Info
 *
 * @author Kenny
 */
public class InsertResponse {
	private Info info = null;
	private MetricBean metric;
	public MetricBean getMetric() {
		return metric;
	}
	public void setMetric(MetricBean metric) {
		this.metric = metric;
	}
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
}

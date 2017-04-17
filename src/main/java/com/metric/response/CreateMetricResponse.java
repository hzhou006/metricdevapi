package com.metric.response;

import com.metric.domain.Info;
import com.metric.domain.MetricsBean;
/**
 * Response to Create the Metric Info
 *
 * @author Kenny
 */
public class CreateMetricResponse {
	private MetricsBean metrics;
	private Info info = null;
	
	
	public MetricsBean getMetrics() {
		return metrics;
	}
	public void setMetrics(MetricsBean metrics) {
		this.metrics = metrics;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}

}

package com.metric.domain;

import java.util.HashMap;

import java.util.PriorityQueue;

/**
 * An entity Metrics composed by 2 fields (current list id, all of metrics).
 *
 * @author Kenny
 */
public class MetricsBean {
	private String newMetricId;
	private HashMap<String,PriorityQueue<Double>> metric = null;
	public MetricsBean(){}
    public MetricsBean(String newMetricId, HashMap<String,PriorityQueue<Double>> metric){
    	this.newMetricId = newMetricId;
    	this.metric = metric;
    }
	public String getNewMetricId() {
		return newMetricId;
	}
	public void setNewMetricId(String newMetricId) {
		this.newMetricId = newMetricId;
	}
	public HashMap<String,PriorityQueue<Double>> getMetric() {
		return metric;
	}
	public void setMetric(HashMap<String,PriorityQueue<Double>> metric) {
		this.metric = metric;
	}
	
}

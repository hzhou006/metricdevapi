package com.metric.domain;


import java.util.PriorityQueue;

/**
 * An entity Metric composed by 2 fields (id, list).
 *
 * @author Kenny
 */
public class MetricBean {
	private String id;
	private PriorityQueue<Double> list;
//	private List<Double> douList;
//	private List<Long> longList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PriorityQueue<Double> getList() {
		return list;
	}
	public void setList(PriorityQueue<Double> list) {
		this.list = list;
	}

	

}


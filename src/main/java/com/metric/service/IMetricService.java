package com.metric.service;

import com.metric.response.ArithmeticMeanValueResponse;
import com.metric.response.CreateMetricResponse;
import com.metric.response.InsertResponse;
import com.metric.response.MaxValueResponse;
import com.metric.response.MedianValueResponse;
import com.metric.response.MinValueResponse;
/**
 * Define some method for the service
 *
 * @author Kenny
 */
public interface IMetricService {
	 public CreateMetricResponse createMetric();
	 public InsertResponse addElementToMetric(String id, Double value);
	 public ArithmeticMeanValueResponse meanValue(String id);
	 public MedianValueResponse medianValue(String id);
	 public MinValueResponse minValue(String id);
	 public MaxValueResponse maxValue(String id);
}

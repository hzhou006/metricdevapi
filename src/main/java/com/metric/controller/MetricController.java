package com.metric.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.metric.response.ArithmeticMeanValueResponse;
import com.metric.response.CreateMetricResponse;
import com.metric.response.InsertResponse;
import com.metric.response.MaxValueResponse;
import com.metric.response.MedianValueResponse;
import com.metric.response.MinValueResponse;
import com.metric.service.impl.MetricServiceImpl;

/**
 * @author Kenny
 * There are 6 end points to implements the web services
 * 1. Create a Metric
 * 2. Post Values to a Metric
 * 3. Get Arithmetic Mean of a values posted to metric
 * 4. Get Median of a values posted to metric
 * 5. Get Min value from metric
 * 6. Get Max value from metric
 * 
 */
@RestController
@RequestMapping("/metric")
public class MetricController {
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
    public CreateMetricResponse createMetric(){
		MetricServiceImpl metricService = new MetricServiceImpl();
		CreateMetricResponse response = metricService.createMetric();
    	return response;
    }
	@RequestMapping(value = "/insert", method = RequestMethod.POST)	
	@ResponseBody
    public InsertResponse InsertValueToMetric(@RequestBody String id,@RequestBody Double value){
		MetricServiceImpl metricService = new MetricServiceImpl();
		InsertResponse response = metricService.addElementToMetric(id, value);
    	return response;
    }
	@RequestMapping(value = "/mean/{id}", method = RequestMethod.GET)
    public ArithmeticMeanValueResponse getMetricMeanValue(@PathVariable String id){
		MetricServiceImpl metricService = new MetricServiceImpl();
		ArithmeticMeanValueResponse response = metricService.meanValue(id);
    	return response;
    }
	@RequestMapping(value = "/median/{id}", method = RequestMethod.GET)
    public MedianValueResponse getMetricMedianValue(@PathVariable String id){
		MetricServiceImpl metricService = new MetricServiceImpl();
		MedianValueResponse response = metricService.medianValue(id);
    	return response;
    }
	@RequestMapping(value = "/maxValue/{id}", method = RequestMethod.GET)
    public MaxValueResponse getMetricMaxValue(@PathVariable String id){
		MetricServiceImpl metricService = new MetricServiceImpl();
		MaxValueResponse response = metricService.maxValue(id);
    	return response;
    }
	@RequestMapping(value = "/minValue/{id}", method = RequestMethod.GET)
    public MinValueResponse getMetricMinValue(@PathVariable String id){
		MetricServiceImpl metricService = new MetricServiceImpl();
		MinValueResponse response = metricService.minValue(id);
    	return response;
    }
}

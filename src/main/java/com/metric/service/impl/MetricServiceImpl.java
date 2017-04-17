package com.metric.service.impl;


import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.metric.domain.Info;
import com.metric.domain.MetricBean;
import com.metric.domain.MetricsBean;
import com.metric.response.ArithmeticMeanValueResponse;
import com.metric.response.CreateMetricResponse;
import com.metric.response.InsertResponse;
import com.metric.response.MaxValueResponse;
import com.metric.response.MedianValueResponse;
import com.metric.response.MinValueResponse;
import com.metric.service.IMetricService;

/**
 * Implement the service method from Interface
 * @author Kenny
 * 
 */

@Service
public class MetricServiceImpl implements IMetricService {
	
	private static HashMap<String,PriorityQueue<Double>> minValueMap;
	private static HashMap<String,PriorityQueue<Double>> maxValueMap;
	private static HashMap<String,PriorityQueue<Double>> minHeapMap;
	private static HashMap<String,PriorityQueue<Double>> maxHeapMap;
	private static HashMap<String,Double> sumMap;
	PriorityQueue<Double> leftQueue; 
    PriorityQueue<Double> rightQueue;

    public MetricServiceImpl(){
    	if(minValueMap == null){
    		minValueMap = new HashMap<String,PriorityQueue<Double>>();
    	}
    	if(maxValueMap == null){
    		maxValueMap = new HashMap<String,PriorityQueue<Double>>();
    	}
    	if(minHeapMap == null){
    		minHeapMap = new HashMap<String,PriorityQueue<Double>>();
    	}
    	if(maxHeapMap == null){
    		maxHeapMap = new HashMap<String,PriorityQueue<Double>>();
    	}
    	if(sumMap == null){
    		sumMap = new HashMap<String,Double>();
    	}
    	leftQueue = new PriorityQueue<Double>(Collections.reverseOrder());
        rightQueue = new PriorityQueue<Double>();
    	
    }
	@Override
	public CreateMetricResponse createMetric() {
		MetricBean metric = new MetricBean();
		MetricsBean metrics = new MetricsBean();
		CreateMetricResponse createMetricResponse = new CreateMetricResponse();
		UUID uniqueKey = UUID.randomUUID();
		String key = uniqueKey.toString();
		metric.setId(key);
		metric.setList(new PriorityQueue<Double>());
		minValueMap.put(key, metric.getList());
       
        maxValueMap.put(key, new PriorityQueue<Double>(Collections.reverseOrder()));
        minHeapMap.put(key, new PriorityQueue<Double>());
        maxHeapMap.put(key, new PriorityQueue<Double>(Collections.reverseOrder()));
        sumMap.put(key, null);
	    try{
		    metrics.setNewMetricId(key);;
		    metrics.setMetric(minValueMap);
		   

		    
	    }catch(Exception e){
	    	createMetricResponse.setInfo(new Info("300",e.getMessage()));
	    	return createMetricResponse;
	    }
	      createMetricResponse.setMetrics(metrics);
		  createMetricResponse.setInfo(new Info("200","Success"));
		  return createMetricResponse;
	}

	@Override
	public InsertResponse addElementToMetric(String id, Double value) {
		MetricBean metric = new MetricBean();
		InsertResponse response = new InsertResponse();
		PriorityQueue<Double> maxHeap = new PriorityQueue<Double>(Collections.reverseOrder());
		PriorityQueue<Double> minHeap = new PriorityQueue<Double>();
		PriorityQueue<Double> leftQueue = new PriorityQueue<Double>(Collections.reverseOrder());
	    PriorityQueue<Double> rightQueue = new PriorityQueue<Double>();
		double sum = 0;
        if(id != null && value != null){
        	if(minValueMap.containsKey(id) && maxValueMap.containsKey(id) &&
        	sumMap.containsKey(id) && minHeapMap.containsKey(id) && 
        	maxHeapMap.containsKey(id)){
        		try{
        			if(sumMap.get(id) == null){
            			sumMap.put(id, value);
       			    }else{
       			         sum = sumMap.get(id);
       			         sum += value;
       			         sumMap.put(id, sum);
       			    }
        			minHeap = minValueMap.get(id);
        			minHeap.offer(value);
        			minValueMap.put(id, minHeap);
        			maxHeap = maxValueMap.get(id);
        			maxHeap.offer(value);
        			maxValueMap.put(id, maxHeap);
        			
        			leftQueue = maxHeapMap.get(id);
        			rightQueue = minHeapMap.get(id);
        			if(leftQueue.isEmpty() || value<=leftQueue.peek()){
        				leftQueue.offer(value);
        			}else{
        				rightQueue.offer(value);
        			}
        			if(leftQueue.size()-rightQueue.size()>1){
        				rightQueue.offer(value);
        			}else if(rightQueue.size()-leftQueue.size()>1){
        				leftQueue.offer(value);
        			}
        			maxHeapMap.put(id, leftQueue);
        			minHeapMap.put(id, rightQueue);
        			metric.setId(id);
        			metric.setList(minValueMap.get(id));
        			response.setMetric(metric);
        			response.setInfo(new Info("200","success"));
        			
        		}catch(Exception e){
        			response.setInfo(new Info("300",e.getMessage()));
        		}
        		
    		}else{
    			response.setInfo(new Info("300","id is not match"));
    		}
        }else{
        	response.setInfo(new Info("300","id or value is null"));
			
        }	
        return response;
	}
	
	

	@Override
	public ArithmeticMeanValueResponse meanValue(String id) {
		ArithmeticMeanValueResponse response = new ArithmeticMeanValueResponse();
		Double sum = null;
		Double mean = null;
		int len = 0;
		if(id != null){
			if(sumMap.containsKey(id) && minValueMap.containsKey(id)){
				try{
					sum = sumMap.get(id);
					len = minValueMap.get(id).size();
					if(len != 0){
				        mean = sum/len;
				        response.setId(id);
				        response.setMean(mean);
				        response.setInfo(new Info("200","success"));
					}else{
						response.setId(id);
				        response.setMean(mean);
						response.setInfo(new Info("300","list is null"));
					}
				}catch(Exception e){
					response.setInfo(new Info("300","Exception:"+e.getMessage()));
				}
				
			}else{
				 response.setMean(mean);
				 response.setInfo(new Info("300","list does not exist"));
				 
			}
		}else{
			 response.setMean(mean);
			 response.setInfo(new Info("300","id is null"));
			 
		}
		return response;
	}

	@Override
	public MedianValueResponse medianValue(String id) {
		MedianValueResponse response = new MedianValueResponse();
		Double medianValue = null;
		if(id != null){
			if(minHeapMap.containsKey(id) && maxHeapMap.containsKey(id)){
				try{
					leftQueue = maxHeapMap.get(id);
					rightQueue = minHeapMap.get(id);
					Double avg = null;
					if(leftQueue.isEmpty()){
						 response.setId(id);
					     response.setMedianValue( rightQueue.peek());
					     response.setInfo(new Info("200","success"));
					}else if(rightQueue.isEmpty()){
						 response.setId(id);
						 response.setMedianValue(leftQueue.peek());
						 response.setInfo(new Info("200","success"));
					}else if(leftQueue.size()>rightQueue.size()){
						 response.setId(id);
						 response.setMedianValue(rightQueue.peek());
						 response.setInfo(new Info("200","success"));
					}else if(rightQueue.size()>leftQueue.size()){
						 response.setId(id);
						 response.setMedianValue(leftQueue.peek());
						 response.setInfo(new Info("200","success"));
					}else{
						 avg = (double)(leftQueue.peek()+rightQueue.peek())/2;
						 response.setId(id);
						 response.setMedianValue(avg);
						 response.setInfo(new Info("200","success"));
					}
				}catch(Exception e){
					 response.setInfo(new Info("300","Exception:"+e.getMessage()));
				}
				
			}else{
				response.setMedianValue(medianValue);
				response.setInfo(new Info("300","list does not exist"));
			}
		}else{
			response.setMedianValue(medianValue);
			response.setInfo(new Info("300","id is null"));
		}
		
		return response;

	}

	@Override
	public MinValueResponse minValue(String id) {
		MinValueResponse response = new MinValueResponse();
		Double minValue = null;
		if(id != null){
			if(minValueMap.containsKey(id)){
				rightQueue = minValueMap.get(id);
				if(!rightQueue.isEmpty()){
					try{
						minValue = rightQueue.peek();
						response.setId(id);
						response.setMinValue(minValue);
						response.setInfo(new Info("200","success"));
					}catch(Exception e){
						response.setInfo(new Info("300","Exception:"+e.getMessage()));
					}
					
				}else{
					response.setId(id);
					response.setMinValue(minValue);
					response.setInfo(new Info("300","list is null"));
				}
				
			}else{
				response.setMinValue(minValue);
				response.setInfo(new Info("300","list does not exist"));
			}
		}else{
			response.setMinValue(minValue);
			response.setInfo(new Info("300","id is null"));
		}
			
		return response;
	}

	@Override
	public MaxValueResponse maxValue(String id) {
		MaxValueResponse response = new MaxValueResponse();
		Double maxValue = null;
		if(id != null){
			if(maxValueMap.containsKey(id)){
				
				leftQueue = maxValueMap.get(id);
				if(!leftQueue.isEmpty()){	
					try{
						maxValue = leftQueue.peek();
						response.setId(id);
						response.setMaxValue(maxValue);
						response.setInfo(new Info("200","success"));
					}catch(Exception e){
						response.setInfo(new Info("300","Exception:"+e.getMessage()));
					}
					
				}else{
					response.setId(id);
					response.setMaxValue(maxValue);
					response.setInfo(new Info("300","list is null"));
				}
				
			}else{
				response.setMaxValue(maxValue);
				response.setInfo(new Info("300","list does not exist"));
			}
		}else{
			response.setMaxValue(maxValue);
			response.setInfo(new Info("300","id is null"));
		}
		return response;
	}

}
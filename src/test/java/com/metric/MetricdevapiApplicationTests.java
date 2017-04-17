package com.metric;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.metric.controller.MetricController;
import com.metric.domain.MetricsBean;

/**
 * Implement the web services unit test
 * @author Kenny
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MetricdevapiApplication.class)
@WebAppConfiguration
@TestPropertySource(properties = {"management.port=0"})
public class MetricdevapiApplicationTests {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    private String id = "a323e791-228c-4aee-9aa0-941853ec1bce";
    private List<MetricsBean> list = new ArrayList<>();
    private PriorityQueue<Double> testHeap = new PriorityQueue<Double>();

    private HashMap<String,PriorityQueue<Double>> map = new HashMap<String, PriorityQueue<Double>>();
    private HttpMessageConverter mappingJackson2HttpMessageConverter;
    

    @Autowired
	private MetricController metricController;
   


    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
            .findAny()
            .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {   
    	this.mockMvc = MockMvcBuilders.standaloneSetup(metricController)
				.build();
    	testHeap.add(5.56);
    	testHeap.add(1.56);
    	testHeap.add(3.56);
    	testHeap.add(2.56);
      
        map.put(id, testHeap);

        this.list.add(new MetricsBean(id,map));

    }

    @Test
    public void merticNotFound() throws Exception {
    	
        mockMvc.perform(post("/metric/create/")
                .content(this.json(new MetricsBean()))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
    
    @Test
    public void addElementToMetric() throws Exception {
    	
        mockMvc.perform(post("/metric/insert/"+list.get(0).getNewMetricId())
                .content(this.json(new MetricsBean()))
                .contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(this.list.get(0).getNewMetricId())));            
               
    }
    @Test
    public void getMeanValueNotFound() throws Exception {
        mockMvc.perform(get("/metric/mean/{id}",id))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getMedianValue() throws Exception {
    	mockMvc.perform(get("/metric/median/{id}",id))
        .andExpect(status().isNotFound());
    }
    @Test
    public void getMaxValue() throws Exception {
    	mockMvc.perform(get("/metric/minValue/{id}",id))
        .andExpect(status().isNotFound());
    }
    @Test
    public void getMinValue() throws Exception {
    	mockMvc.perform(get("/metric/maxVlaue/{id}",id))
        .andExpect(status().isNotFound());
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}

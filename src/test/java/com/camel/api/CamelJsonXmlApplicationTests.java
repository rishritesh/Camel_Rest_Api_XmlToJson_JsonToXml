package com.camel.api;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
class CamelJsonXmlApplicationTests {

	@Autowired
	private ProducerTemplate producerTemplate;

	@EndpointInject("mock:result")
	private org.apache.camel.component.mock.MockEndpoint mockEndpoint;

	@Test
	public void testJsonToXmlEndpoint() throws Exception {
		String jsonData = "{\"id\":\"1\",\"name\":\"java\"}";
		String expectedXml = "<root><id>1</id><name>java</name></root>";
		producerTemplate.sendBody("direct:jsonToXml", jsonData);

		// Assert that the mock endpoint received the expected JSON
		mockEndpoint.expectedBodiesReceived(expectedXml);
		mockEndpoint.assertIsSatisfied();
	}

}

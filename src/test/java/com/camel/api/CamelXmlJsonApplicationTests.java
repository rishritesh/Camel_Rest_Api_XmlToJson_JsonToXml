package com.camel.api;


import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CamelSpringBootTest
@SpringBootTest
class CamelXmlJsonApplicationTests {

	@Autowired
	private ProducerTemplate producerTemplate;

	@EndpointInject("mock:result")
	private org.apache.camel.component.mock.MockEndpoint mockEndpoint;

	@Test
	public void testXmlToJsonRoute() throws Exception {
		String xmlData = "<root><id>1</id><name>java</name></root>";
		String expectedJson = "{\"id\":\"1\",\"name\":\"java\"}";

		// Send XML to the route
		producerTemplate.sendBody("direct:xmlToJson", expectedJson);

		// Assert that the mock endpoint received the expected JSON
		mockEndpoint.expectedBodiesReceived(expectedJson);
		mockEndpoint.assertIsSatisfied();
	}

}

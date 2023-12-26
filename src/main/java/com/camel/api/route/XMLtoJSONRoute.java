package com.camel.api.route;

import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Component;

@Component
public class XMLtoJSONRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub

		// XML to JSON transformation
		from("direct:xmlToJson").to("direct:transformedData");

		// JSON to XML transformation
		from("direct:jsonToXml").unmarshal().json().marshal().jacksonXml().to("direct:transformedData");

		from("direct:transformedData").log("${body}").to("mock:result");

		

	}

}

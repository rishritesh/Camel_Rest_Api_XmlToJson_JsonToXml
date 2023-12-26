package com.camel.api.controller;

import java.util.LinkedHashMap;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
public class TransformContoller {

	@Autowired
	private ProducerTemplate producerTemplate;

	@PostMapping("/xmltojson")
	public String transformXmlToJson(@RequestBody String xml) {

		// Use Jackson's XmlMapper to read the XML
		ObjectMapper xmlMapper = new XmlMapper();
		LinkedHashMap<String, Object> map = null;
		try {
			map = xmlMapper.readValue(xml, LinkedHashMap.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Convert the LinkedHashMap to JSON
		ObjectMapper jsonMapper = new ObjectMapper();
		String json1 = null;
		try {
			json1 = jsonMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return producerTemplate.requestBody("direct:xmlToJson", json1, String.class);
	}

	@PostMapping("/jsontoxml")
	public String transformJsonToXml(@RequestBody String json) {
		return producerTemplate.requestBody("direct:jsonToXml", json, String.class);
	}

}

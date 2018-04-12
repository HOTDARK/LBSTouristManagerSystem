/*
 * Copyright © 2015, Wangding (Chengdu) Tech Co.,Ltd. 
 * All right reserved.
 */
package com.hd.workflow.app.convert.deserialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;


/**
 * 解析外链的id
 * @version	0.0.1
 * @author	<a href="mailto:sunjian@iwangding.com">孙剑</a>
 * @date	2015-3-3 上午10:48:08
 */
public class OutgoingDeserializer extends JsonDeserializer<List<String>> {

	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		List<String> list = new ArrayList<String>();
		
		JsonNode node = jp.getCodec().readTree(jp);
		if(node.isArray()){
			int size = node.size();
			for(int i=0;i<size;i++){
				JsonNode item = node.get(i);
				list.add(item.get("resourceId").asText());
			}
		}
		
		return list;
	}

}

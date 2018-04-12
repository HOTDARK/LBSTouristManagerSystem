package com.hd.sfw.core.utils.json;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.introspect.AnnotatedClass;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.StdSerializerProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
import org.codehaus.jackson.map.ser.std.NullSerializer;
import org.codehaus.jackson.type.TypeReference;

/**
 * json转换工具类
 * @author somnuscy
 *
 */
public class JsonUtils {
	public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	private static final Logger log = Logger.getLogger(JsonUtils.class);

	private static boolean isPretty = false;

	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private static StdSerializerProvider sp = new StdSerializerProvider();

	public static boolean isPretty() {
		return isPretty;
	}

	public static void setPretty(boolean isPretty) {
		JsonUtils.isPretty = isPretty;
	}

	static {
		sp.setNullValueSerializer(NullSerializer.instance);
	}
	
	/**
	 * JSON串转换为Java泛型对象，可以是各种类型。通过TypeReference可以进行复杂类型的转换，详细可见jackson文档
	 *  
	 * @param jsonString JSON字符串
	 * @param tr 
	 * @param dateFormat 时间格式，可以不传入。默认:yyyy-MM-dd HH:mm:ss
	 * @return 返回Java对象，如果转换失败返回null
	 */
	@SuppressWarnings("unchecked")
	public static <T> T json2GenericObject(String jsonString,TypeReference<T> tr, String dateFormat) {
		if (StringUtils.isNotEmpty(jsonString)) {
			ObjectMapper mapper = new ObjectMapper(null, sp, null);
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.setDateFormat(getDateFormat(dateFormat));
			
			try {
				return (T)mapper.readValue(jsonString, tr);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
			
		}
		
		return null;
	}
	
	/**
	 * Json字符串转Java对象
	 * 
	 * @param jsonString
	 * @param c
	 * @param dateFormat 时间格式，可以不传入。默认:yyyy-MM-dd HH:mm:ss
	 * @return 返回Java对象，如果转换失败返回null
	 */
	public static <T> T json2Object(String jsonString, Class<T> c,String dateFormat) {
		if (StringUtils.isNotEmpty(jsonString)) {
			
			ObjectMapper mapper = new ObjectMapper(null, sp, null);
			mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
			mapper.setDateFormat(getDateFormat(dateFormat));
			
			try {
				return mapper.readValue(jsonString, c);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
			}
			
		}
		
		return null;
	}
	

	/**
	 * java对象转换为json字符串<br>
	 * 如果includeFields不为空则对executeFields的设置将不生效
	 * 
	 * @param object
	 * @param executeFields 排除属性
	 * @param includeFields 包含属性
	 * @param dateFormat dateFormat 时间格式，可以不传入。默认:yyyy-MM-dd HH:mm:ss
	 * @return 输出json串，如果转换失败返回空串
	 */
	public static String toJson(Object object, String[] executeFields,String[] includeFields, String dateFormat) {
		String jsonString = "";
		
		ObjectMapper mapper = new ObjectMapper(null, sp, null);
		mapper.setDateFormat(getDateFormat(dateFormat));
		
		FilterProvider filterProvider = null;
		if (includeFields != null) {
			filterProvider = new SimpleFilterProvider().addFilter("filter", SimpleBeanPropertyFilter.filterOutAllExcept(includeFields));
		} else if (executeFields != null) {
			filterProvider = new SimpleFilterProvider().addFilter("filter", SimpleBeanPropertyFilter.serializeAllExcept(executeFields));
		}
		
		if(filterProvider!=null){
			mapper.setFilters(filterProvider);
			mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector(){
				@Override
				public Object findFilterId(AnnotatedClass ac) {
					return "filter";
				}
			});
		}
		
		try {	
			if (isPretty) {
				jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			} else {
				jsonString = mapper.writeValueAsString(object);
			}
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return jsonString;
	}
	
	
	private static DateFormat getDateFormat(String dateFormat){
		return StringUtils.isEmpty(dateFormat)?new SimpleDateFormat(DEFAULT_DATE_FORMAT):new SimpleDateFormat(dateFormat);
	}
}

package com.yhr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Properties文件工具类
 * @date 2017-08-10
 * @author yhr 
 */
public final class PropertiesUtil {

	private PropertiesUtil() {
	}
	
	private static Properties prop = new Properties();

	/**
	 * @param fileName
	 * @return key值集合
	 */
	public static List<String> getAllKeys(String fileName) {
		propLoad(fileName);
		Enumeration<Object> keys = prop.keys();
		List<String> list = new ArrayList<>();
		while (keys.hasMoreElements()) {
			String str = (String) keys.nextElement();
			list.add(str);
		}
		return list;
	}

	/**
	 * @param fileName
	 * @param single key
	 * @return 单个value
	 */
	public static String getValue(String fileName,String key) {
		propLoad(fileName);
		return (String) prop.get(key);
	}
	
	/**
	 * @param fileName
	 * @param 自定义个数key
	 * @return 多个value
	 */
	public static List<String> getValues(String fileName,String... key) {
		List<String> values=new ArrayList<>();
		for (String string : key) {
			values.add(getValue(fileName, string));
		}
		return values;
	}
	
	/**
	 * @param fileName
	 * @return 全部value
	 */
	public static List<String> getAllValues(String fileName) {
		List<String> values=new ArrayList<>();
		Collection<Object> list = prop.values();
		Iterator<Object> iterator = list.iterator();
		while (iterator.hasNext()) {
			values.add((String) iterator.next());
		}
		return values;
	}
	
	/**
	 * @param fileName
	 * @return 全部value
	 */
	public static Map<String, String> getAllKeysAndValues(String fileName) {
		Map<String, String> kvs=new HashMap<>();
		List<String> allKeys = getAllKeys(fileName);
		List<String> allValues = getAllValues(fileName);
		for(int i=0;i<allKeys.size();i++){
			kvs.put(allKeys.get(i), allValues.get(i));
		}
		return kvs;
	}
	

	public static void propLoad(String fileName) {
		try {
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String fileName="my.propertis";
		List<String> keys = PropertiesUtil.getAllKeys(fileName);
		System.out.println("keys："+Arrays.asList(keys));
		System.out.println("values："+Arrays.asList(PropertiesUtil.getAllValues(fileName)));
		System.out.println("single value："+Arrays.asList(PropertiesUtil.getValue(fileName, "aaa")));
		String[] str=new String[]{"aaa","bbb"};
		System.out.println("single value："+Arrays.asList(PropertiesUtil.getValues(fileName, str)));
		Map<String, String> map = PropertiesUtil.getAllKeysAndValues(fileName);
	}

}

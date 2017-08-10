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
	
	private static String PropertiesFilePath="";
	
	private PropertiesUtil() {
	}
	
	private static class PeopertiesUtilsHandler{
		private static PropertiesUtil instance=new PropertiesUtil();
	}
	
	public static PropertiesUtil getInstance(){
		return PeopertiesUtilsHandler.instance;
	}
	
	public static PropertiesUtil setFilePathS(String filePath){
		PropertiesFilePath=filePath;
		return PropertiesUtil.getInstance();
	}
	
	private static Properties prop = new Properties();

	/**
	 * @param fileName
	 * @return key值集合
	 */
	public List<String> getAllKeys() {
		propLoad();
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
	public String getValue(String key) {
		propLoad();
		return (String) prop.get(key);
	}
	
	/**
	 * @param fileName
	 * @param 自定义个数key
	 * @return 多个value
	 */
	public List<String> getValues(String... key) {
		List<String> values=new ArrayList<>();
		for (String string : key) {
			values.add(getValue(string));
		}
		return values;
	}
	
	/**
	 * @param fileName
	 * @return 全部value
	 */
	public List<String> getAllValues() {
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
	public Map<String, String> getAllKeysAndValues() {
		Map<String, String> kvs=new HashMap<>();
		List<String> allKeys = getAllKeys();
		List<String> allValues = getAllValues();
		for(int i=0;i<allKeys.size();i++){
			kvs.put(allKeys.get(i), allValues.get(i));
		}
		return kvs;
	}
	

	public static void propLoad() {
		try {
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(PropertiesFilePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String fileName="my.propertis";
		List<String> keys = PropertiesUtil.setFilePathS(fileName).getAllKeys();
		System.out.println("values："+Arrays.asList(PropertiesUtil.setFilePathS(fileName).getAllValues()));
	}

}

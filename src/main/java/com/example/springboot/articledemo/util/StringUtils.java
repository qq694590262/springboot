package com.example.springboot.articledemo.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class StringUtils {
	public static boolean isEmpty(String str) {
		return null == str || str.equals("") || str.matches("\\s*");
	}
	
	public static String defaultValue(String content,String defaultValue) {
		if (isEmpty(content)) {
			return defaultValue;
		}
		return content;
	}

	/**
	 * �ж϶����Ƿ�Ϊ��
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;

		if (obj instanceof String)
//			return StringUtils.isEmptyOrWhitespaceOnly((String) obj);
		if (obj instanceof Collection && ((Collection<?>) obj).isEmpty())
			return true;
		if (obj.getClass().isArray() && Array.getLength(obj) == 0)
			return true;
		if (obj instanceof Map && ((Map<?, ?>) obj).isEmpty())
			return true;

		return false;
	}
	
	/**
	 * �����ݿ��ֶ�����Ϊ�շ巽ʽ
	 * @param column
	 * @return
	 */
	public static String columnToProperty(String column) {
	    /**����ֶ���Ϊ�գ��ͷ��ؿ��ַ���* */
	    if(isEmpty(column)) return "";
	    /**��ȡ�ֶεĳ��ȣ�һ����˵�ֶγ��Ȳ������м��ٸ��ֽڵģ�������Byte������* */
	    Byte length = (byte) column.length();
	    
	    StringBuilder sb = new StringBuilder(length);
	    int i = 0;
	    /**�����ֶε�ÿһ���ַ�* */
	    for (int j = 0; j < length; j++) {
	         /**ƥ�䵽��һ��_* */
	        if (column.charAt(j) == '_') {
	            /**������滹��_,Ҳ����������_,��ôj����Ҫ����һ����λ��ֱ�����治��_Ϊֹ* */
	            while (column.charAt(j + 1) == '_') {
	                j += 1;
	            }
	            sb.append(("" + column.charAt(++j)).toUpperCase());
	            
	        } else {
	             /**���ѭ�������ַ�����_,��ô�ͱ�������* */
	                sb.append(column.charAt(j));
	            }
	        }
	 
	     return sb.toString();
	 }
	    
	/**
	 * ��һ���ַ���������ĸ�ĳɴ�д
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstCharacter(String str){
	    StringBuilder sb = new StringBuilder();
	    char[] arr = str.toCharArray();
	    for (int i = 0; i < arr.length; i++) {
	        if(i==0) sb.append((arr[i] + "").toUpperCase());
	        else sb.append((arr[i]+""));
	    }
	    return sb.toString();
	}
}
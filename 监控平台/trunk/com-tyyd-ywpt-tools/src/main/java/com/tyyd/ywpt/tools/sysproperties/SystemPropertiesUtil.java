/**   
* @Title: SystemPropertiesUtil.java 
* @Package com.tyyd.ywpt.tools.sysproperties 
* @Description:  
* @author wangyu   
* @date 2014-7-17 下午1:18:49 
* @CopyRight 天翼阅读
* @version V1.0   
*/
package com.tyyd.ywpt.tools.sysproperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.StringUtils;

/**
 * @author wangyu
 *
 */
public class SystemPropertiesUtil {

	/**
	 * 根据 properties 中设置的 key value 值设置系统环境变量
	 * 
	 * @param properties
	 */
	public static Map<String, String> setSystemProperties(Properties properties) {

		Map<String, String> ret = new HashMap<String, String>();

		for (Map.Entry<Object, Object> entry : properties.entrySet()) {

			String key = (String) entry.getKey();
			String oldValue = System.getProperty(key);
			String value = (String) entry.getValue();
			
			if (oldValue != null && !StringUtils.pathEquals(oldValue, value)) {
				throw new IllegalStateException("the system property already set to different value: '" + key + "'" + " = [" + oldValue + "] instead of [" + value + "]");
			}

			System.setProperty(key, value);
			ret.put(key, value);
		}
		return ret;
	}

	/**
	 * 根据 properties 中的 key 和 value 删除系统环境变量
	 * 
	 * @param properties
	 */
	public static void removeSystemProperties(Properties properties) {
		
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			String key = (String) entry.getKey();
			System.getProperties().remove(key);
		}
		
	}
	
}

package com.ljx.tcwjneln._09util.maputil;

import java.util.HashMap;
import java.util.Map;

/**
 *
* @Description:  提供从Map中获取指定类型数据的功能
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
* @Remark:
* @CodeBug解决:
* @date 2021年3月15日 下午5:17:30
* @author  ljx
*
 */
public class MapGetter {
	public static void main(String[] args) {

	}
	/**
	 *
	 * @Title: newHashMapWithExpectedSize
	 * @Description: 根据预期要创建HashMap的大小,创建HashMap
	 * @param expectedSize 预期要创建HashMap的大小
	 * @return HashMap &lt;K, V&gt; 返回HashMap对像
	 */
	public static <K, V> HashMap<K, V> newHashMapWithExpectedSize(int expectedSize) {
	    return new HashMap<K, V>(initialCapacity (expectedSize));
	}

	/**
	 *
	 * @Title: initialCapacity
	 * @Description: 根据预期要创建HashMap的大小，计算实际初始化容量值
	 * @param expectedSize 预期要创建HashMap的大小
	 * @return int 返回类型
	 */
	public static int initialCapacity (int expectedSize) {
	      return (int) (expectedSize / 0.75F + 1.0F);
	}
	/**
	 *
	 * @Title: defaultInitialCapacity
	 * @Description: 获取默认值(16)，计算实际初始化容量值
	 * @return int 返回类型
	 */
	public static int defaultInitialCapacity () {
		return (int) (16 / 0.75F + 1.0F);
	}

	/**
	 *
	 * @Title: getString
	 * @Description: 获取字符串
	 * @param map &lt;String, Object&gt; 指定Map对象
	 * @param key 指定Key值
	 * @return String 返回类型
	 */
	public static String getString(Map<String, Object> map, String key) {
		try {
			String reValue = "";
			Object objValue = map.get(key);
			if (null == objValue) {
				return reValue;
			}

			if (objValue instanceof String) {
				// reValue = ((String) objValue).replaceAll(".*([';]+|(--)+).*","");
				reValue = ((String) objValue).replaceAll("'", "‘");
				// reValue = ((String) objValue);
			} else if (objValue instanceof Integer) {
				reValue = String.valueOf(((Integer) objValue).intValue());
			} else {
				reValue = objValue.toString();
			}

			return reValue;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}

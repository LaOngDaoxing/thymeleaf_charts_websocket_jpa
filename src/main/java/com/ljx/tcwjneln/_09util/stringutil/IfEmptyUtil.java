package com.ljx.tcwjneln._09util.stringutil;

import java.util.Collection;
import java.util.Map;

import com.ljx.tcwjneln._09util.constantutil.ConstantUtil;
import org.apache.commons.lang3.StringUtils;

/**
 *
* @Description: 判断是否空的工具类
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
* @Remark:
* @CodeBug解决:
* @date 2021年5月6日 下午3:02:28
* @author  ljx
*
 */
public class IfEmptyUtil {
	public static void main(String[] args) throws Exception {

	}
	/**
	 *
	* @Description: 判断Long是否空
	* @CodeSteps：
	* @param objL
	* @return
	* @throws
	* @ApiGrammer规则：
		1、long 是基本数据类型，不可能 为""，也不可能为null；如果没有初始化是不能够使用的，编译器会直接报错。
		2、Long 是long的包装类，是一个类，类就存在了空值的情况，没有初始化的时候调用方法或者属性会造成空指针异常。若有默认值则为0，无默认值时为null；不可能是""。
	* @Remark:
	 */
	public static boolean ifLongIsEmpty(Long objL) {
		boolean bool = false;
		try {
			// 必须先判断null，否则会抛异常
			if(objL == null || objL == 0) {
				bool = true;
			}
		}catch(Exception e) {
			e.getLocalizedMessage();
		}
		return bool;
	}
	public static boolean ifLongIsEmptyFun2(Long objL) {
		// Long转换为String
		String str=String.valueOf(objL);
		// 再判断
		boolean bool = isEmpty(str);
		return bool;
	}
	/**
	 *
	* @Description: 判断一个字符串是否为空。
	* @param str
	* @return true：空串 false：非空串
	* @throws
	* @Remark
	 */
	public static boolean isEmpty(String str) {
		boolean bool = false;
		if(isNull(str) || ConstantUtil.STR_EMPTY.equals(str.trim()) || str.trim().length() <=0 || ConstantUtil.STR_LOWER_NULL.equals(str.trim().toLowerCase()) || StringUtils.isBlank(str) || str.isEmpty()) {
			// 字符串为空
			bool = true;
		}
		return bool;
	}
    /**
     *
    * @Description: 判断一个字符串是否为非空
    * @CodeSteps：
    * @param str
    * @return true：非空串 false：空串
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isNotEmpty(String str) {
    	boolean bool=!isEmpty(str);
        return bool;
    }
    /**
     *
    * @Description: 判断一个对象是否为空
    * @CodeSteps：
    * @param object
    * @return true：为空 false：非空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isNull(Object object) {
    	boolean bool=object == null;
        return bool;
    }
    /**
     *
    * @Description: 判断一个对象是否非空
    * @CodeSteps：
    * @param object
    * @return true：非空 false：空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isNotNull(Object object) {
    	boolean bool=!isNull(object);
        return bool;
    }

    /**
     *
    * @Description: 判断一个Map是否为空
    * @CodeSteps：
    * @param map
    * @return true：为空 false：非空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isEmpty(Map<?, ?> map) {
    	boolean bool=(isNull(map) || map.isEmpty());
        return bool;
    }
    /**
     *
    * @Description: 判断一个Map是否为空
    * @CodeSteps：
    * @param map
    * @return true：非空 false：空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
    	boolean bool=!isEmpty(map);
    	return bool;
    }
    /**
     *
    * @Description: 获取参数不为空值
    * @CodeSteps：
    * @param value
    * @param defaultValue
    * @return
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static <T> T nvl(T value, T defaultValue) {
    	T bool=(value != null ? value : defaultValue);
    	return bool;
    }
    /**
     *
    * @Description: 断一个Collection是否为空， 包含List，Set，Queue
    * @CodeSteps：
    * @param coll
    * @return true：为空 false：非空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isEmpty(Collection<?> coll) {
    	boolean bool=isNull(coll) || coll.isEmpty();
        return bool;
    }
    /**
     *
    * @Description: 判断一个Collection是否非空，包含List，Set，Queue
    * @CodeSteps：
    * @param coll
    * @return true：非空 false：空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isNotEmpty(Collection<?> coll) {
    	boolean bool=!isEmpty(coll);
        return bool;
    }
    /**
     *
    * @Description: 判断一个对象数组是否为空
    * @CodeSteps：
    * @param objArr
    * @return true：为空 false：非空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isEmpty(Object[] objArr) {
    	boolean bool=isNull(objArr) || (objArr.length == 0);
        return bool;
    }
    /**
     *
    * @Description: 判断一个对象数组是否非空
    * @CodeSteps：
    * @param objArr
    * @return true：非空 false：空
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static boolean isNotEmpty(Object[] objArr) {
    	boolean bool=!isEmpty(objArr);
        return bool;
    }
}

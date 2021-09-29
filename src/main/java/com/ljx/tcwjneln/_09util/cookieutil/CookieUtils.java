package com.ljx.tcwjneln._09util.cookieutil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
* @Description: Cookie 工具类
* @FR功能需求：	用户登录时，后台接口在Cookie中存储   cookie最大 有效时间|多长时间后失效 /秒；用于自动退出浏览器网页。
* @ImportJar:
* @ApiGrammer规则：
* @Remark:
* @AlibabaCodeStatuteScanError：
* @CodeBug解决:
* @Debug调试：
* @date 2021年9月28日 下午3:00:58
* @author  ljx
*
 */
public final class CookieUtils {
	/**
	 *
	* @Description: 获取Cookie的值，不编码
	* @CodeSteps：
	* @param request
	* @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
	* @return
	* @throws
	* @ApiGrammer规则：
	* @Remark:
	 */
    public static String getcookieVal(HttpServletRequest request, String cookieKey) {
        return getcookieVal(request, cookieKey, false);
    }
    /**
     *
    * @Description:  获取Cookie的值
    * @CodeSteps：
    * @param request
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param ifEncode				是否编码
    * @return
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static String getcookieVal(HttpServletRequest request, String cookieKey, boolean ifEncode) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieKey == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieKey)) {
                    if (ifEncode) {
                        retValue = URLDecoder.decode(cookieList[i].getValue(), "UTF-8");
                    } else {
                        retValue = cookieList[i].getValue();
                    }
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retValue;
    }
    /**
     *
    * @Description: 获取Cookie的值
    * @CodeSteps：
    * @param request
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param charset				编码方式
    * @return
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static String getcookieVal(HttpServletRequest request, String cookieKey, String charset) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieKey == null) {
            return null;
        }
        String retValue = null;
        try {
            for (int i = 0; i < cookieList.length; i++) {
                if (cookieList[i].getName().equals(cookieKey)) {
                    retValue = URLDecoder.decode(cookieList[i].getValue(), charset);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
        	 e.printStackTrace();
        }
        return retValue;
    }
    /**
     *
    * @Description:  设置Cookie的值，不设置有效时间（默认浏览器关闭即失效），也不编码
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
            String cookieVal) {
        setCookie(request, response, cookieKey, cookieVal, -1);
    }
    /**
     *
    * @Description:  设置Cookie的值，在指定时间内有效，但不编码
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @param maxCookieEffectTime	cookie最大 有效时间|多长时间后失效 /秒
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
            String cookieVal, int maxCookieEffectTime) {
        setCookie(request, response, cookieKey, cookieVal, maxCookieEffectTime, false);
    }
    /**
     *
    * @Description:  设置Cookie的值，不设置有效时间，但编码
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @param ifEncode				是否编码
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
            String cookieVal, boolean ifEncode) {
        setCookie(request, response, cookieKey, cookieVal, -1, ifEncode);
    }
    /**
     *
    * @Description:  设置Cookie的值，在指定时间内有效， 编码参数
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @param maxCookieEffectTime	cookie最大 有效时间|多长时间后失效 /秒
    * @param ifEncode				是否编码
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
            String cookieVal, int maxCookieEffectTime, boolean ifEncode) {
        doSetCookie(request, response, cookieKey, cookieVal, maxCookieEffectTime, ifEncode);
    }
    /**
     *
    * @Description:  设置Cookie的值，在指定时间内有效，编码参数(指定编码)
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @param maxCookieEffectTime	cookie最大 有效时间|多长时间后失效 /秒
    * @param charset				编码方式
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey,
            String cookieVal, int maxCookieEffectTime, String charset) {
        doSetCookie(request, response, cookieKey, cookieVal, maxCookieEffectTime, charset);
    }
    /**
     *
    * @Description:  删除Cookie带cookie域名
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieKey) {
        doSetCookie(request, response, cookieKey, "", -1, false);
    }
    /**
     *
    * @Description:  设置Cookie的值，并使其在指定时间内为最大有效时间
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @param maxCookieEffectTime	cookie最大 有效时间|多长时间后失效 /秒
    * @param ifEncode				是否编码
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieKey, String cookieVal, int maxCookieEffectTime, boolean ifEncode) {
        try {
            if (cookieVal == null) {
                cookieVal = "";
            } else if (ifEncode) {
                cookieVal = URLEncoder.encode(cookieVal, "utf-8");
            }
            Cookie cookie = new Cookie(cookieKey, cookieVal);
            if (maxCookieEffectTime > 0){
                cookie.setMaxAge(maxCookieEffectTime);
            }
            if (null != request) {
            	// 获取cookie的域名
            	String domainName = getDomainName(request);
            	System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                	// 设置域名的cookie
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }
    /**
     *
    * @Description:  设置Cookie的值，并使其在指定时间内有效
    * @CodeSteps：
    * @param request
    * @param response
    * @param cookieKey				ConstantUtil.TOKEN_CUS_NAME_STR
    * @param cookieVal				token值（UUID）
    * @param maxCookieEffectTime	cookie最大 有效时间|多长时间后失效 /秒
    * @param charset				编码方式
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    private static final void doSetCookie(HttpServletRequest request, HttpServletResponse response,
            String cookieKey, String cookieVal, int maxCookieEffectTime, String charset) {
        try {
            if (cookieVal == null) {
                cookieVal = "";
            } else {
                cookieVal = URLEncoder.encode(cookieVal, charset);
            }
            Cookie cookie = new Cookie(cookieKey, cookieVal);
            if (maxCookieEffectTime > 0){
                cookie.setMaxAge(maxCookieEffectTime);
            }

            if (null != request) {
            	// 获取cookie的域名
            	String domainName = getDomainName(request);
            	System.out.println(domainName);
                if (!"localhost".equals(domainName)) {
                	// 设置域名的cookie
                	cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }
    /**
     *
    * @Description:  获取cookie的域名
    * @CodeSteps：
    * @param request
    * @return
    * @throws
    * @ApiGrammer规则：
    * @Remark:
     */
    private static final String getDomainName(HttpServletRequest request) {
        String domainName = null;

        String serverName = request.getRequestURL().toString();
        if (serverName == null || serverName.equals("")) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            // "http://"
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int len = domains.length;
            if (len > 3) {
                // www.xxx.com.cn
                domainName = "." + domains[len - 3] + "." + domains[len - 2] + "." + domains[len - 1];
            } else if (len <= 3 && len > 1) {
                // xxx.com or xxx.cn
                domainName = "." + domains[len - 2] + "." + domains[len - 1];
            } else {
                domainName = serverName;
            }
        }

        if (domainName != null && domainName.indexOf(":") > 0) {
            String[] ary = domainName.split("\\:");
            domainName = ary[0];
        }
        return domainName;
    }

}

package com.ljx.tcwjneln._02variables._07convertVar;

import java.util.HashMap;
import java.util.Map;

import com.ljx.tcwjneln._09util.stringutil.IfEmptyUtil;
import org.apache.commons.lang3.StringUtils;


/**
 *
* @Description: Str_Map相互转换
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
* @Remark:
* @CodeBug解决:
* @date 2021年8月6日 下午7:42:09
* @author  ljx
*
 */
public class _05Convert_Str_Map {

    public static void main(String[] args) {
        // 将url参数转换成map
        convertUrlParamsStrToMap("aa=11&bb=22&cc=33");
        // 将map转换成url
        convertMapToUrlParamsStr(convertUrlParamsStrToMap("aa=11&bb=22&cc=33"));
    }
    /**
     *
     * @Description: 将请求路径中的url参数字符串 ————>转 map
     * @CodeSteps：
     * @param param	 	"aa=11&bb=22&cc=33"
     * @return			{aa=11, bb=22, cc=33}
     * @throws
     * @ApiGrammer规则：
     * @Remark:
     */
    public static Map<String, Object> convertUrlParamsStrToMap(String param) {
        Map<String, Object> map = new HashMap<String, Object>(0);
        if (IfEmptyUtil.isEmpty(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            String[] p = params[i].split("=");
            if (p.length == 2) {
                map.put(p[0], p[1]);
            }
        }
        return map;
    }
    /**
     *
     * @Description: 将map ————>转 url参数字符串
     * @CodeSteps：
     * @param map	{aa=11, bb=22, cc=33}
     * @return		"aa=11&bb=22&cc=33"
     * @throws
     * @ApiGrammer规则：
     * @Remark:
     */
    public static String convertMapToUrlParamsStr(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }
}

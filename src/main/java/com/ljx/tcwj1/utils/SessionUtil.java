package com.ljx.tcwj1.utils;

import com.ljx.tcwjneln._02variables._07convertVar._05Convert_Str_Map;

import javax.websocket.Session;
import java.util.Map;

/**
 * @author ljx
 * @Description:
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2022/6/6 16:59
 */
public class SessionUtil {
    /**
     * 从session中，获取请求路径中携带的信息
     * @param session
     */
    public static void gainUrlParamFromSession(Session session){
        // {moduleCode=dkh,userId=54fdea80-f6a5-11eb-9810-f44d300627e6}
        Map<String, String> map = session.getPathParameters();
        // param1=paramVal123ss
        String str = session.getQueryString();
        // ws://192.168.21.21:8090/thymeleaf_charts_websocket_jpa/userWs1/dkh/54fdea80-f6a5-11eb-9810-f44d300627e6?param1=paramVal123ss
        String uri = session.getRequestURI().toString();
        // param1=paramVal123ss
        String paramsStr=uri.substring(uri.indexOf("?")+1);
        // {param1=paramVal123ss}
        Map<String, Object>  paramsMap= _05Convert_Str_Map.convertUrlParamsStrToMap(paramsStr);
    }
}

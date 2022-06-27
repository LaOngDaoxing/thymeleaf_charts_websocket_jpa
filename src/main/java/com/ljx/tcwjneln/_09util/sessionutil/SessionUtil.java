package com.ljx.tcwjneln._09util.sessionutil;

import com.ljx.tcwj1.pojo.doo.UserDO;
import com.ljx.tcwjneln._02variables._07convertVar._05Convert_Str_Map;
import com.ljx.tcwjneln._09util.constantutil.ConstantUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.Date;
import java.util.Map;

/**
 * @author ljx
 * @Description: session工具类
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
        ehcache是一个缓存服务，它将数据存在内存当中，即使你的tomcat等服务器容器停止了，数据依然在内存；
        session是由服务器容器管理的，当服务器容器停止以后，session数据也没了；
        还有就是创建session的时候会向客户端写入一个cookies。
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/20 0020 下午 3:38
 */
public class SessionUtil {
    /**
     * SpringMvc下获取request
     * @return
     */
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;

    }
    /**
     * SpringMvc下获取session
     * @return
     */
    public static HttpSession getSession() {
        HttpSession session = getRequest().getSession();
        return session;
    }
    /**
     * Description:
     * @CodeSteps：
                    校验|验证|检查 登录的用户信息 一致后，保存当前登录的用户信息到session中；
                    若第一次登录，则新增一条数据到登录信息表中；若第n次登录，则修改一条数据在登录信息表中
                    添加登陆日志，到数据库 日志表中
     * @Param req:
     * @Param user:
     * @return: void
     * Author: ljx
     * Date: 2021/9/28 0028 下午 2:27
      */
    public static void ifCheckUserSuccessThenSaveIntoSession(HttpServletRequest req, UserDO user) {
        HttpSession session = SessionUtil.getSession();
        session.setAttribute(ConstantUtil.LOCAL_CLINET_USER, user);
        // 在线用户的管理中，当前session为空 或者 当前session的用户信息与刚输入的用户信息一致时，则更新Client信息
        if (1==1) {

        }
        // 如果不一致，则注销session并通过session=req.getSession(true)初始化session
        else {
            session.invalidate();
            //session初始化
            session = req.getSession(true);
            session.setAttribute(ConstantUtil.LOCAL_CLINET_USER, user);
            //保存验证码
            session.setAttribute("randCode", req.getParameter("randCode"));
        }
    }
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

package com.ljx.tcwjneln._09util.sessionutil;

import com.ljx.tcwj1.pojo.doo.UserDO;
import com.ljx.tcwjneln._09util.constantutil.ConstantUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author ljx
 * @Description: session工具类
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
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
    }
}

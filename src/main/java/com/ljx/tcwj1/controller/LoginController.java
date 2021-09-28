package com.ljx.tcwj1.controller;

import com.ljx.tcwj1.pojo.doo.UserDO;
import com.ljx.tcwjneln._09util.constantutil.ConstantUtil;
import com.ljx.tcwjneln._09util.maputil.MapGetter;
import com.ljx.tcwjneln._09util.sessionutil.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljx
 * @Description: 用户登录
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/9 0009 上午 11:11
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    /**
     * Title: checkuser
     * Description:  校验|验证|检查 登录的用户信息
     * @CodeSteps：  根据 登录的用户信息，与数据库表的用户信息是否一致；
                     若不一致，则返回提示信息“201”；
                     若一致，则返回提示信息“200”，并将用户信息存储到session中；
     * @Param user:
     * @Param request:
     * @return: java.lang.String
     * Author: ljx
     * Date: 2021/8/20 0020 下午 2:08
      */
    @RequestMapping(params = "checkuser_noAuth")
    @ResponseBody
    public Map<String,Object> checkuser(UserDO user, HttpServletRequest request) {
        Map<String,Object> backMap=new HashMap<String,Object>(MapGetter.defaultInitialCapacity());
        // 根据用户名、密码，查询用户表的一条用户数据
        UserDO oneUser=new UserDO();
        // 用户数据为空（即不存在此用户），则返回提示“用户名、密码错误”
        if(oneUser == null){
            backMap.put("rstCode",201);
        }
        // 用户数据不为空（即存在此用户）、用户状态为已启用，则保存用户登录信息到session中，并提示“用户校验成功”。
        if(oneUser != null){
            // 保存当前登录的用户信息到session中
            SessionUtil.ifCheckUserSuccessThenSaveIntoSession(request,user);
            backMap.put("rstCode",200);
        }
        return backMap;
    }
}

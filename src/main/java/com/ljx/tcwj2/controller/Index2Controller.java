package com.ljx.tcwj2.controller;

import com.ljx.tcwjneln._09util.maputil.MapGetter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 *
* @Description: 首页
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
* @Remark:
    http://localhost:8090
    SpringBoot项目在启动后，首先会去静态资源路径（resources/static）下查找 index.html 作为首页文件。
    如果在静态资源路径（resources/static）下找不到 index.html，则会到（resources/templates）目录下找 index.html（使用 Thymeleaf 模版）作为首页文件。
* @AlibabaCodeStatuteScanError：
* @CodeBug解决:
* @Debug调试：
* @date
* @author  ljx
*
 */
@Controller
@RequestMapping("/index2")
public class Index2Controller {
    /**
      跳转到的前台页面路径前缀
     */
    private String userPrefix="general_jsp/tcwjJsp2/user/";
    private String takeawayOrderPrefix="general_jsp/tcwjJsp2/takeawayOrder/";
    /**
     * webUrl测试http://localhost:8090/thymeleaf_charts_websocket_jpa/index2/toQueryByUserIdIndex
     * Description: 跳转单条件图表页面-直接跳转
     * @return: jspStr
      */
    @RequestMapping("/toQueryByUserIdIndex")
    public String  toQueryByUserIdIndexFun1(){
        return takeawayOrderPrefix+"queryByUserId/index";
    }
    /**
     * Description: 跳转单条件图表页面-转发
     * @return: jspStr
      */
    public String  toQueryByUserIdIndexFun2(){
        return "forward:"+takeawayOrderPrefix+"queryByUserId/index.html";
    }
    /**
     * webUrl测试http://localhost:8090/thymeleaf_charts_websocket_jpa/index2/toByUserIdAdd
     * @return
     */
    @RequestMapping("/toByUserIdAdd")
    public String  toByUserIdAdd(){
        return takeawayOrderPrefix+"queryByUserId/add";
    }
    /**
     * webUrl测试http://localhost:8090/thymeleaf_charts_websocket_jpa/index2/toQueryByUserIdAndParamsIndex
     * Description: 跳转多条件图表页面
     * @return: jspStr
      */
    @RequestMapping("/toQueryByUserIdAndParamsIndex")
    public String  toQueryByUserIdAndParamsIndexFun1(){
        return takeawayOrderPrefix+"queryByUserIdAndParams/index";
    }
    /**
     * webUrl测试http://localhost:8090/thymeleaf_charts_websocket_jpa/index2/toByUserIdAndParamsAdd
     * @return
     */
    @RequestMapping("/toByUserIdAndParamsAdd")
    public String  toByUserIdAndParamsAdd(@RequestParam Map<String,Object> params, Model model){
        String param1= MapGetter.getString(params,"param1");
        model.addAttribute("param1",param1);
        return takeawayOrderPrefix+"queryByUserIdAndParams/add";
    }
}

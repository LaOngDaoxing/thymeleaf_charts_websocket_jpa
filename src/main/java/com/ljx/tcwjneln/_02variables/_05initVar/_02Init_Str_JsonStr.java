package com.ljx.tcwjneln._02variables._05initVar;

import java.util.UUID;

/**
 * @author ljx
 * @Description: 初始化并赋值Str
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/9/9 0009 下午 1:49
 */
public class _02Init_Str_JsonStr {

    /**
     *
     * webUrl测试
     * @Description: 生成一个32位的随机字符串：36位UUID，去掉“-”
     * @return void    返回类型
     * @throws
     * @Remark	String uuidStr=UUID.randomUUID().toString().replaceAll("-", "");
     */
    public static String initUuidLen32() {
        // 长度为36位	2e963c9f-26a1-4a01-ab6b-69640487da88
        UUID uuid = UUID.randomUUID();
        // 长度为36位	2e963c9f-26a1-4a01-ab6b-69640487da88
        String uuidStr=uuid.toString();
        // 长度为32位	2e963c9f26a14a01ab6b69640487da88
        uuidStr=uuidStr.replaceAll("-", "");
        return uuidStr;
    }
}

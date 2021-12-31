package com.ljx.tcwj1.pojo.dto;

/**
 * @author ljx
 * @Description: 图表结果DTO
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/12 0012 上午 10:16
 */
public class TakeawayOrderChartDTO {
    private Long eleMe;
    private Long kouBei;
    private Long meiTuan;

    /**
     * 右键》Generate》Getter and Setter...》
     */
    public Long getEleMe() {
        return eleMe;
    }

    public void setEleMe(Long eleMe) {
        this.eleMe = eleMe;
    }
    public Long getKouBei() {
        return kouBei;
    }

    public void setKouBei(Long kouBei) {
        this.kouBei = kouBei;
    }

    public Long getMeiTuan() {
        return meiTuan;
    }

    public void setMeiTuan(Long meiTuan) {
        this.meiTuan = meiTuan;
    }
    /**
     *
     * @Description：无参构造函数
     * @创建步骤：使用字段，生成不带参数的 构造函数：
            英文版步骤：右键》Generate》Constructor》点击Select None 》
     * @Remark: 若在Controller层方法参数中定义了Bean类，如fun(User user)。需在Bean类中构造无参函数
     */
    public TakeawayOrderChartDTO() {
    }
    /**
     * 使用字段，生成带参数的 构造函数：右键》Generate》Constructor》字段全选》点击OK 》
     */
    public TakeawayOrderChartDTO(Long eleMe, Long kouBei, Long meiTuan) {
        this.eleMe = eleMe;
        this.kouBei = kouBei;
        this.meiTuan = meiTuan;
    }
    /**
     * 右键》Source》Generate toString()... 目的是为了属性能打印出来
     */
    @Override
    public String toString() {
        return "TakeawayOrderChartDTO{" +
                "eleMe=" + eleMe +
                ", kouBei=" + kouBei +
                ", meiTuan=" + meiTuan +
                '}';
    }
}

package com.ljx.tcwj1.pojo.dto;

/**
 * @Description: 图表结果DTO
 * @Author Zhouxw
 * @Date 2020/9/18 0018 17:02
 **/
public class UserChartDTO {
    private Long boy;
    private Long girl;
    private Long unknown;

    /**
     * 右键》Generate》Getter and Setter...》
     */
    public Long getBoy() {
        return boy;
    }

    public void setBoy(Long boy) {
        this.boy = boy;
    }

    public Long getGirl() {
        return girl;
    }

    public void setGirl(Long girl) {
        this.girl = girl;
    }

    public Long getUnknown() {
        return unknown;
    }

    public void setUnknown(Long unknown) {
        this.unknown = unknown;
    }

    /**
     *
     * @Description：无参构造函数
     * @创建步骤：使用字段，生成不带参数的 构造函数：
            英文版步骤：右键》Generate》Constructor》点击Select None 》
     * @Remark: 若在Controller层方法参数中定义了Bean类，如fun(User user)。需在Bean类中构造无参函数
     */
    public UserChartDTO() {
    }
    /**
     * 使用字段，生成带参数的 构造函数：右键》Generate》Constructor》字段全选》点击OK 》
     */
    public UserChartDTO(Long boy, Long girl, Long unknown) {
        this.boy = boy;
        this.girl = girl;
        this.unknown = unknown;
    }

    /**
     * 右键》Source》Generate toString()... 目的是为了属性能打印出来
     */
    @Override
    public String toString() {
        return "UserChartDTO{" +
                "boy=" + boy +
                ", girl=" + girl +
                ", unknown=" + unknown +
                '}';
    }
}

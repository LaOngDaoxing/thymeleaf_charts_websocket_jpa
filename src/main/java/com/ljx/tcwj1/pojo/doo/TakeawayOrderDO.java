package com.ljx.tcwj1.pojo.doo;

import javax.persistence.*;

/**
 * @author ljx
 * @Description: 外卖订单对象————实体类，对应mysql数据库jpa_norm_db_test的takeaway_orderdo表
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
        问题百度搜索：@Table(name = "t_user")出现Cannot resolve table "t_user"问题
 * @Debug调试：
 * @date 2021/8/10 0010 下午 2:47
 */
@Entity
@Table(name = "takeaway_orderdo")
@org.hibernate.annotations.Table(appliesTo = "takeaway_orderdo",comment="外卖订单表")
public class TakeawayOrderDO {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 分组编码————外卖订单模块编码waimai
     */
    @Column(nullable = false,columnDefinition = "varchar(100) default '' comment '分组编码'")
    private String groupCode;
    /**
     * 外卖订单id
     */
    @Column(nullable = false,columnDefinition = "varchar(36) default '' comment '外卖订单id'")
    private String takeawayOrderId;
    /**
     * 关联外键-用户id
     */
    @Column(nullable = false,columnDefinition = "varchar(36) default '' comment '关联外键-用户id'")
    private String userId;
    /**
     * 外卖平台id（01、02、03）
     */
    @Column(nullable = false,columnDefinition = "varchar(36) default '' comment '外卖平台id（01、02、03）'")
    private String platformId;
    /**
     * 外卖平台名称（饿了么、口碑、美团）
     */
    @Column(columnDefinition = "varchar(100) default '' comment '外卖平台名称（饿了么、口碑、美团）'")
    private String platformName;
    /**
     * 省编码
     */
    @Column(columnDefinition = "varchar(36) default '' comment '省编码'")
    private String provinceCode;
    /**
     * 省名称
     */
    @Column(columnDefinition = "varchar(100) default '' comment '省名称'")
    private String provinceName;
    /**
     * 市编码
     */
    @Column(columnDefinition = "varchar(36) default '' comment '市编码'")
    private String cityCode;
    /**
     * 市名称
     */
    @Column(columnDefinition = "varchar(100) default '' comment '市名称'")
    private String cityName;
    /**
     * 县编码
     */
    @Column(columnDefinition = "varchar(36) default '' comment '县编码'")
    private String countyCode;
    /**
     * 县名称
     */
    @Column(columnDefinition = "varchar(100) default '' comment '县名称'")
    private String countyName;
    /**
     * 卖家id
     */
    @Column(columnDefinition = "varchar(36) default '' comment '卖家id'")
    private String sellerId;
    /**
     * 卖家名称
     */
    @Column(columnDefinition = "varchar(100) default '' comment '卖家名称'")
    private String sellerName;
    /**
     * 商品id
     */
    @Column(columnDefinition = "varchar(36) default '' comment '商品id'")
    private String goodsId;
    /**
     * 商品名称
     */
    @Column(columnDefinition = "varchar(100) default '' comment '商品名称'")
    private String goodsName;
    /**
     * 查询条件
     */
    private String param1;
    /**
     * 右键》Generate》Getter and Setter...》
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getTakeawayOrderId() {
        return takeawayOrderId;
    }

    public void setTakeawayOrderId(String takeawayOrderId) {
        this.takeawayOrderId = takeawayOrderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    /**
     *
     * @Description：无参构造函数
     * @创建步骤：使用字段，生成不带参数的 构造函数：
            英文版步骤：右键》Generate》Constructor》点击Select None 》
     * @Remark: 若在Controller层方法参数中定义了Bean类，如fun(User user)。需在Bean类中构造无参函数
     */
    public TakeawayOrderDO() {
    }

    /**
     * 使用字段，生成带参数的 构造函数：右键》Generate》Constructor》字段全选》点击OK 》
     */
    public TakeawayOrderDO(Long id, String groupCode, String takeawayOrderId, String userId, String platformId, String platformName, String provinceCode, String provinceName, String cityCode, String cityName, String countyCode, String countyName, String sellerId, String sellerName, String goodsId, String goodsName,String param1) {
        this.id = id;
        this.groupCode = groupCode;
        this.takeawayOrderId = takeawayOrderId;
        this.userId = userId;
        this.platformId = platformId;
        this.platformName = platformName;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.countyCode = countyCode;
        this.countyName = countyName;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.param1 = param1;
    }

    /**
     * 右键》Generate》toString()... 目的是为了属性能打印出来
     */
    @Override
    public String toString() {
        return "TakeawayOrderDO{" +
                "id=" + id +
                ", groupCode='" + groupCode + '\'' +
                ", takeawayOrderId='" + takeawayOrderId + '\'' +
                ", userId='" + userId + '\'' +
                ", platformId='" + platformId + '\'' +
                ", platformName='" + platformName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", countyCode='" + countyCode + '\'' +
                ", countyName='" + countyName + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", sellerName='" + sellerName + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", param1='" + param1 + '\'' +
                '}';
    }
}

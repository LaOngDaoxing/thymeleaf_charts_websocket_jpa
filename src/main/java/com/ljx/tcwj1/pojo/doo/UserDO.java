package com.ljx.tcwj1.pojo.doo;
import javax.persistence.*;

/**
 *
 * @Description: 用户对象————实体类，对应mysql数据库jpa_norm_db_test的userdo表
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则_JPA注解：

 * @ExtendsReference
        一、MVC架构分层情况
            1、应用分层
                终端显示层							开放接口
                请求处理层（Web/Controller层）
                业务逻辑层（Service层）
                通用业务处理层（Manager层）
                数据处理层（Dao层）
                数据库源							外部接口或第三方平台
            2、通用业务处理层（Manager层），它有如下特征：
                1）对第三方平台封装的层，预处理返回结果及转化异常信息；
                2）对Service层通用能力的下沉，如缓存方案、中间件通用处理；
                3）与DAO层交互，对多个DAO的组合复用。
        二、模型
            1、分层领域模型规约：
                DO（ Data Object）：与数据库表结构一一对应，通过DAO层向上传输数据源对象。
                DTO（ Data Transfer Object）：数据传输对象，Service 层或Manager 层 向外传输的对象。
                BO（ Business Object）：业务对象。 由Service层输出的封装业务逻辑的对象。
                    （1）BO层的设计思想
                        创建一个BO层的对象用于重组实体对象DO，即对DAO处理后的原始实体对象DO进行业务逻辑的处理后重新封装成业务对象（BO对象）供显示成直接显示。
                    （2）举例
                        例如用户实体对象 UserDO的属性有：id,name,sex,status,country,province,city
                        对应对象 UserBO的属性可以设计成:id,name,sex,status,address
                        在我们得到实体对象UserDO后，我们再业务层作处理》如将sex,status在库中表示为int,char等1，2，3表示法重组时候set为男，女、状态，正常，锁定。而且可以把实体对象UserDO中的国家，省份，城市，字段拼接成地址字符串，设置到对象UserBO的的address中。
                AO（ Application Object）：应用对象。 在Web层与Service层之间抽象的复用对象模型，极为贴近展示层，复用度不高。
                VO（ View Object）：显示层对象，通常是Web向模板渲染引擎层传输的对象。
                POJO（ Plain Ordinary Java Object）：在本手册中， POJO专指只有setter/getter/toString的简单类，包括DO/DTO/BO/VO等。
                Query：数据查询对象，各层接收上层的查询请求。 注意超过2个参数的查询封装，禁止使用Map类来传输。
            2、领域模型命名规约：
                数据对象：xxxDO，xxx即为数据表名。
                数据传输对象：xxxDTO，xxx为业务领域相关的名称。
                展示对象：xxxVO，xxx一般为网页名称。
                POJO是DO/DTO/BO/VO的统称，禁止命名成xxxPOJO。
 * @Remark:
 * @CodeBug解决:
        问题：@Table(name = "t_user")出现Cannot resolve table "t_user"问题
 * @Debug调试：
 * @date 2021年8月9日 上午9:25:22
 * @author  ljx
 *
 */
@Entity
@Table(name = "userdo")
@org.hibernate.annotations.Table(appliesTo = "userdo",comment="用户表")
public class UserDO {
    @Id
    @GeneratedValue
    private Long id;
    /**
     * 分组编码
     */
    @Column(nullable = false,columnDefinition = "varchar(100) default '' comment '分组编码'")
    private String groupCode;
    /**
     * 用户id
     */
    @Column(nullable = false,columnDefinition = "varchar(36) default '' comment '用户id'")
    private String userId;
    /**
     * 用户名
     */
    @Column(nullable = false, unique = true,columnDefinition = "varchar(100) default '' comment '用户名'")
    private String userName;
    /**
     * 密码
     */
    @Column(nullable = false, unique = true,columnDefinition = "varchar(100) default '' comment '密码'")
    private String password;
    /**
     * 性别
     */
    @Column(nullable = false,columnDefinition = "varchar(100) default '' comment '性别'")
    private Integer sex;
    /**
     * 邮箱
     */
    @Column(columnDefinition = "varchar(100) default '' comment '邮箱'")
    private String email;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @Description：无参构造函数
     * @创建步骤：使用字段，生成不带参数的 构造函数：
            英文版步骤：右键》Generate》Constructor》点击Select None 》
     * @Remark: 若在Controller层方法参数中定义了Bean类，如fun(User user)。需在Bean类中构造无参函数
     */
    public UserDO() {
    }
    /**
     * 使用字段，生成带参数的 构造函数：右键》Generate》Constructor》字段全选》点击OK 》
     */
    public UserDO(Long id, String groupCode, String userId, String userName, String password, Integer sex, String email) {
        this.id = id;
        this.groupCode = groupCode;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.email = email;
    }

    /**
     * 右键》Generate》toString()... 目的是为了属性能打印出来
     */
    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", groupCode='" + groupCode + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                '}';
    }
}

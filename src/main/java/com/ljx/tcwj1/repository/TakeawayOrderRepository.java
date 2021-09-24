package com.ljx.tcwj1.repository;

import com.ljx.tcwj1.pojo.doo.TakeawayOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
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
 * @date 2021/8/10 0010 下午 5:16
 */
@Repository
public interface TakeawayOrderRepository extends JpaRepository<TakeawayOrderDO, Long> {

    /**
     * 根据性别、userId，查询用户表条数
     * @param platformId    平台id
     * @param userId        用户id
     * @return
     * @ApiGrammer规则：
             // 有nativeQuery = true时，是可以执行原生sql语句，所谓原生sql，也就是说这段sql拷贝到数据库中，然后把参数值给一下就能运行了。
             @Query(value = "原生sql语句",  nativeQuery = true)
             // 没有nativeQuery = true时，就不是原生sql，而其中的select * from xxx中xxx也不是数据库对应的真正的表名，而是对应的实体名，并且sql中的字段名也不是数据库中真正的字段名，而是实体的字段名。
             @Query( "使用实体对象及实体字段的sql语句" )
     */
    int countByPlatformIdAndUserId(String platformId, String userId);
    /**
     * 根据userId，列表查询
     * @param userId 用户id
     * @return
     * @ApiGrammer规则：
             // 有nativeQuery = true时，是可以执行原生sql语句，所谓原生sql，也就是说这段sql拷贝到数据库中，然后把参数值给一下就能运行了。
             @Query(value = "原生sql语句",  nativeQuery = true)
             // 没有nativeQuery = true时，就不是原生sql，而其中的select * from xxx中xxx也不是数据库对应的真正的表名，而是对应的实体名，并且sql中的字段名也不是数据库中真正的字段名，而是实体的字段名。
             @Query( "使用实体对象及实体字段的sql语句" )
     */
    @Query("SELECT platformId AS platformId,count(platformId) AS platformIdNum FROM TakeawayOrderDO WHERE 1=1 and userId = ?1 GROUP BY platformId")
    List<Map<String, Object>> selectSexNumListByUserId(String userId);
    /**
     * 根据userId、前台页面条件|请求参数param1，列表查询
     * @param userId
     * @param param1
     * @return
     * @ApiGrammer规则：
             // 有nativeQuery = true时，是可以执行原生sql语句，所谓原生sql，也就是说这段sql拷贝到数据库中，然后把参数值给一下就能运行了。
             @Query(value = "原生sql语句",  nativeQuery = true)
             // 没有nativeQuery = true时，就不是原生sql，而其中的select * from xxx中xxx也不是数据库对应的真正的表名，而是对应的实体名，并且sql中的字段名也不是数据库中真正的字段名，而是实体的字段名。
             @Query( "使用实体对象及实体字段的sql语句" )
     */
    @Query(value = "SELECT platform_id AS platformId,count(platform_id) AS platformIdNum FROM takeaway_orderdo WHERE 1=1 and user_id= :userId  and if(:param1!='',goods_name LIKE CONCAT('%',:param1,'%'),1=1) GROUP BY platform_id ",  nativeQuery = true)
    List<Map<String, Object>> selectSexNumListByParams(@Param("userId") String userId, @Param("param1") String param1);
}

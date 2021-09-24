package com.ljx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
/**
 *
* @Description: springboot主配置文件——启动程序
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
	SpringBoot项目的Bean装配默认规则是根据Application类所在的包位置从上往下扫描。使用注解@MapperScan("加上本项目的service或dao所在文件位置即可")。
* @Remark:
* @AlibabaCodeStatuteScanError：
* @CodeBug解决:
* @Debug调试：
* @date ${DATE} ${TIME}
* @author  ljx
*
 */
@EnableWebSocket
@SpringBootApplication
public class ChartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChartApplication.class, args);
		System.out.println("服务启动成功！");
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}

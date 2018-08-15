package com.xkcoding.shiny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * <p>
 * 后端启动类
 * </p>
 *
 * @package: com.xkcoding.shiny
 * @description： 后端启动类
 * @author: yangkai.shen
 * @date: Created in 2018/8/15 上午10:24
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.xkcoding.shiny.mapper"})
public class ShinyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShinyApplication.class, args);
	}
}

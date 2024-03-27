package com.staroot.h2multi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;


@SpringBootApplication
public class H2multiApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2multiApplication.class, args);
	}
/*
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	public DataSource dataSource1() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	public DataSource dataSource2() {
		return DataSourceBuilder.create().build();
	}

 */
}
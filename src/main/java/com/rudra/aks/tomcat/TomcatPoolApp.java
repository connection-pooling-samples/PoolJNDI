package com.rudra.aks.tomcat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TomcatPoolApp {

	public static void main(String[] args) {
		SpringApplication.run(TomcatPoolApp.class, args);
	}

	@Autowired
	DataSource	dataSource;
	
	@Bean
	public	JdbcTemplate	jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
}

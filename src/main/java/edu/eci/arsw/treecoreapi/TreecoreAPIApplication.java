package edu.eci.arsw.treecoreapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication(scanBasePackages = {"edu.eci.arsw.treecore"})
@MapperScan("edu.eci.arsw.treecore.persistence.mappers")
public class TreecoreAPIApplication {
	public static void main(String[] args) {
		SpringApplication.run(TreecoreAPIApplication.class, args);
	}
}
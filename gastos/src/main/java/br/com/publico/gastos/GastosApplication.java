package br.com.publico.gastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastosApplication.class, args);
	}

}

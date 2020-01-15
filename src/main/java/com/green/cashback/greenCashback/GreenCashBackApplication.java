package com.green.cashback.greenCashback;

import com.green.cashback.greenCashback.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SwaggerConfig.class)
public class GreenCashBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GreenCashBackApplication.class, args);
	}

}

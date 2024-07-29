package com.booking.airline;

import com.booking.airline.util.EncryptionUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AirlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApplication.class, args);
	}

	@Bean
	EncryptionUtil getEncryptionUtilBean(){
		return new EncryptionUtil();
	}

}

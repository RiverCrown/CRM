package com.example.crm;

import com.example.crm.domain.Staff;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}

	@Bean
    public Staff test() {
	    return new Staff("cfb", true, "123", 1, "salesman", "123");
    }

    @Bean
    public Staff lest() {
        return new Staff("666", true, "666", 666, "personnelManager", "666");
    }
}

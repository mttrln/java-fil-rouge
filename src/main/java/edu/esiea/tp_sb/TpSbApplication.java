package edu.esiea.tp_sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("edu.esiea.tp_sb.domain.entity")
public class TpSbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TpSbApplication.class, args);
	}

}

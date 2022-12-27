package com.company.msclinic;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Clinic Apllication", version = "0.0.1-SNAPSHOT"))
public class MsClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsClinicApplication.class, args);
    }

}

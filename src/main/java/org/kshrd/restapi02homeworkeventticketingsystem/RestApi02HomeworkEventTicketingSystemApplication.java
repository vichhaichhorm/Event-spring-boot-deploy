package org.kshrd.restapi02homeworkeventticketingsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Event Ticketing System", description = "Efficiently manage events with our streamlined ticketing system.", version = "1.0"))
public class RestApi02HomeworkEventTicketingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApi02HomeworkEventTicketingSystemApplication.class, args);
    }

}

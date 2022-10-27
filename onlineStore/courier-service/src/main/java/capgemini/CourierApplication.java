package capgemini;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourierApplication {
    @Bean
    public ModelMapper modelMappper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(CourierApplication.class, args);
    }

}
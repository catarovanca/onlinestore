package capgemini;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderApplication {

    @Bean
    public ModelMapper modelMappper(){
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
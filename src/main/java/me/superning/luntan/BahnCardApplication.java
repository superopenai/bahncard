package me.superning.luntan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "me.superning.luntan.mapper")
public class BahnCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BahnCardApplication.class, args);
    }

}

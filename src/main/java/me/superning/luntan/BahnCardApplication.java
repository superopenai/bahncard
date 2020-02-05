package me.superning.luntan;

import me.superning.luntan.security.AuthCheckInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;

@SpringBootApplication
@MapperScan(basePackages = "me.superning.luntan.mapper")
public class BahnCardApplication extends WebMvcConfigurationSupport {
    @Resource
    AuthCheckInterceptor authCheckInterceptor;


    @Override
    protected void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(authCheckInterceptor)
                .addPathPatterns("/merchant/**");
    }

    public static void main(String[] args) {
        SpringApplication.run(BahnCardApplication.class, args);
    }

}

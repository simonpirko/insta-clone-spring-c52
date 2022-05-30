package by.tms.insta.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "by.tms.insta")
@EnableWebMvc
public class WebConfiguration extends WebMvcConfigurerAdapter {

}

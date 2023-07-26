package com.devsuperior.userdept;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
public class UserdeptApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserdeptApplication.class, args);
    }
     
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}

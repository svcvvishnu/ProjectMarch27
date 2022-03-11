package com.csci5308.w22.wiseshopping;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
public class WiseshoppingApplication {


    public static void main(String[] args) {
        SpringApplication.run(WiseshoppingApplication.class, args);
    }

//    @Profile("!dev")
//    @Bean
//    public Runner getRunner(){
//        return new Runner();
//    }
//
    @Bean
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

//    @Bean
//    public ScreenFactory screenFactory(){
//        return  new ScreenFactory();
//    }


}

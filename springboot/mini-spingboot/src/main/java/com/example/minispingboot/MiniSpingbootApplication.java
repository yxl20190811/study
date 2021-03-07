package com.example.minispingboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;

@SpringBootApplication
public class MiniSpingbootApplication {

    public static void main(String[] args) throws ServletException {
        TMiniSpringboot pThis = new TMiniSpringboot();
        pThis.init();
        String packName = pThis.getClass().getPackageName();
        SpringApplication.run(MiniSpingbootApplication.class, args);
    }

}

package com.example.flowblebootapp;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.flowable.common.engine.api.FlowableException;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.flowblebootapp.delegate.DemoDelegate1;

@SpringBootApplication
public class FlowbleBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowbleBootAppApplication.class, args);
    }

    @Bean
    JavaDelegate delegate1(){
        return delegateExecution -> {
            System.out.println("delegate1");
        };
    }

    @Bean
    JavaDelegate delegate2(){
        return delegateExecution -> {
            throw new FlowableException("FLOOOOOP");
        };
    }
}

@Component
class Runner implements CommandLineRunner {
    static Logger logger = Logger.getLogger(Runner.class.getName());


    protected final RuntimeService runtimeService;

    @Autowired
    Runner(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("-----------------------------------");
        logger.info("Starting Demo Process");
        logger.info("-----------------------------------");

        Map<String, Object> vars = new HashMap<>();

        vars.put("fizz", "pop");
        vars.put("meaningOfLife", 42);

        runtimeService.startProcessInstanceByKey("demoProcess", vars);
        runtimeService.startProcessInstanceByKey("demoProcess2", vars);
    }
}
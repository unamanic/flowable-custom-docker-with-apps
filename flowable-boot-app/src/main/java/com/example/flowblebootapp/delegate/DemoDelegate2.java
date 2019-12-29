package com.example.flowblebootapp.delegate;

import java.util.logging.Logger;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class DemoDelegate2 implements JavaDelegate {
    static Logger logger = Logger.getLogger(DemoDelegate2.class.getName());

    @Override
    public void execute(DelegateExecution delegateExecution) {
        logger.info("-----------------------------------");
        logger.info("In Demo Delegate 2");
        logger.info("-----------------------------------");
        logger.info("Listing Variables:");

        delegateExecution.getVariables().entrySet().forEach(stringObjectEntry -> {
            logger.info(stringObjectEntry.getKey() + ": " + stringObjectEntry.getValue());
        });
    }
}

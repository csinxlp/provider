/**
 * Eigpay.com Inc.
 * Copyright (c) 2013-2066 All Rights Reserved.
 */
package com.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xinming chen
 * @version $Id: ServiceProviderStarter.java
 */
public class ServiceProviderStarter {
    private final static Logger logger = LoggerFactory.getLogger(ServiceProviderStarter.class);
    private static volatile boolean running = true;

    public static void main(String[] args){
        try {
            logger.info("开始启动服务提供端服务！");
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                    new String[]{"spring/applicationContext-remote.xml"});

            ((AbstractApplicationContext) applicationContext).registerShutdownHook();

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    logger.info("停止服务提供端服务!");

                    synchronized (ServiceProviderStarter.class) {
                        running = false;
                        ServiceProviderStarter.class.notify();
                    }
                }
            });

            logger.info("服务提供端服务启动成功！");

            synchronized (ServiceProviderStarter.class) {
                while (running) {
                    try {
                        ServiceProviderStarter.class.wait();
                    } catch (Throwable e) {
                        logger.error(e.getMessage(),e);
                        //ignore
                    }
                }
            }
        } catch (Exception e) {
            logger.error("异常关闭服务提供端", e);
            System.exit(0);
        }
    }
}

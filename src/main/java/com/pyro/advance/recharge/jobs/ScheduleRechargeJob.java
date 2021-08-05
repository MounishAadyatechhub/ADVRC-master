package com.pyro.advance.recharge.jobs;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Slf4j
public class ScheduleRechargeJob {
   Logger logger = LoggerFactory.getLogger(ScheduleRechargeJob.class);

    //@Scheduled(cron="*/5 * * * * MON-FRI")
    //@Scheduled(cron="*/5 * * * * *")
    public void doSomething() {
        System.out.println("====New Date ====="+new Date());
        logger.info("Hello====");
        // this will execute on weekdays
    }
}
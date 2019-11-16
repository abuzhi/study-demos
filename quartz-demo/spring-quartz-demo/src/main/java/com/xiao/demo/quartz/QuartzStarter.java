package com.xiao.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

/**
 * Created by xiao on 2019/11/14.
 */
public class QuartzStarter {
    public static final Logger logger = LoggerFactory.getLogger(QuartzStarter.class);
    private static Scheduler scheduler;

    public static void main(String[] args) throws Exception {
        logger.info("start.");

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();

        scheduler.start();
        logger.info("end .");
    }
}

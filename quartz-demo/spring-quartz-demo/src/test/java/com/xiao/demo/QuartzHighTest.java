package com.xiao.demo;

import com.wanmei.df.tick.scheduler.trigger.ScheduleJob;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by xiao on 2019/11/15.
 */
public class QuartzHighTest {
    private static final int num = 10000;
    private Scheduler scheduler;
    private static final String JOB_GROUP = "test_group_high_" + num;
    private static final String TRIGGER_GROUP = "test_group_high" + num;;

    @Before
    public void init() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
    }

    @Test
    public void start() throws Exception {
        scheduler.start();
        int time = 20 * 60;
        while (scheduler.isStarted() && time>0){
            Thread.sleep(1000 );
            time--;
        }
        scheduler.shutdown(true);
    }

    @Test
    public void addBatchJob() throws Exception {
        for(int i = 1;i<=num;i++){
            JobKey jobKey = new JobKey("job_" + i,JOB_GROUP);
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobKey)
                    .build();
            scheduler.addJob(jobDetail, false, true);
            TriggerKey triggerKey = new TriggerKey("trigger_" + i,TRIGGER_GROUP);
            String cronExpression = "0 0/5 * * * ? *";
            CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                    .withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                    .startAt(new Date(1573374280000L))
                    .endAt(new Date(1574065480000L))
                    .forJob(jobDetail).build();
            scheduler.scheduleJob(cronTrigger);
        }

    }

    @Test
    public void multAddJob() {
        int size = 20;
        int total = 100000;
        int pageSize = total / size ;
        int pageNo = 0;

        ExecutorService pool = Executors.newFixedThreadPool(size);
        for(int i = 0;i<size;i++){
            int start = pageNo * pageSize;
            int end = start + pageSize;

        }
    }

}

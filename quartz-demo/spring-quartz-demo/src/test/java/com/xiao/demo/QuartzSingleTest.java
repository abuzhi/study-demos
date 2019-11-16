package com.xiao.demo;

import com.wanmei.df.tick.scheduler.trigger.ScheduleJob;
import org.junit.Before;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by xiao on 2019/11/15.
 */
public class QuartzSingleTest {

    private Scheduler scheduler;
    private static final String JOB_KEY = "test_job_1";
    private static final String TRIGGER_KEY = "test_trigger_1";
    private static final String JOB_GROUP = "test_group";
    private static final String TRIGGER_GROUP = "test_group";

    @Before
    public void init() throws Exception {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();
    }

    @Test
    public void fireTime() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        scheduler.getJobDetail(jobKey);
    }

    @Test
    public void start() throws Exception {
        scheduler.start();
        Thread.sleep(60000);
        scheduler.shutdown(true);
    }

    @Test
    public void addJob() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobKey)
                .build();
        scheduler.addJob(jobDetail, false, true);
        TriggerKey triggerKey = new TriggerKey(TRIGGER_KEY,TRIGGER_GROUP);
        String cronExpression = "0/5 * * * * ? *";
        CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                .withSchedule(cronSchedule(cronExpression))
                .startAt(new Date(1573801200000L))  //2019/11/15 15:00:00
                .endAt(new Date(1573819200000L))    //2019/11/15 20:00:00
                .forJob(jobDetail).build();
        scheduler.scheduleJob(cronTrigger);
    }

    @Test
    public void addJobDefualt() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobKey)
                .build();
        scheduler.addJob(jobDetail, false, true);
        TriggerKey triggerKey = new TriggerKey(TRIGGER_KEY,TRIGGER_GROUP);
        String cronExpression = "0/5 * * * * ? *";
        CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                .withSchedule(cronSchedule(cronExpression))
                .startAt(new Date(1573801200000L))  //2019/11/15 15:00:00
                .endAt(new Date(1573819200000L))    //2019/11/15 20:00:00
                .forJob(jobDetail).build();
        scheduler.scheduleJob(cronTrigger);
    }

    @Test
    public void addJobDoNothing() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobKey)
                .build();
        scheduler.addJob(jobDetail, false, true);
        TriggerKey triggerKey = new TriggerKey(TRIGGER_KEY,TRIGGER_GROUP);
        String cronExpression = "0/5 * * * * ? *";
        CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                .withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing())
                .startAt(new Date(1573801200000L))  //2019/11/15 15:00:00
                .endAt(new Date(1573819200000L))    //2019/11/15 20:00:00
                .forJob(jobDetail).build();
        scheduler.scheduleJob(cronTrigger);
    }

    @Test
    public void addJobIgnore() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobKey)
                .build();
        scheduler.addJob(jobDetail, false, true);
        TriggerKey triggerKey = new TriggerKey(TRIGGER_KEY,TRIGGER_GROUP);
        String cronExpression = "0/5 * * * * ? *";
        CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                .withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionIgnoreMisfires())
                .startAt(new Date(1573801200000L))  //2019/11/15 15:00:00
                .endAt(new Date(1573819200000L))    //2019/11/15 20:00:00
                .forJob(jobDetail).build();
        scheduler.scheduleJob(cronTrigger);
    }

    @Test
    public void deleteJob() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        scheduler.deleteJob(jobKey);
    }


    @Test
    public void updateJob() throws Exception {
        JobKey jobKey = new JobKey(JOB_KEY,JOB_GROUP);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        TriggerKey triggerKey = new TriggerKey(TRIGGER_KEY,TRIGGER_GROUP);

        String cronExpression = "0/10 * * * * ? *";
        CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                .withSchedule(cronSchedule(cronExpression).withMisfireHandlingInstructionIgnoreMisfires())
                .startAt(new Date(1573801200000L))  //2019/11/15 15:00:00
                .endAt(new Date(1573819200000L))    //2019/11/15 20:00:00
                .forJob(jobDetail).build();

        scheduler.rescheduleJob(triggerKey, cronTrigger);
    }
}

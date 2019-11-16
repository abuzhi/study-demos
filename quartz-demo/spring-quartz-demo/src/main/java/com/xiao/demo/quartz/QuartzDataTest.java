package com.xiao.demo.quartz;

import com.wanmei.df.tick.scheduler.trigger.ScheduleJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by xiao on 2019/11/14.
 * quartz 做一个压力测试，看看能运行多少任务调度
 */
public class QuartzDataTest {
    public static final Logger logger = LoggerFactory.getLogger(MainStarter.class);
    private static Scheduler scheduler;

    public static void main(String[] args) throws SchedulerException {
        logger.info("start.");
        int nums = 10000;
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        scheduler = schedulerFactory.getScheduler();

            for(int i=0;i<nums;i++){
                JobKey jobKey = new JobKey("job_"+i);
                JobDetail jobDetail;
                if (scheduler.checkExists(jobKey)) {
                    jobDetail = scheduler.getJobDetail(jobKey);
                } else {
                    jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(jobKey)
                            .build();
                    scheduler.addJob(jobDetail, false, true);
                }

                TriggerKey triggerKey = new TriggerKey("trigger5");

                String cronExpression = "1/5 * * * * ? *";
                CronTrigger cronTrigger = newTrigger().withIdentity(triggerKey)
                        .withSchedule(cronSchedule(cronExpression)).startAt(new Date(1573374280000L))
                        .endAt(new Date(1574065480000L))
                        .forJob(jobDetail).build();
                if (scheduler.checkExists(triggerKey)) {
                    // updateProcessInstance scheduler trigger when scheduler cycle changes
                    CronTrigger oldCronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
                    String oldCronExpression = oldCronTrigger.getCronExpression();
                    if (!StringUtils.equalsIgnoreCase(cronExpression, oldCronExpression)) {
                        // reschedule job trigger
                        scheduler.rescheduleJob(triggerKey, cronTrigger);
                    }
                } else {
                    scheduler.scheduleJob(cronTrigger);
                }
        }
        scheduler.start();
        logger.info("end .");
    }
}

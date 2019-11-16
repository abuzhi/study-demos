package com.wanmei.df.tick.scheduler.trigger;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiao on 2019/10/9.
 */
public class ScheduleJob implements Job {

    public static final Logger logger = LoggerFactory.getLogger(ScheduleJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger =  context.getTrigger();
        Date time = context.getScheduledFireTime();
        Date time2 = context.getNextFireTime();
        Date time1 = context.getPreviousFireTime();

        logger.info(jobDetail.getKey()
                + " exe , schedule time =  "+  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(time)
                + " getNextFireTime time =  "+  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(time2)
                + " getPreviousFireTime time =  "+  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(time1)
                + " trigger getNextFireTime time =  "+  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(trigger.getNextFireTime())
                + " trigger getPreviousFireTime time =  "+  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(trigger.getPreviousFireTime())
        );
        try {
            Thread.sleep(17000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

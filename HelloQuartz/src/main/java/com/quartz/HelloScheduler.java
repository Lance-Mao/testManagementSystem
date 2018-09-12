package com.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: HelloQuartz
 * @author: MaoLW
 * @create: 2018-09-11 15:51
 **/

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time is:" + simpleDateFormat.format(date));
        // 创建一个 JobDetail 实例， 将该实例与HelloJob.class 绑定
        JobDetail jobDetail = JobBuilder
                .newJob(HelloJob.class)
                .withIdentity("myJob", "group1")
                .build();


        // 创建一个Trigger实例, 定义该Job立即执行
        CronTrigger trigger = (CronTrigger) TriggerBuilder
                .newTrigger()
                .startAt(date)
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();

        // 创建 scheduler 实例
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}

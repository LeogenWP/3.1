package com.example.demo.util.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class JobStarter {
    private static final SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    public void startJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail getAllSymbols = JobBuilder.newJob(GetAllSymbolsJob.class)
                .withIdentity("GetAllSymbolsJob", "mainGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("allSymbolsJobTrigger", "mainGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .forJob(getAllSymbols)
                .build();

        scheduler.start();
        scheduler.scheduleJob(getAllSymbols, trigger);
    }

    public void startReports() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail getAllSymbols = JobBuilder.newJob(GetAllSymbolsJob.class)
                .withIdentity("GetAllSymbolsJob", "mainGroup")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("allSymbolsJobTrigger", "mainGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .forJob(getAllSymbols)
                .build();

        scheduler.start();
        scheduler.scheduleJob(getAllSymbols, trigger);
    }
}
